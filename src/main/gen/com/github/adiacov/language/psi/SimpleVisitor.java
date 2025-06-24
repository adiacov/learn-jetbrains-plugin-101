// This is a generated file. Not intended for manual editing.
package com.github.adiacov.language.psi;

import org.jetbrains.annotations.*;
import com.intellij.psi.PsiElementVisitor;
import com.intellij.psi.PsiElement;

public class SimpleVisitor<R> extends PsiElementVisitor {

  public R visitProperty(@NotNull SimpleProperty o) {
    return visitPsiElement(o);
  }

  public R visitPsiElement(@NotNull PsiElement o) {
    visitElement(o);
    return null;
  }

}
