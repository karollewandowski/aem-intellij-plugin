package co.nums.intellij.aem.htl.psi.patterns

import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil
import com.intellij.patterns.*
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext

class HtlBlockPattern(private val type: String) : PatternCondition<PsiElement>("block") {

    override fun accepts(element: PsiElement, context: ProcessingContext): Boolean {
        return type == HtlPsiUtil.getOuterBlockType(element)
    }

    companion object {
        fun htlBlock(type: String): PsiElementPattern.Capture<PsiElement> = psiElement().with(HtlBlockPattern(type))
    }

}
