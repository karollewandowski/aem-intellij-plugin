// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.parsing;

import com.intellij.lang.PsiBuilder;
import com.intellij.lang.PsiBuilder.Marker;
import static co.nums.intellij.aem.htl.psi.HtlTokenTypes.*;
import static com.intellij.lang.parser.GeneratedParserUtilBase.*;
import com.intellij.psi.tree.IElementType;
import com.intellij.lang.ASTNode;
import com.intellij.psi.tree.TokenSet;
import com.intellij.lang.PsiParser;
import com.intellij.lang.LightPsiParser;

@SuppressWarnings({"SimplifiableIfStatement", "UnusedAssignment"})
public class HtlParser implements PsiParser, LightPsiParser {

  public ASTNode parse(IElementType t, PsiBuilder b) {
    parseLight(t, b);
    return b.getTreeBuilt();
  }

  public void parseLight(IElementType t, PsiBuilder b) {
    boolean r;
    b = adapt_builder_(t, b, this, null);
    Marker m = enter_section_(b, 0, _COLLAPSE_, null);
    if (t == ATOM) {
      r = atom(b, 0);
    }
    else if (t == BINARY_OPERATION) {
      r = binary_operation(b, 0);
    }
    else if (t == COMPARISON_OPERATOR) {
      r = comparison_operator(b, 0);
    }
    else if (t == COMPARISON_TERM) {
      r = comparison_term(b, 0);
    }
    else if (t == EXPR_NODE) {
      r = expr_node(b, 0);
    }
    else if (t == EXPRESSION) {
      r = expression(b, 0);
    }
    else if (t == FACTOR) {
      r = factor(b, 0);
    }
    else if (t == LOGICAL_OPERATOR) {
      r = logical_operator(b, 0);
    }
    else if (t == OPTION) {
      r = option(b, 0);
    }
    else if (t == OPTION_LIST) {
      r = option_list(b, 0);
    }
    else if (t == STRING_LITERAL) {
      r = string_literal(b, 0);
    }
    else if (t == TERM) {
      r = term(b, 0);
    }
    else if (t == VALUE_LIST) {
      r = value_list(b, 0);
    }
    else {
      r = parse_root_(t, b, 0);
    }
    exit_section_(b, 0, m, t, r, true, TRUE_CONDITION);
  }

  protected boolean parse_root_(IElementType t, PsiBuilder b, int l) {
    return interpolation(b, l + 1);
  }

