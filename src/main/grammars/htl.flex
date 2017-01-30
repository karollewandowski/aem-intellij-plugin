package co.nums.intellij.aem.htl.lexer;

import co.nums.intellij.aem.htl.psi.HtlElementTypes;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

%%

%class HtlLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode


WHITE_SPACE_CHAR = [\ \n\r\t\f]

SINGLE_QUOTED_STRING = '([^\\'\r\n]|\\([\\'/bfnrt]|u[a-fA-F0-9]{4}))*'?
DOUBLE_QUOTED_STRING = \"([^\\\"\r\n]|\\([\\\"/bfnrt]|u[a-fA-F0-9]{4}))*\"?

INTEGER_NUMBER = 0|[1-9]\d*
FLOAT_NUMBER = [0-9]*\.[0-9]+([eE][-+]?[0-9]+)?|[0-9]+[eE][-+]?[0-9]+
IDENTIFIER = [\p{Alpha}_][\p{Alnum}_:]*


%state EXPRESSION

%%

<YYINITIAL> {
  ~"${"                       {
                                if (yylength() >= 3 && yytext().toString().substring(yylength() - 3, yylength()).equals("\\${")) {
                                  // escaped expression
                                  return HtlElementTypes.HTML_FRAGMENT;
                                } else {
                                  // matched expression
                                  yypushback(2); // get back before ${
                                  yybegin(EXPRESSION);
                                  if (yylength() > 0) {
                                    return HtlElementTypes.HTML_FRAGMENT;
                                  }
                                }
                              }
  !([^]*"${"[^]*)             { return HtlElementTypes.HTML_FRAGMENT; }
}

<EXPRESSION> {
  "${"                        { return HtlElementTypes.EXPR_START; }
  "}"                         { yybegin(YYINITIAL); return HtlElementTypes.EXPR_END; }

  {WHITE_SPACE_CHAR}+         { return TokenType.WHITE_SPACE; }

  "true"                      { return HtlElementTypes.BOOLEAN_TRUE; }
  "false"                     { return HtlElementTypes.BOOLEAN_FALSE; }

  {SINGLE_QUOTED_STRING}      { return HtlElementTypes.SINGLE_QUOTED_STRING; }
  {DOUBLE_QUOTED_STRING}      { return HtlElementTypes.DOUBLE_QUOTED_STRING; }
  {INTEGER_NUMBER}            { return HtlElementTypes.INTEGER_NUMBER; }
  {FLOAT_NUMBER}              { return HtlElementTypes.FLOAT_NUMBER; }
  {IDENTIFIER}                { return HtlElementTypes.IDENTIFIER; }

  "("                         { return HtlElementTypes.LEFT_PARENTH; }
  ")"                         { return HtlElementTypes.RIGHT_PARENTH; }
  "["                         { return HtlElementTypes.LEFT_BRACKET; }
  "]"                         { return HtlElementTypes.RIGHT_BRACKET; }
  "."                         { return HtlElementTypes.DOT; }
  ","                         { return HtlElementTypes.COMMA; }
  "!"                         { return HtlElementTypes.NOT; }
  "@"                         { return HtlElementTypes.OPTIONS_SEPARATOR; }
  " ? "                       { return HtlElementTypes.TERNARY_QUESTION_OP; }
  " : "                       { return HtlElementTypes.TERNARY_BRANCHES_OP; }

  "&&"                        { return HtlElementTypes.AND; }
  "||"                        { return HtlElementTypes.OR; }

  "="                         { return HtlElementTypes.ASSIGN; }
  "=="                        { return HtlElementTypes.EQ; }
  "!="                        { return HtlElementTypes.NEQ; }
  "<"                         { return HtlElementTypes.LT; }
  ">"                         { return HtlElementTypes.GT; }
  "<="                        { return HtlElementTypes.LEQ; }
  ">="                        { return HtlElementTypes.GEQ; }
}

{WHITE_SPACE_CHAR}+           { return HtlElementTypes.HTML_FRAGMENT; }
[^]                           { return TokenType.BAD_CHARACTER; }
