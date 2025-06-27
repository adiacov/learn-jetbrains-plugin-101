package com.github.adiacov.language;

import com.github.adiacov.language.psi.SimpleProperty;
import com.intellij.navigation.ChooseByNameContributorEx;
import com.intellij.navigation.NavigationItem;
import com.intellij.openapi.project.Project;
import com.intellij.psi.search.GlobalSearchScope;
import com.intellij.util.Processor;
import com.intellij.util.containers.ContainerUtil;
import com.intellij.util.indexing.FindSymbolParameters;
import com.intellij.util.indexing.IdFilter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;
import java.util.Objects;

public class SimpleChooseByNameContributor implements ChooseByNameContributorEx {
    @Override
    public void processNames(@NotNull Processor<? super String> processor,
                             @NotNull GlobalSearchScope globalSearchScope, @Nullable IdFilter idFilter) {
        Project project = Objects.requireNonNull(globalSearchScope.getProject());
        List<String> properties = ContainerUtil.map(SimpleUtil.findProperties(project), SimpleProperty::getKey);
        ContainerUtil.process(properties, processor);
    }

    @Override
    public void processElementsWithName(@NotNull String name,
                                        @NotNull Processor<? super NavigationItem> processor,
                                        @NotNull FindSymbolParameters findSymbolParameters) {
        List<NavigationItem> properties = ContainerUtil.map(
                SimpleUtil.findProperties(findSymbolParameters.getProject(), name),
                property -> (NavigationItem) property
        );
        ContainerUtil.process(properties, processor);
    }
}
