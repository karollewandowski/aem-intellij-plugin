package co.nums.intellij.aem.htl.psi.patterns

import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil
import com.intellij.patterns.ObjectPattern
import com.intellij.patterns.PatternCondition
import com.intellij.psi.PsiElement
import com.intellij.util.ProcessingContext

class HtlPattern : ObjectPattern<PsiElement, HtlPattern>(PsiElement::class.java) {

    fun block(name: String): HtlPattern {
        return with(object : PatternCondition<PsiElement>("block") {
            override fun accepts(element: PsiElement, context: ProcessingContext): Boolean {
                return name == HtlPsiUtil.getOuterBlockName(element)
            }
        })
    }

}
