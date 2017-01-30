package co.nums.intellij.aem.htl.editor.braces

import co.nums.intellij.aem.htl.psi.HtlElementTypes
import com.intellij.lang.BracePair
import com.intellij.lang.PairedBraceMatcher
import com.intellij.psi.PsiFile
import com.intellij.psi.tree.IElementType

private const val STRUCTURAL = true

class HtlBraceMatcher : PairedBraceMatcher {

    private val PAIRS = arrayOf(
            BracePair(HtlElementTypes.LEFT_BRACKET, HtlElementTypes.RIGHT_BRACKET, !STRUCTURAL),
            BracePair(HtlElementTypes.LEFT_PARENTH, HtlElementTypes.RIGHT_PARENTH, !STRUCTURAL)
    )

    override fun getPairs() = PAIRS

    override fun isPairedBracesAllowedBeforeType(lbraceType: IElementType, contextType: IElementType?) = true

    override fun getCodeConstructStart(file: PsiFile, openingBraceOffset: Int) = openingBraceOffset

}
