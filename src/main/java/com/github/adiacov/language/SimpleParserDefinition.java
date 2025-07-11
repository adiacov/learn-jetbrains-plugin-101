package com.github.adiacov.language;

import com.github.adiacov.language.parser.SimpleParser;
import com.github.adiacov.language.psi.SimpleFile;
import com.github.adiacov.language.psi.SimpleTokenSets;
import com.github.adiacov.language.psi.SimpleTypes;
import com.intellij.lang.ASTNode;
import com.intellij.lang.ParserDefinition;
import com.intellij.lang.PsiParser;
import com.intellij.lexer.Lexer;
import com.intellij.openapi.project.Project;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.tree.IFileElementType;
import com.intellij.psi.tree.TokenSet;
import org.jetbrains.annotations.NotNull;

public final class SimpleParserDefinition implements ParserDefinition {

    public static final IFileElementType FILE = new IFileElementType(SimpleLanguage.INSTANCE);

    @Override
    public @NotNull Lexer createLexer(Project project) {
        return new SimpleLexerAdapter();
    }

    @Override
    public @NotNull PsiParser createParser(Project project) {
        return new SimpleParser();
    }

    @Override
    public @NotNull IFileElementType getFileNodeType() {
        return FILE;
    }

    @Override
    public @NotNull TokenSet getCommentTokens() {
        return SimpleTokenSets.COMMENTS;
    }

    @Override
    public @NotNull TokenSet getStringLiteralElements() {
        return TokenSet.EMPTY;
    }

    @Override
    public @NotNull PsiElement createElement(ASTNode astNode) {
        return SimpleTypes.Factory.createElement(astNode);
    }

    @Override
    public @NotNull PsiFile createFile(@NotNull FileViewProvider fileViewProvider) {
        return new SimpleFile(fileViewProvider);
    }
}
