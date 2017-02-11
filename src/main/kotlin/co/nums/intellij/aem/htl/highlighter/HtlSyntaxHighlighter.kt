package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.lexer.HtlLexerAdapter
import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.KEYWORD
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.NUMBER
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.STRING
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

object HtlSyntaxHighlighter : SyntaxHighlighterBase() {

    private val ATTRIBUTES = mapOf(
            HtlTypes.BOOLEAN_TRUE to KEYWORD,
            HtlTypes.BOOLEAN_FALSE to KEYWORD,
            HtlTypes.EXPR_START to KEYWORD,
            HtlTypes.EXPR_END to KEYWORD,
            HtlTypes.OPTIONS_SEPARATOR to KEYWORD,
            HtlTypes.SINGLE_QUOTED_STRING to STRING,
            HtlTypes.DOUBLE_QUOTED_STRING to STRING,
            HtlTypes.INTEGER_NUMBER to NUMBER,
            HtlTypes.FLOAT_NUMBER to NUMBER
    )

    override fun getHighlightingLexer() = HtlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey> =
            SyntaxHighlighterBase.pack(ATTRIBUTES[tokenType])

}
