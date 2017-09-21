package co.nums.intellij.aem.htl.lexer;

import co.nums.intellij.aem.htl.psi.HtlTypes;

import com.intellij.lexer.FlexLexer;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.TokenType;

%%

%{
  private boolean ternaryBranchesOparatorMatched = false;
  private boolean wsAfterTernaryBranchesOparatorMatched = false;

  public _HtlLexer() {
    this((java.io.Reader) null);
  }
%}

%class _HtlLexer
%implements FlexLexer
%function advance
%type IElementType
%unicode


WS = \s

SINGLE_QUOTED_STR_CONTENT = ([^\\']|\\([\\'\"/bfnrt]|u[a-fA-F0-9]{4}))+
DOUBLE_QUOTED_STR_CONTENT = ([^\\\"]|\\([\\'\"/bfnrt]|u[a-fA-F0-9]{4}))+

INTEGER_NUMBER = 0|[1-9]\d*
FLOAT_NUMBER = [0-9]*\.[0-9]+([eE][-+]?[0-9]+)?|[0-9]+[eE][-+]?[0-9]+
IDENTIFIER = [\p{Alpha}_][\p{Alnum}_:]*

%state EXPRESSION
%state SINGLE_QUOTED_STRING
%state DOUBLE_QUOTED_STRING
%state TERNARY_BRANCHES_OP
%state HTL_COMMENT

%%

<YYINITIAL> {
  "${"                        { yybegin(EXPRESSION); return HtlTypes.EXPR_START; }
  "\\${"                      { return HtlTypes.OUTER_TEXT; }
  "<!--/*"                    { yybegin(HTL_COMMENT); return HtlTypes.COMMENT_START; }
  [^]                         { return HtlTypes.OUTER_TEXT; }
}

<EXPRESSION> {
  "}"                         { yybegin(YYINITIAL); return HtlTypes.EXPR_END; }

  "true"                      { return HtlTypes.BOOLEAN_LITERAL; }
  "false"                     { return HtlTypes.BOOLEAN_LITERAL; }

  "'"                         { yybegin(SINGLE_QUOTED_STRING); return HtlTypes.SINGLE_QUOTE; }
  \"                          { yybegin(DOUBLE_QUOTED_STRING); return HtlTypes.DOUBLE_QUOTE; }
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
  "&&"                        { return HtlTypes.AND; }
  "||"                        { return HtlTypes.OR; }

  "="                         { return HtlTypes.ASSIGN; }
  "=="                        { return HtlTypes.EQ; }
  "!="                        { return HtlTypes.NEQ; }
  "<"                         { return HtlTypes.LT; }
  ">"                         { return HtlTypes.GT; }
  "<="                        { return HtlTypes.LEQ; }
  ">="                        { return HtlTypes.GEQ; }
  "?"                         { return HtlTypes.TERNARY_QUESTION_OP; }
  {WS}* ":"                   {
                                if (yylength() <= 2) { // ":" or "WS:" matched
                                  yypushback(yylength()); // get back before ":" or "WS:"
                                } else { // WS: with preceding spaces matched
                                  yypushback(2); // get back before "WS:"
                                  if (yylength() > 0) {
                                    return TokenType.WHITE_SPACE;
                                  }
                                }
                                yybegin(TERNARY_BRANCHES_OP);
                              }

  {WS}+                       { return TokenType.WHITE_SPACE; }

  [^]                         { yybegin(YYINITIAL); return HtlTypes.OUTER_TEXT; }
}

<SINGLE_QUOTED_STRING> {
  "'"                         { yybegin(EXPRESSION); return HtlTypes.SINGLE_QUOTE; }
  {SINGLE_QUOTED_STR_CONTENT} { return HtlTypes.STRING_CONTENT; }

  [^]                         {
                                yypushback(1);       // cancel unexpected char
                                yybegin(EXPRESSION); // and try to parse it again in <EXPRESSION>
                              }
}

<DOUBLE_QUOTED_STRING> {
  \"                          { yybegin(EXPRESSION); return HtlTypes.DOUBLE_QUOTE; }
  {DOUBLE_QUOTED_STR_CONTENT} { return HtlTypes.STRING_CONTENT; }

  [^]                         {
                                yypushback(1);       // cancel unexpected char
                                yybegin(EXPRESSION); // and try to parse it again in <EXPRESSION>
                              }
}

<TERNARY_BRANCHES_OP> {
  ":"                         {
                                ternaryBranchesOparatorMatched = true;
                                return HtlTypes.TERNARY_BRANCHES_OP;
                              }
  {WS}                        {
                                if (ternaryBranchesOparatorMatched) {
                                  yybegin(EXPRESSION);
                                  ternaryBranchesOparatorMatched = false;
                                }
                                return TokenType.WHITE_SPACE;
                              }
  [^]                         {
                                yypushback(1);       // cancel unexpected char
                                yybegin(EXPRESSION); // and try to parse it again in <EXPRESSION>
                              }
}

<HTL_COMMENT> {
  "*/-->"                     { yybegin(YYINITIAL); return HtlTypes.COMMENT_END; }
  [^]                         { return HtlTypes.COMMENT_CONTENT; }
}