  /* ********************************************************** */
  // IDENTIFIER
  //        | INTEGER_NUMBER
  //        | FLOAT_NUMBER
  //        | string_literal
  //        | boolean_constant
  public static boolean atom(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "atom")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, ATOM, "<atom>");
    r = consumeToken(b, IDENTIFIER);
    if (!r) r = consumeToken(b, INTEGER_NUMBER);
    if (!r) r = consumeToken(b, FLOAT_NUMBER);
    if (!r) r = string_literal(b, l + 1);
    if (!r) r = boolean_constant(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // comparison_term (logical_operator comparison_term)*
  public static boolean binary_operation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_operation")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, BINARY_OPERATION, "<binary operation>");
    r = comparison_term(b, l + 1);
    r = r && binary_operation_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (logical_operator comparison_term)*
  private static boolean binary_operation_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_operation_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!binary_operation_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "binary_operation_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // logical_operator comparison_term
  private static boolean binary_operation_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "binary_operation_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = logical_operator(b, l + 1);
    r = r && comparison_term(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // BOOLEAN_TRUE | BOOLEAN_FALSE
  static boolean boolean_constant(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "boolean_constant")) return false;
    if (!nextTokenIs(b, "", BOOLEAN_FALSE, BOOLEAN_TRUE)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, BOOLEAN_TRUE);
    if (!r) r = consumeToken(b, BOOLEAN_FALSE);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '>'
  //                       | '<'
  //                       | '>='
  //                       | '<='
  //                       | '=='
  //                       | '!='
  public static boolean comparison_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comparison_operator")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPARISON_OPERATOR, "<comparison operator>");
    r = consumeToken(b, GT);
    if (!r) r = consumeToken(b, LT);
    if (!r) r = consumeToken(b, GEQ);
    if (!r) r = consumeToken(b, LEQ);
    if (!r) r = consumeToken(b, EQ);
    if (!r) r = consumeToken(b, NEQ);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // factor [comparison_operator factor]
  public static boolean comparison_term(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comparison_term")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, COMPARISON_TERM, "<comparison term>");
    r = factor(b, l + 1);
    r = r && comparison_term_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [comparison_operator factor]
  private static boolean comparison_term_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comparison_term_1")) return false;
    comparison_term_1_0(b, l + 1);
    return true;
  }

  // comparison_operator factor
  private static boolean comparison_term_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "comparison_term_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = comparison_operator(b, l + 1);
    r = r && factor(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // binary_operation [' ? ' binary_operation ' : ' binary_operation]
  public static boolean expr_node(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_node")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, EXPR_NODE, "<expr node>");
    r = binary_operation(b, l + 1);
    r = r && expr_node_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // [' ? ' binary_operation ' : ' binary_operation]
  private static boolean expr_node_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_node_1")) return false;
    expr_node_1_0(b, l + 1);
    return true;
  }

  // ' ? ' binary_operation ' : ' binary_operation
  private static boolean expr_node_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expr_node_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, TERNARY_QUESTION_OP);
    r = r && binary_operation(b, l + 1);
    r = r && consumeToken(b, TERNARY_BRANCHES_OP);
    r = r && binary_operation(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '${' expr_node? ['@' option_list] '}'
  public static boolean expression(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression")) return false;
    boolean r, p;
    Marker m = enter_section_(b, l, _NONE_, EXPRESSION, "<expression>");
    r = consumeToken(b, EXPR_START);
    p = r; // pin = 1
    r = r && report_error_(b, expression_1(b, l + 1));
    r = p && report_error_(b, expression_2(b, l + 1)) && r;
    r = p && consumeToken(b, EXPR_END) && r;
    exit_section_(b, l, m, r, p, expression_recover_parser_);
    return r || p;
  }

  // expr_node?
  private static boolean expression_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_1")) return false;
    expr_node(b, l + 1);
    return true;
  }

  // ['@' option_list]
  private static boolean expression_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_2")) return false;
    expression_2_0(b, l + 1);
    return true;
  }

  // '@' option_list
  private static boolean expression_2_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_2_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, OPTIONS_SEPARATOR);
    r = r && option_list(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // !('${' | text_fragment)
  static boolean expression_recover(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_recover")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NOT_);
    r = !expression_recover_0(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '${' | text_fragment
  private static boolean expression_recover_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "expression_recover_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, EXPR_START);
    if (!r) r = text_fragment(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // term
  //          | '!' term
  public static boolean factor(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "factor")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, FACTOR, "<factor>");
    r = term(b, l + 1);
    if (!r) r = factor_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // '!' term
  private static boolean factor_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "factor_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, NOT);
    r = r && term(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER
  static boolean field(PsiBuilder b, int l) {
    return consumeToken(b, IDENTIFIER);
  }

  /* ********************************************************** */
  // (expression | text_fragment)*
  static boolean interpolation(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interpolation")) return false;
    int c = current_position_(b);
    while (true) {
      if (!interpolation_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "interpolation", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // expression | text_fragment
  private static boolean interpolation_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "interpolation_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = expression(b, l + 1);
    if (!r) r = text_fragment(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // '&&'
  //                    | '||'
  public static boolean logical_operator(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "logical_operator")) return false;
    if (!nextTokenIs(b, "<logical operator>", AND, OR)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, LOGICAL_OPERATOR, "<logical operator>");
    r = consumeToken(b, AND);
    if (!r) r = consumeToken(b, OR);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // IDENTIFIER ['=' expr_node]
  public static boolean option(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, IDENTIFIER);
    r = r && option_1(b, l + 1);
    exit_section_(b, m, OPTION, r);
    return r;
  }

  // ['=' expr_node]
  private static boolean option_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_1")) return false;
    option_1_0(b, l + 1);
    return true;
  }

  // '=' expr_node
  private static boolean option_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ASSIGN);
    r = r && expr_node(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // option (',' option)*
  public static boolean option_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_list")) return false;
    if (!nextTokenIs(b, IDENTIFIER)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = option(b, l + 1);
    r = r && option_list_1(b, l + 1);
    exit_section_(b, m, OPTION_LIST, r);
    return r;
  }

  // (',' option)*
  private static boolean option_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_list_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!option_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "option_list_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' option
  private static boolean option_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "option_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && option(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // atom
  //                  | '(' expr_node ')'
  //                  | '[' value_list ']'
  //                  | '[' ']'
  static boolean simple(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = atom(b, l + 1);
    if (!r) r = simple_1(b, l + 1);
    if (!r) r = simple_2(b, l + 1);
    if (!r) r = parseTokens(b, 0, LEFT_BRACKET, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // '(' expr_node ')'
  private static boolean simple_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_PARENTH);
    r = r && expr_node(b, l + 1);
    r = r && consumeToken(b, RIGHT_PARENTH);
    exit_section_(b, m, null, r);
    return r;
  }

  // '[' value_list ']'
  private static boolean simple_2(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "simple_2")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACKET);
    r = r && value_list(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // SINGLE_QUOTED_STRING | DOUBLE_QUOTED_STRING
  public static boolean string_literal(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "string_literal")) return false;
    if (!nextTokenIs(b, "<string literal>", DOUBLE_QUOTED_STRING, SINGLE_QUOTED_STRING)) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, STRING_LITERAL, "<string literal>");
    r = consumeToken(b, SINGLE_QUOTED_STRING);
    if (!r) r = consumeToken(b, DOUBLE_QUOTED_STRING);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  /* ********************************************************** */
  // simple ('[' expr_node ']' | '.' field)*
  public static boolean term(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, TERM, "<term>");
    r = simple(b, l + 1);
    r = r && term_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // ('[' expr_node ']' | '.' field)*
  private static boolean term_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!term_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "term_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // '[' expr_node ']' | '.' field
  private static boolean term_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = term_1_0_0(b, l + 1);
    if (!r) r = term_1_0_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // '[' expr_node ']'
  private static boolean term_1_0_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term_1_0_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, LEFT_BRACKET);
    r = r && expr_node(b, l + 1);
    r = r && consumeToken(b, RIGHT_BRACKET);
    exit_section_(b, m, null, r);
    return r;
  }

  // '.' field
  private static boolean term_1_0_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "term_1_0_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, DOT);
    r = r && field(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // HTML_FRAGMENT+ | ESC_EXPR+
  static boolean text_fragment(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text_fragment")) return false;
    if (!nextTokenIs(b, "", ESC_EXPR, HTML_FRAGMENT)) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = text_fragment_0(b, l + 1);
    if (!r) r = text_fragment_1(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  // HTML_FRAGMENT+
  private static boolean text_fragment_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text_fragment_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, HTML_FRAGMENT);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, HTML_FRAGMENT)) break;
      if (!empty_element_parsed_guard_(b, "text_fragment_0", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  // ESC_EXPR+
  private static boolean text_fragment_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "text_fragment_1")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, ESC_EXPR);
    int c = current_position_(b);
    while (r) {
      if (!consumeToken(b, ESC_EXPR)) break;
      if (!empty_element_parsed_guard_(b, "text_fragment_1", c)) break;
      c = current_position_(b);
    }
    exit_section_(b, m, null, r);
    return r;
  }

  /* ********************************************************** */
  // expr_node (',' expr_node)*
  public static boolean value_list(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_list")) return false;
    boolean r;
    Marker m = enter_section_(b, l, _NONE_, VALUE_LIST, "<value list>");
    r = expr_node(b, l + 1);
    r = r && value_list_1(b, l + 1);
    exit_section_(b, l, m, r, false, null);
    return r;
  }

  // (',' expr_node)*
  private static boolean value_list_1(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_list_1")) return false;
    int c = current_position_(b);
    while (true) {
      if (!value_list_1_0(b, l + 1)) break;
      if (!empty_element_parsed_guard_(b, "value_list_1", c)) break;
      c = current_position_(b);
    }
    return true;
  }

  // ',' expr_node
  private static boolean value_list_1_0(PsiBuilder b, int l) {
    if (!recursion_guard_(b, l, "value_list_1_0")) return false;
    boolean r;
    Marker m = enter_section_(b);
    r = consumeToken(b, COMMA);
    r = r && expr_node(b, l + 1);
    exit_section_(b, m, null, r);
    return r;
  }

  final static Parser expression_recover_parser_ = new Parser() {
    public boolean parse(PsiBuilder b, int l) {
      return expression_recover(b, l + 1);
    }
  };
}
