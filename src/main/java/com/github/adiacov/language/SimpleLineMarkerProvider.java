package com.github.adiacov.language;

import com.github.adiacov.language.psi.SimpleProperty;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerInfo;
import com.intellij.codeInsight.daemon.RelatedItemLineMarkerProvider;
import com.intellij.codeInsight.navigation.NavigationGutterIconBuilder;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiLiteralExpression;
import com.intellij.psi.impl.source.tree.java.PsiJavaTokenImpl;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;
import java.util.List;

public final class SimpleLineMarkerProvider extends RelatedItemLineMarkerProvider {

    @Override
    protected void collectNavigationMarkers(@NotNull PsiElement element, @NotNull Collection<? super RelatedItemLineMarkerInfo<?>> result) {
        // This must be an element with a literal expression as a parent
        if (!(element.getParent() instanceof PsiLiteralExpression literalExpression) || !(element instanceof PsiJavaTokenImpl)) {
            return;
        }

        // The literal expression must start with the Simple language literal expression
        String value = literalExpression.getValue() instanceof String ? (String) literalExpression.getValue() : null;
        if ((value == null) || !value.startsWith(SimpleAnnotator.SIMPLE_PREFIX_STR + SimpleAnnotator.SIMPLE_SEPARATOR_STR)) {
            return;
        }

        // Get the Simple language property usage
        Project project = element.getProject();
        String possibleProperties = value.substring(
                SimpleAnnotator.SIMPLE_PREFIX_STR.length() + SimpleAnnotator.SIMPLE_SEPARATOR_STR.length()
        );
        List<SimpleProperty> properties = SimpleUtil.findProperties(project, possibleProperties);
        if (!properties.isEmpty()) {
            // Add the property to a collection of line marker info
            NavigationGutterIconBuilder<PsiElement> builder =
                    NavigationGutterIconBuilder.create(SimpleIcons.FILE)
                            .setTargets(properties)
                            .setTooltipText("Navigate to Simple language property");
            result.add(builder.createLineMarkerInfo(element));
        }
    }
}
