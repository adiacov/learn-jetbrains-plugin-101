package com.github.adiacov.language;

import com.intellij.lang.Language;

public final class SimpleLanguage extends Language {

    public static final SimpleLanguage INSTANCE = new SimpleLanguage();

    private SimpleLanguage() {
        super("Simple");
    }
}
