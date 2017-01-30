package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.lexer.HtlLexerAdapter
import co.nums.intellij.aem.htl.psi.HtlElementTypes
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.KEYWORD
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.NUMBER
import com.intellij.openapi.editor.DefaultLanguageHighlighterColors.STRING
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.fileTypes.SyntaxHighlighterBase
import com.intellij.psi.tree.IElementType

object HtlSyntaxHighlighter : SyntaxHighlighterBase() {

    private val ATTRIBUTES = mapOf(
            HtlElementTypes.BOOLEAN_TRUE to KEYWORD,
            HtlElementTypes.BOOLEAN_FALSE to KEYWORD,
            HtlElementTypes.EXPR_START to KEYWORD,
            HtlElementTypes.EXPR_END to KEYWORD,
            HtlElementTypes.OPTIONS_SEPARATOR to KEYWORD,
            HtlElementTypes.SINGLE_QUOTED_STRING to STRING,
            HtlElementTypes.DOUBLE_QUOTED_STRING to STRING,
            HtlElementTypes.INTEGER_NUMBER to NUMBER,
            HtlElementTypes.FLOAT_NUMBER to NUMBER
    )

    override fun getHighlightingLexer() = HtlLexerAdapter()

    override fun getTokenHighlights(tokenType: IElementType): Array<out TextAttributesKey> =
            SyntaxHighlighterBase.pack(ATTRIBUTES[tokenType])

}
