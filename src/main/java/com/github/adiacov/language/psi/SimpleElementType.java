package com.github.adiacov.language.psi;

import com.github.adiacov.language.SimpleLanguage;
import com.intellij.psi.tree.IElementType;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

public class SimpleElementType extends IElementType {
    public SimpleElementType(@NonNls @NotNull String debugName) {
        super(debugName, SimpleLanguage.INSTANCE);
    }
}
