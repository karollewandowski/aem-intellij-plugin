package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.lexer.HtlLexerAdapter
import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.KEYWORD
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.NUMBER
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.STRING
import com.intellij.openapi.editor.XmlHighlighterColors.HTML_COMMENT
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

object HtlSyntaxHighlighter : SyntaxHighlighterBase() {

    private val ATTRIBUTES = mapOf(
            HtlTypes.EXPR_START to KEYWORD,
            HtlTypes.EXPR_END to KEYWORD,
            HtlTypes.OPTIONS_SEPARATOR to KEYWORD,
            HtlTypes.SINGLE_QUOTE to STRING,
            HtlTypes.DOUBLE_QUOTE to STRING,
            HtlTypes.STRING_CONTENT to STRING,
            HtlTypes.INTEGER_NUMBER to NUMBER,
            HtlTypes.FLOAT_NUMBER to NUMBER,
            HtlTypes.BOOLEAN_LITERAL to KEYWORD,
            HtlTypes.COMMENT_START to HTML_COMMENT,
            HtlTypes.COMMENT_CONTENT to HTML_COMMENT,
            HtlTypes.COMMENT_END to HTML_COMMENT
    )

    override fun getHighlightingLexer() = HtlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey> =
            SyntaxHighlighterBase.pack(ATTRIBUTES[tokenType])

}
