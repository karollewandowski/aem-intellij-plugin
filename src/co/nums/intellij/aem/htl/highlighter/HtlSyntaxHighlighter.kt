package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.parsing.HtlLexerAdapter
import co.nums.intellij.aem.htl.psi.HtlTokenTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.*
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

object HtlSyntaxHighlighter : SyntaxHighlighterBase() {

    private val ATTRIBUTES = mapOf(
            HtlTokenTypes.BOOLEAN_TRUE to KEYWORD,
            HtlTokenTypes.BOOLEAN_FALSE to KEYWORD,
            HtlTokenTypes.EXPR_START to KEYWORD,
            HtlTokenTypes.EXPR_END to KEYWORD,
            HtlTokenTypes.OPTIONS_SEPARATOR to KEYWORD,
            HtlTokenTypes.SINGLE_QUOTED_STRING to STRING,
            HtlTokenTypes.DOUBLE_QUOTED_STRING to STRING,
            HtlTokenTypes.INTEGER_NUMBER to NUMBER,
            HtlTokenTypes.FLOAT_NUMBER to NUMBER
    )

    override fun getHighlightingLexer() = HtlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey> =
            SyntaxHighlighterBase.pack(ATTRIBUTES[tokenType])

}
