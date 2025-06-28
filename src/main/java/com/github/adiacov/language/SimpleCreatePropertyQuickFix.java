package com.github.adiacov.language;

import com.github.adiacov.language.psi.SimpleElementFactory;
import com.github.adiacov.language.psi.SimpleFile;
import com.github.adiacov.language.psi.SimpleProperty;
import com.github.adiacov.language.psi.SimpleTypes;
import com.intellij.codeInsight.intention.impl.BaseIntentionAction;
import com.intellij.codeInspection.util.IntentionFamilyName;
import com.intellij.codeInspection.util.IntentionName;
import com.intellij.lang.ASTNode;
import com.intellij.openapi.application.ApplicationManager;
import com.intellij.openapi.command.WriteCommandAction;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.fileChooser.FileChooser;
import com.intellij.openapi.fileChooser.FileChooserDescriptor;
import com.intellij.openapi.fileChooser.FileChooserDescriptorFactory;
import com.intellij.openapi.fileEditor.FileEditorManager;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.project.ProjectUtil;
import com.intellij.openapi.vfs.VirtualFile;
import com.intellij.pom.Navigatable;
import com.intellij.psi.PsiFile;
import com.intellij.psi.PsiManager;
import com.intellij.psi.search.FileTypeIndex;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.IncorrectOperationException;
import org.jetbrains.annotations.NotNull;

import java.util.Collection;

public final class SimpleCreatePropertyQuickFix extends BaseIntentionAction {

    private final String key;

    public SimpleCreatePropertyQuickFix(String key) {
        this.key = key;
    }

    @Override
    public @NotNull @IntentionName String getText() {
        return "Create property '" + key + "'";
    }

    @Override
    public @NotNull @IntentionFamilyName String getFamilyName() {
        return "Create property";
    }

    @Override
    public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile psiFile) {
        return true;
    }

    @Override
    public void invoke(@NotNull Project project, Editor editor, PsiFile psiFile) throws IncorrectOperationException {
        ApplicationManager.getApplication().invokeLater(() -> {
            @NotNull Collection<VirtualFile> virtualFiles =
                    FileTypeIndex.getFiles(SimpleFileType.INSTANCE, GlobalSearchScope.allScope(project));
            if (virtualFiles.size() == 1) {
                createProperty(project, virtualFiles.iterator().next());
            } else {
                FileChooserDescriptor descriptor =
                        FileChooserDescriptorFactory.createSingleFileDescriptor(SimpleFileType.INSTANCE);
                descriptor.setRoots(ProjectUtil.guessProjectDir(project));
                VirtualFile file = FileChooser.chooseFile(descriptor, project, null);
                if (file != null) {
                    createProperty(project, file);
                }
            }
        });
    }

    private void createProperty(Project project, VirtualFile file) {
        WriteCommandAction.writeCommandAction(project).run(() -> {
            SimpleFile simpleFile = (SimpleFile) PsiManager.getInstance(project).findFile(file);
            assert simpleFile != null;

            ASTNode lastChildNode = simpleFile.getNode().getLastChildNode();
            // TODO: Add another check for CRLF
            if (lastChildNode != null && !lastChildNode.getElementType().equals(SimpleTypes.CRLF)) {
                simpleFile.getNode().addChild(SimpleElementFactory.createCRLF(project).getNode());
                simpleFile.getNode().addChild(SimpleElementFactory.createCRLF(project).getNode());
            }
            // IMPORTANT: change spaces to escaped spaces or the new node will only have the first word for the key
            SimpleProperty property = SimpleElementFactory.createProperty(project, key.replaceAll(" ", "\\\\ "), "");
            simpleFile.getNode().addChild(property.getNode());
            ((Navigatable) property.getLastChild().getNavigationElement()).navigate(true);
            Editor editor = FileEditorManager.getInstance(project).getSelectedTextEditor();
            assert editor != null;
            editor.getCaretModel().moveCaretRelatively(2, 0, false, false, false);
        });
    }
}
