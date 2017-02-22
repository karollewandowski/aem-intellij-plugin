package co.nums.intellij.aem.htl.lexer

import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.lexer.FlexAdapter
import com.intellij.lexer.MergingLexerAdapter
import com.intellij.psi.tree.TokenSet

class HtlLexerAdapter : MergingLexerAdapter(FlexAdapter(_HtlLexer()), TokenSet.create(HtlTypes.HTML_FRAGMENT))
