package com.github.adiacov.language;

import com.github.adiacov.language.psi.SimpleProperty;
import com.intellij.lang.ASTNode;
import com.intellij.lang.folding.FoldingBuilderEx;
import com.intellij.lang.folding.FoldingDescriptor;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.FoldingGroup;
import com.intellij.openapi.project.DumbAware;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.openapi.util.text.StringUtil;
import com.intellij.psi.JavaRecursiveElementWalkingVisitor;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.util.PsiLiteralUtil;
import com.intellij.util.containers.ContainerUtil;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import static com.github.adiacov.language.SimpleAnnotator.SIMPLE_PREFIX_STR;
import static com.github.adiacov.language.SimpleAnnotator.SIMPLE_SEPARATOR_STR;

public class SimpleFoldingBuilder extends FoldingBuilderEx implements DumbAware {
    @Override
    public FoldingDescriptor @NotNull [] buildFoldRegions(
            @NotNull PsiElement psiElement, @NotNull Document document, boolean b) {
        // Initialize the group of folding regions that will expand/collapse together.
        FoldingGroup group = FoldingGroup.newGroup(SIMPLE_PREFIX_STR);
        // Initialize the list of folding regions
        List<FoldingDescriptor> descriptors = new ArrayList<>();

        psiElement.accept(new JavaRecursiveElementWalkingVisitor() {
            @Override
            public void visitLiteralExpression(@NotNull PsiLiteralExpression expression) {
                super.visitLiteralExpression(expression);

                String value = PsiLiteralUtil.getStringLiteralContent(expression);
                if (value != null && value.startsWith(SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR)) {
                    Project project = expression.getProject();
                    String key = value.substring(SIMPLE_PREFIX_STR.length() + SIMPLE_SEPARATOR_STR.length());

                    // find SimpleProperty for the given key in the project
                    SimpleProperty simpleProperty = ContainerUtil.getOnlyItem(SimpleUtil.findProperties(project, key));
                    if (simpleProperty != null) {
                        // Add a folding descriptor for the literal expression at this node.
                        descriptors.add(new FoldingDescriptor(
                                expression.getNode(),
                                new TextRange(expression.getTextRange().getStartOffset() + 1,
                                        expression.getTextRange().getEndOffset() - 1),
                                group,
                                Collections.singleton(simpleProperty)
                        ));
                    }
                }
            }
        });

        return descriptors.toArray(FoldingDescriptor.EMPTY_ARRAY);
    }

    /**
     * Gets the Simple Language 'value' string corresponding to the 'key'
     *
     * @param astNode Node corresponding to PsiLiteralExpression containing a string in the format
     *                SIMPLE_PREFIX_STR + SIMPLE_SEPARATOR_STR + Key, where Key is
     *                defined by the Simple language file.
     */
    @Override
    public @Nullable String getPlaceholderText(@NotNull ASTNode astNode) {
        if (astNode.getPsi() instanceof PsiLiteralExpression literalExpression) {
            String text = PsiLiteralUtil.getStringLiteralContent(literalExpression);
            if (text == null) {
                return null;
            }

            String key = text.substring(SIMPLE_PREFIX_STR.length() + SIMPLE_SEPARATOR_STR.length());
            SimpleProperty simpleProperty = ContainerUtil.getOnlyItem(SimpleUtil.findProperties(
                    literalExpression.getProject(), key));
            if (simpleProperty == null) {
                return StringUtil.THREE_DOTS;
            }

            String value = simpleProperty.getValue();
            // IMPORTANT: keys can come with no values, so a test for null is needed
            // IMPORTANT: Convert embedded \n to backslash n, so that the string will look
            // like it has LF embedded in it and embedded " to escaped "
            if (value == null) {
                return StringUtil.THREE_DOTS;
            }

            return value
                    .replaceAll("\n", "\\n")
                    .replaceAll("\"", "\\\\\"");
        }

        return null;
    }

    @Override
    public boolean isCollapsedByDefault(@NotNull ASTNode astNode) {
        return true;
    }
}
