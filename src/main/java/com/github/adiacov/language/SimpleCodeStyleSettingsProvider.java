package com.github.adiacov.language;

import com.intellij.application.options.CodeStyleAbstractConfigurable;
import com.intellij.application.options.CodeStyleAbstractPanel;
import com.intellij.application.options.TabbedLanguageCodeStylePanel;
import com.intellij.openapi.util.NlsContexts;
import com.intellij.psi.codeStyle.CodeStyleConfigurable;
import com.intellij.psi.codeStyle.CodeStyleSettings;
import com.intellij.psi.codeStyle.CodeStyleSettingsProvider;
import com.intellij.psi.codeStyle.CustomCodeStyleSettings;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleCodeStyleSettingsProvider extends CodeStyleSettingsProvider {

    @Override
    public @Nullable CustomCodeStyleSettings createCustomSettings(@NotNull CodeStyleSettings settings) {
        return new SimpleCodeStyleSettings(settings);
    }

    @Override
    public @Nullable @NlsContexts.ConfigurableName String getConfigurableDisplayName() {
        return SimpleLanguage.INSTANCE.getDisplayName();
    }

    @Override
    public @NotNull CodeStyleConfigurable createConfigurable(@NotNull CodeStyleSettings settings,
                                                             @NotNull CodeStyleSettings modelSettings) {
        return new CodeStyleAbstractConfigurable(settings, modelSettings, this.getConfigurableDisplayName()) {

            @Override
            protected @NotNull CodeStyleAbstractPanel createPanel(@NotNull CodeStyleSettings codeStyleSettings) {
                return new SimpleCodeStyleMainPanel(getCurrentSettings(), codeStyleSettings);
            }
        };
    }

    private static class SimpleCodeStyleMainPanel extends TabbedLanguageCodeStylePanel {

        protected SimpleCodeStyleMainPanel(CodeStyleSettings currentSettings, @NotNull CodeStyleSettings settings) {
            super(SimpleLanguage.INSTANCE, currentSettings, settings);
        }
    }
}
