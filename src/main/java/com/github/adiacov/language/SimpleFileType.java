package com.github.adiacov.language;

import com.intellij.lang.Language;
import com.intellij.openapi.fileTypes.LanguageFileType;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.openapi.util.NlsSafe;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;

import javax.swing.*;

public final class SimpleFileType extends LanguageFileType {

    public static final SimpleFileType INSTANCE = new SimpleFileType();

    private SimpleFileType() {
        super(SimpleLanguage.INSTANCE);
    }

    @Override
    public @NonNls @NotNull String getName() {
        return "Simple File";
    }

    @Override
    public @NlsContexts.Label @NotNull String getDescription() {
        return "Simple language file";
    }

    @Override
    public @NlsSafe @NotNull String getDefaultExtension() {
        return "simple";
    }

    @Override
    public Icon getIcon() {
        return SimpleIcons.FILE;
    }
}
