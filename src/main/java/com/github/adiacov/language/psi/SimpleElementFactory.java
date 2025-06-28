package com.github.adiacov.language.psi;

import com.github.adiacov.language.SimpleFileType;
import com.intellij.openapi.project.Project;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFileFactory;

public class SimpleElementFactory {

    public static SimpleFile createFile(Project project, String text) {
        String name = "dummy.simple";
        return (SimpleFile) PsiFileFactory.getInstance(project).
                createFileFromText(name, SimpleFileType.INSTANCE, text);
    }

    public static SimpleProperty createProperty(Project project, String name) {
        SimpleFile file = createFile(project, name);
        return (SimpleProperty) file.getFirstChild();
    }

    public static SimpleProperty createProperty(Project project, String name, String value) {
        SimpleFile file = createFile(project, name + " = " + value);
        return (SimpleProperty) file.getFirstChild();
    }


    public static PsiElement createCRLF(Project project) {
        SimpleFile simpleFile = createFile(project, "\n");
        return simpleFile.getFirstChild();
    }
}
