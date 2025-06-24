package com.github.adiacov.language.psi;

import com.github.adiacov.language.SimpleFileType;
import com.github.adiacov.language.SimpleLanguage;
import com.intellij.extapi.psi.PsiFileBase;
import com.intellij.openapi.fileTypes.FileType;
import com.intellij.psi.FileViewProvider;
import org.jetbrains.annotations.NotNull;

public class SimpleFile extends PsiFileBase {

    public SimpleFile(@NotNull FileViewProvider viewProvider) {
        super(viewProvider, SimpleLanguage.INSTANCE);
    }

    @Override
    public @NotNull FileType getFileType() {
        return SimpleFileType.INSTANCE;
    }

    @Override
    public String toString() {
        return "Simple File";
    }
}
