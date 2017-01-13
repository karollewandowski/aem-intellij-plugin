// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi;

import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import co.nums.intellij.aem.htl.psi.impl.*;

public interface HtlTokenTypes {

  IElementType ATOM = new HtlElementType("ATOM");
  IElementType BINARY_OPERATION = new HtlElementType("BINARY_OPERATION");
  IElementType COMPARISON_OPERATOR = new HtlElementType("COMPARISON_OPERATOR");
  IElementType COMPARISON_TERM = new HtlElementType("COMPARISON_TERM");
  IElementType EXPRESSION = new HtlElementType("EXPRESSION");
  IElementType EXPR_NODE = new HtlElementType("EXPR_NODE");
  IElementType FACTOR = new HtlElementType("FACTOR");
  IElementType LOGICAL_OPERATOR = new HtlElementType("LOGICAL_OPERATOR");
  IElementType OPTION = new HtlElementType("OPTION");
  IElementType OPTION_LIST = new HtlElementType("OPTION_LIST");
  IElementType STRING_LITERAL = new HtlElementType("STRING_LITERAL");
  IElementType TERM = new HtlElementType("TERM");
  IElementType VALUE_LIST = new HtlElementType("VALUE_LIST");

  IElementType AND = new HtlTokenType("&&");
  IElementType ASSIGN = new HtlTokenType("=");
  IElementType BOOLEAN_FALSE = new HtlTokenType("false");
  IElementType BOOLEAN_TRUE = new HtlTokenType("true");
  IElementType COMMA = new HtlTokenType(",");
  IElementType DOT = new HtlTokenType(".");
  IElementType DOUBLE_QUOTED_STRING = new HtlTokenType("DOUBLE_QUOTED_STRING");
  IElementType EQ = new HtlTokenType("==");
  IElementType ESC_EXPR = new HtlTokenType("ESC_EXPR");
  IElementType EXPR_END = new HtlTokenType("}");
  IElementType EXPR_START = new HtlTokenType("${");
  IElementType FLOAT_NUMBER = new HtlTokenType("FLOAT_NUMBER");
  IElementType GEQ = new HtlTokenType(">=");
  IElementType GT = new HtlTokenType(">");
  IElementType HTML_FRAGMENT = new HtlTokenType("HTML_FRAGMENT");
  IElementType IDENTIFIER = new HtlTokenType("IDENTIFIER");
  IElementType INTEGER_NUMBER = new HtlTokenType("INTEGER_NUMBER");
  IElementType LEFT_BRACKET = new HtlTokenType("[");
  IElementType LEFT_PARENTH = new HtlTokenType("(");
  IElementType LEQ = new HtlTokenType("<=");
  IElementType LT = new HtlTokenType("<");
  IElementType NEQ = new HtlTokenType("!=");
  IElementType NOT = new HtlTokenType("!");
  IElementType OPTIONS_SEPARATOR = new HtlTokenType("@");
  IElementType OR = new HtlTokenType("||");
  IElementType RIGHT_BRACKET = new HtlTokenType("]");
  IElementType RIGHT_PARENTH = new HtlTokenType(")");
  IElementType SINGLE_QUOTED_STRING = new HtlTokenType("SINGLE_QUOTED_STRING");
  IElementType TERNARY_BRANCHES_OP = new HtlTokenType(" : ");
  IElementType TERNARY_QUESTION_OP = new HtlTokenType(" ? ");

  class Factory {
    public static PsiElement createElement(ASTNode node) {
      IElementType type = node.getElementType();
       if (type == ATOM) {
        return new HtlAtomImpl(node);
      }
      else if (type == BINARY_OPERATION) {
        return new HtlBinaryOperationImpl(node);
      }
      else if (type == COMPARISON_OPERATOR) {
        return new HtlComparisonOperatorImpl(node);
      }
      else if (type == COMPARISON_TERM) {
        return new HtlComparisonTermImpl(node);
      }
      else if (type == EXPRESSION) {
        return new HtlExpressionImpl(node);
      }
      else if (type == EXPR_NODE) {
        return new HtlExprNodeImpl(node);
      }
      else if (type == FACTOR) {
        return new HtlFactorImpl(node);
      }
      else if (type == LOGICAL_OPERATOR) {
        return new HtlLogicalOperatorImpl(node);
      }
      else if (type == OPTION) {
        return new HtlOptionImpl(node);
      }
      else if (type == OPTION_LIST) {
        return new HtlOptionListImpl(node);
      }
      else if (type == STRING_LITERAL) {
        return new HtlStringLiteralImpl(node);
      }
      else if (type == TERM) {
        return new HtlTermImpl(node);
      }
      else if (type == VALUE_LIST) {
        return new HtlValueListImpl(node);
      }
      throw new AssertionError("Unknown element type: " + type);
    }
  }
}
