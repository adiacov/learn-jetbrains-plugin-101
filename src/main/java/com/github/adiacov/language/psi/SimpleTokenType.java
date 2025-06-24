package com.github.adiacov.language.psi;

import com.github.adiacov.language.SimpleLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SimpleTokenType extends IElementType {

    public SimpleTokenType(@NonNls @NotNull String debugName) {
        super(debugName, SimpleLanguage.INSTANCE);
    }

    @Override
    public String toString() {
        return "SimpleTokenType." + super.toString();
    }
}
