// This is a generated file. Not intended for manual editing.
package co.nums.intellij.aem.htl.psi;

import co.nums.intellij.aem.htl.psi.impl.HtlAtomImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlBinaryOpImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlComparisonOpImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlComparisonTermImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlExpressionImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlFactorImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlFieldImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlOperatorImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlOptionImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlOptionListImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlSimpleImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlTermImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlValueListImpl;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.PsiElement;
import com.intellij.lang.ASTNode;
import co.nums.intellij.aem.htl.psi.impl.HtlExprNodeImpl;
import co.nums.intellij.aem.htl.psi.impl.HtlStringLiteralImpl;

public interface HtlTokenTypes {

  IElementType ATOM = new HtlElementType("ATOM");
  IElementType BINARY_OP = new HtlElementType("BINARY_OP");
  IElementType COMPARISON_OP = new HtlElementType("COMPARISON_OP");
  IElementType COMPARISON_TERM = new HtlElementType("COMPARISON_TERM");
  IElementType EXPRESSION = new HtlElementType("EXPRESSION");
  IElementType EXPR_NODE = new HtlElementType("EXPR_NODE");
  IElementType FACTOR = new HtlElementType("FACTOR");
  IElementType FIELD = new HtlElementType("FIELD");
  IElementType OPERATOR = new HtlElementType("OPERATOR");
  IElementType OPTION = new HtlElementType("OPTION");
  IElementType OPTION_LIST = new HtlElementType("OPTION_LIST");
  IElementType SIMPLE = new HtlElementType("SIMPLE");
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
  IElementType HTL_FRAGMENT = new HtlTokenType("HTL_FRAGMENT");
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
      else if (type == BINARY_OP) {
        return new HtlBinaryOpImpl(node);
      }
      else if (type == COMPARISON_OP) {
        return new HtlComparisonOpImpl(node);
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
      else if (type == FIELD) {
        return new HtlFieldImpl(node);
      }
      else if (type == OPERATOR) {
        return new HtlOperatorImpl(node);
      }
      else if (type == OPTION) {
        return new HtlOptionImpl(node);
      }
      else if (type == OPTION_LIST) {
        return new HtlOptionListImpl(node);
      }
      else if (type == SIMPLE) {
        return new HtlSimpleImpl(node);
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
