package com.github.adiacov.language;

import com.github.adiacov.language.psi.SimpleProperty;
import com.intellij.ide.IconProvider;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public final class SimplePropertyIconProvider extends IconProvider {
    @Override
    public @Nullable Icon getIcon(@NotNull PsiElement psiElement, int i) {
        return psiElement instanceof SimpleProperty ? SimpleIcons.FILE : null;
    }
}
