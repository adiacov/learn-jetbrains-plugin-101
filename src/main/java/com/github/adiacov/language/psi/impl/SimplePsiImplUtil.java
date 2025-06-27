package com.github.adiacov.language.psi.impl;

import com.github.adiacov.language.psi.SimpleElementFactory;
import com.github.adiacov.language.psi.SimpleProperty;
import com.github.adiacov.language.psi.SimpleTypes;
import com.intellij.lang.ASTNode;
import com.intellij.navigation.ItemPresentation;
import com.intellij.openapi.util.NlsSafe;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public class SimplePsiImplUtil {

    public static String getKey(SimpleProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
        if (keyNode != null) {
            return keyNode.getText().replaceAll("\\\\ ", " ");
        } else {
            return null;
        }
    }

    public static String getValue(SimpleProperty element) {
        ASTNode valueNode = element.getNode().findChildByType(SimpleTypes.VALUE);
        if (valueNode != null) {
            return valueNode.getText();
        } else {
            return "";
        }
    }

    public static String getName(SimpleProperty element) {
        return getKey(element);
    }

    public static PsiElement setName(SimpleProperty element, String newName) {
        ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
        if (keyNode != null) {
            SimpleProperty property =
                    SimpleElementFactory.createProperty(element.getProject(), newName);
            ASTNode newKeyNode = property.getFirstChild().getNode();
            element.getNode().replaceChild(keyNode, newKeyNode);
        }
        return element;
    }

    public static PsiElement getNameIdentifier(SimpleProperty element) {
        ASTNode keyNode = element.getNode().findChildByType(SimpleTypes.KEY);
        return keyNode != null ? keyNode.getPsi() : null;
    }

    public static ItemPresentation getPresentation(SimpleProperty element) {
        return new ItemPresentation() {
            @Override
            public @NlsSafe @Nullable String getPresentableText() {
                return element.getKey();
            }

            @Override
            public @NlsSafe @Nullable String getLocationString() {
                PsiFile file = element.getContainingFile();
                return file == null ? null : file.getName();
            }

            @Override
            public @Nullable Icon getIcon(boolean b) {
                return element.getIcon(0);
            }
        };
    }
}
