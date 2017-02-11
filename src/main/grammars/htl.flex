package co.nums.intellij.aem.htl.lexer;

import co.nums.intellij.aem.htl.psi.HtlTypes;

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
                                  return HtlTypes.HTML_FRAGMENT;
                                } else {
                                  // matched expression
                                  yypushback(2); // get back before ${
                                  yybegin(EXPRESSION);
                                  if (yylength() > 0) {
                                    return HtlTypes.HTML_FRAGMENT;
                                  }
                                }
                              }
  !([^]*"${"[^]*)             { return HtlTypes.HTML_FRAGMENT; }
}

<EXPRESSION> {
  "${"                        { return HtlTypes.EXPR_START; }
  "}"                         { yybegin(YYINITIAL); return HtlTypes.EXPR_END; }

  {WHITE_SPACE_CHAR}+         { return TokenType.WHITE_SPACE; }

  "true"                      { return HtlTypes.BOOLEAN_TRUE; }
  "false"                     { return HtlTypes.BOOLEAN_FALSE; }

  {SINGLE_QUOTED_STRING}      { return HtlTypes.SINGLE_QUOTED_STRING; }
  {DOUBLE_QUOTED_STRING}      { return HtlTypes.DOUBLE_QUOTED_STRING; }
  {INTEGER_NUMBER}            { return HtlTypes.INTEGER_NUMBER; }
  {FLOAT_NUMBER}              { return HtlTypes.FLOAT_NUMBER; }
  {IDENTIFIER}                { return HtlTypes.IDENTIFIER; }

  "("                         { return HtlTypes.LEFT_PARENTH; }
  ")"                         { return HtlTypes.RIGHT_PARENTH; }
  "["                         { return HtlTypes.LEFT_BRACKET; }
  "]"                         { return HtlTypes.RIGHT_BRACKET; }
  "."                         { return HtlTypes.DOT; }
  ","                         { return HtlTypes.COMMA; }
  "!"                         { return HtlTypes.NOT; }
  "@"                         { return HtlTypes.OPTIONS_SEPARATOR; }
  " ? "                       { return HtlTypes.TERNARY_QUESTION_OP; }
  " : "                       { return HtlTypes.TERNARY_BRANCHES_OP; }

  "&&"                        { return HtlTypes.AND; }
  "||"                        { return HtlTypes.OR; }

  "="                         { return HtlTypes.ASSIGN; }
  "=="                        { return HtlTypes.EQ; }
  "!="                        { return HtlTypes.NEQ; }
  "<"                         { return HtlTypes.LT; }
  ">"                         { return HtlTypes.GT; }
  "<="                        { return HtlTypes.LEQ; }
  ">="                        { return HtlTypes.GEQ; }
}

{WHITE_SPACE_CHAR}+           { return HtlTypes.HTML_FRAGMENT; }
[^]                           { return TokenType.BAD_CHARACTER; }
