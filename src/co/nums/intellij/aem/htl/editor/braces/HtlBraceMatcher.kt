package co.nums.intellij.aem.htl.editor.braces

import co.nums.intellij.aem.htl.psi.HtlTokenTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

class HtlBraceMatcher : PairedBraceMatcher {

    private val PAIRS = arrayOf(
            BracePair(HtlTokenTypes.LEFT_BRACKET, HtlTokenTypes.RIGHT_BRACKET, false),
            BracePair(HtlTokenTypes.LEFT_PARENTH, HtlTokenTypes.RIGHT_PARENTH, false)
    )

    override fun getPairs() = PAIRS

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int) = openingBraceOffset

}
