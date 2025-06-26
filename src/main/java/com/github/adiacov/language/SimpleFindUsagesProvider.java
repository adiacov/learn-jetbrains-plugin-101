package com.github.adiacov.language;

import com.github.adiacov.language.psi.SimpleProperty;
import com.github.adiacov.language.psi.SimpleTokenSets;
import com.intellij.lang.cacheBuilder.DefaultWordsScanner;
import com.intellij.lang.cacheBuilder.WordsScanner;
import com.intellij.lang.findUsages.FindUsagesProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiNamedElement;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NonNls;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class SimpleFindUsagesProvider implements FindUsagesProvider {

    @Override
    public @Nullable WordsScanner getWordsScanner() {
        return new DefaultWordsScanner(
                new SimpleLexerAdapter(),
                SimpleTokenSets.IDENTIFIERS,
                SimpleTokenSets.COMMENTS,
                TokenSet.EMPTY
        );
    }

    @Override
    public boolean canFindUsagesFor(@NotNull PsiElement psiElement) {
        return psiElement instanceof PsiNamedElement;
    }

    @Override
    public @Nullable @NonNls String getHelpId(@NotNull PsiElement psiElement) {
        return null;
    }

    @Override
    public @Nls @NotNull String getType(@NotNull PsiElement psiElement) {
        if(psiElement instanceof SimpleProperty) {
            return "simple property";
        }
        return "";
    }

    @Override
    public @Nls @NotNull String getDescriptiveName(@NotNull PsiElement psiElement) {
        if(psiElement instanceof SimpleProperty) {
            return ((SimpleProperty) psiElement).getKey();
        }
        return "";
    }

    @Override
    public @Nls @NotNull String getNodeText(@NotNull PsiElement psiElement, boolean b) {
        if (psiElement instanceof SimpleProperty) {
            return ((SimpleProperty) psiElement).getKey() +
                    SimpleAnnotator.SIMPLE_SEPARATOR_STR +
                    ((SimpleProperty) psiElement).getValue();
        }
        return "";
    }
}
