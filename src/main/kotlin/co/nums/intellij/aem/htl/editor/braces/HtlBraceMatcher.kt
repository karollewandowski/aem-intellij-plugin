package co.nums.intellij.aem.htl.editor.braces

import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

private const val STRUCTURAL = true

class HtlBraceMatcher : PairedBraceMatcher {

    private val PAIRS = arrayOf(
            BracePair(HtlTypes.EXPR_START, HtlTypes.EXPR_END, !STRUCTURAL),
            BracePair(HtlTypes.LEFT_BRACKET, HtlTypes.RIGHT_BRACKET, !STRUCTURAL),
            BracePair(HtlTypes.LEFT_PARENTH, HtlTypes.RIGHT_PARENTH, !STRUCTURAL)
    )

    override fun getPairs() = PAIRS

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int) = openingBraceOffset

}
