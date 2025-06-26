package com.github.adiacov.language.psi.impl;

import com.github.adiacov.language.psi.SimpleNamedElement;
import com.intellij.extapi.psi.ASTWrapperPsiElement;
import com.intellij.lang.ASTNode;
import org.jetbrains.annotations.NotNull;

public abstract class SimpleNamedElementImpl extends ASTWrapperPsiElement implements SimpleNamedElement {

    public SimpleNamedElementImpl(@NotNull ASTNode node) {
        super(node);
    }

}
