package co.nums.intellij.aem.htl.lexer

import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.MergingLexerAdapter
import com.intellij.psi.tree.TokenSet

private val MERGEABLE_TOKENS = TokenSet.create(HtlTypes.OUTER_TEXT, HtlTypes.COMMENT_CONTENT)

class HtlLexerAdapter : MergingLexerAdapter(FlexAdapter(_HtlLexer()), MERGEABLE_TOKENS)
