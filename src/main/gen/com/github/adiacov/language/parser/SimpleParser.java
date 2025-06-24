// This is a generated file. Not intended for manual editing.
package com.github.adiacov.language.parser;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static com.github.adiacov.language.psi.SimpleTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class SimpleParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType type, PsiBuilder builder) {
    parseLight(type, builder);
    return builder.getTreeBuilt();
  }

  public void parseLight(IElementType type, PsiBuilder builder) {
    boolean result;
    builder = adapt_builder_(type, builder, this, null);
    Marker marker = enter_section_(builder, 0, _COLLAPSE_, null);
    result = parse_root_(type, builder);
    exit_section_(builder, 0, marker, type, result, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType type, PsiBuilder builder) {
    return parse_root_(type, builder, 0);
  }

  static boolean parse_root_(IElementType type, PsiBuilder builder, int level) {
    return simpleFile(builder, level + 1);
  }

  /* ********************************************************** */
  // property|COMMENT|CRLF
  static boolean item_(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "item_")) return false;
    boolean result;
    result = property(builder, level + 1);
    if (!result) result = consumeToken(builder, COMMENT);
    if (!result) result = consumeToken(builder, CRLF);
    return result;
  }

  /* ********************************************************** */
  // (KEY? SEPARATOR VALUE?) | KEY
  public static boolean property(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "property")) return false;
    if (!nextTokenIs(builder, "<property>", KEY, SEPARATOR)) return false;
    boolean result;
    Marker marker = enter_section_(builder, level, _NONE_, PROPERTY, "<property>");
    result = property_0(builder, level + 1);
    if (!result) result = consumeToken(builder, KEY);
    exit_section_(builder, level, marker, result, false, null);
    return result;
  }

  // KEY? SEPARATOR VALUE?
  private static boolean property_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "property_0")) return false;
    boolean result;
    Marker marker = enter_section_(builder);
    result = property_0_0(builder, level + 1);
    result = result && consumeToken(builder, SEPARATOR);
    result = result && property_0_2(builder, level + 1);
    exit_section_(builder, marker, null, result);
    return result;
  }

  // KEY?
  private static boolean property_0_0(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "property_0_0")) return false;
    consumeToken(builder, KEY);
    return true;
  }

  // VALUE?
  private static boolean property_0_2(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "property_0_2")) return false;
    consumeToken(builder, VALUE);
    return true;
  }

  /* ********************************************************** */
  // item_*
  static boolean simpleFile(PsiBuilder builder, int level) {
    if (!recursion_guard_(builder, level, "simpleFile")) return false;
    while (true) {
      int pos = current_position_(builder);
      if (!item_(builder, level + 1)) break;
      if (!empty_element_parsed_guard_(builder, "simpleFile", pos)) break;
    }
    return true;
  }

}
