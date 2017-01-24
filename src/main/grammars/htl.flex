package co.nums.intellij.aem.htl.lexer;

import co.nums.intellij.aem.htl.psi.HtlTokenTypes;

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
                                  return HtlTokenTypes.HTML_FRAGMENT;
                                } else {
                                  // matched expression
                                  yypushback(2); // get back before ${
                                  yybegin(EXPRESSION);
                                  if (yylength() > 0) {
                                    return HtlTokenTypes.HTML_FRAGMENT;
                                  }
                                }
                              }
  !([^]*"${"[^]*)             { return HtlTokenTypes.HTML_FRAGMENT; }
}

<EXPRESSION> {
  "${"                        { return HtlTokenTypes.EXPR_START; }
  "}"                         { yybegin(YYINITIAL); return HtlTokenTypes.EXPR_END; }

  {WHITE_SPACE_CHAR}+         { return TokenType.WHITE_SPACE; }

  "true"                      { return HtlTokenTypes.BOOLEAN_TRUE; }
  "false"                     { return HtlTokenTypes.BOOLEAN_FALSE; }

  {SINGLE_QUOTED_STRING}      { return HtlTokenTypes.SINGLE_QUOTED_STRING; }
  {DOUBLE_QUOTED_STRING}      { return HtlTokenTypes.DOUBLE_QUOTED_STRING; }
  {INTEGER_NUMBER}            { return HtlTokenTypes.INTEGER_NUMBER; }
  {FLOAT_NUMBER}              { return HtlTokenTypes.FLOAT_NUMBER; }
  {IDENTIFIER}                { return HtlTokenTypes.IDENTIFIER; }

  "("                         { return HtlTokenTypes.LEFT_PARENTH; }
  ")"                         { return HtlTokenTypes.RIGHT_PARENTH; }
  "["                         { return HtlTokenTypes.LEFT_BRACKET; }
  "]"                         { return HtlTokenTypes.RIGHT_BRACKET; }
  "."                         { return HtlTokenTypes.DOT; }
  ","                         { return HtlTokenTypes.COMMA; }
  "!"                         { return HtlTokenTypes.NOT; }
  "@"                         { return HtlTokenTypes.OPTIONS_SEPARATOR; }
  " ? "                       { return HtlTokenTypes.TERNARY_QUESTION_OP; }
  " : "                       { return HtlTokenTypes.TERNARY_BRANCHES_OP; }

  "&&"                        { return HtlTokenTypes.AND; }
  "||"                        { return HtlTokenTypes.OR; }

  "="                         { return HtlTokenTypes.ASSIGN; }
  "=="                        { return HtlTokenTypes.EQ; }
  "!="                        { return HtlTokenTypes.NEQ; }
  "<"                         { return HtlTokenTypes.LT; }
  ">"                         { return HtlTokenTypes.GT; }
  "<="                        { return HtlTokenTypes.LEQ; }
  ">="                        { return HtlTokenTypes.GEQ; }
}

{WHITE_SPACE_CHAR}+           { return HtlTokenTypes.HTML_FRAGMENT; }
[^]                           { return TokenType.BAD_CHARACTER; }
