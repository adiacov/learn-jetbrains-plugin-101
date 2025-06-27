package com.github.adiacov.language;

import com.github.adiacov.language.psi.SimpleFile;
import com.github.adiacov.language.psi.SimpleProperty;
import com.intellij.icons.AllIcons;
import com.intellij.ide.navigationToolbar.StructureAwareNavBarModelExtension;
import com.intellij.lang.Language;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import javax.swing.*;

public final class SimpleStructureAwareNavbar extends StructureAwareNavBarModelExtension {
    @Override
    protected @NotNull Language getLanguage() {
        return SimpleLanguage.INSTANCE;
    }

    @Override
    public @Nullable String getPresentableText(Object o) {
        if (o instanceof SimpleFile) {
            return ((SimpleFile) o).getName();
        }
        if (o instanceof SimpleProperty) {
            return ((SimpleProperty) o).getName();
        }
        return null;
    }

    @Override
    public @Nullable Icon getIcon(Object object) {
        if (object instanceof SimpleProperty) {
            return AllIcons.Nodes.Property;
        }

        return null;
    }
}
