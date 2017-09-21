package co.nums.intellij.aem.htl.lexer

import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.lexer.*
import com.intellij.psi.TokenType
import com.intellij.psi.tree.TokenSet

private val mergeableTokens = TokenSet.create(
        TokenType.WHITE_SPACE,
        HtlTypes.STRING_CONTENT,
        HtlTypes.OUTER_TEXT,
        HtlTypes.COMMENT_CONTENT)

class HtlLexerAdapter : MergingLexerAdapter(FlexAdapter(_HtlLexer()), mergeableTokens)
