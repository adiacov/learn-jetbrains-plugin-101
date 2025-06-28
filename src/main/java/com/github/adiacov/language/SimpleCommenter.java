package com.github.adiacov.language;

import com.intellij.lang.Commenter;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.Unmodifiable;

import java.util.ArrayList;
import java.util.List;

public class SimpleCommenter implements Commenter {
    @Override
    public @Nullable String getLineCommentPrefix() {
        return "#";
    }

    @Override
    public @Unmodifiable @NotNull List<String> getLineCommentPrefixes() {
        List<String> comments = new ArrayList<>();
        comments.add("#");
        comments.add("!");
        return comments;
    }

    @Override
    public @Nullable String getBlockCommentPrefix() {
        return "";
    }

    @Override
    public @Nullable String getBlockCommentSuffix() {
        return "";
    }

    @Override
    public @Nullable String getCommentedBlockCommentPrefix() {
        return "";
    }

    @Override
    public @Nullable String getCommentedBlockCommentSuffix() {
        return "";
    }
}
