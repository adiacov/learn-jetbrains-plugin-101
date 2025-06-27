package com.github.adiacov.language;

import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;

public class SimpleCodeStyleSettings extends CustomCodeStyleSettings {
    protected SimpleCodeStyleSettings(@NotNull CodeStyleSettings container) {
        super("SimpleCodeStyleSettings", container);
    }
}
