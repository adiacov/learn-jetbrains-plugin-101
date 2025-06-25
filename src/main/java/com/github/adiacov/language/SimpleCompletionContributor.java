package com.github.adiacov.language;

import com.github.adiacov.language.psi.SimpleTypes;
import com.intellij.codeInsight.completion.*;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.patterns.PlatformPatterns;
import com.intellij.util.ProcessingContext;
import org.jetbrains.annotations.NotNull;

import java.util.Arrays;

final class SimpleCompletionContributor extends CompletionContributor {

    SimpleCompletionContributor() {
        extend(CompletionType.BASIC, PlatformPatterns.psiElement(SimpleTypes.VALUE),
                new CompletionProvider<>() {
                    public void addCompletions(@NotNull CompletionParameters parameters,
                                               @NotNull ProcessingContext context,
                                               @NotNull CompletionResultSet resultSet) {
                        resultSet.addElement(LookupElementBuilder.create("Hello"));
                        resultSet.addAllElements(
                                Arrays.asList(
                                        LookupElementBuilder.create("Howdy"),
                                        LookupElementBuilder.create("Hi There")
                                )
                        );
                    }
                }
        );
    }

}
