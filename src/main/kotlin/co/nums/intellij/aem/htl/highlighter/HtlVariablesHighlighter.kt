package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.psi.HtlVariable
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

class HtlVariablesHighlighter : Annotator {

    private val globalVariableNames = HtlDefinitions.globalObjects.map { it.name }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is HtlVariable) {
            val isGlobal = globalVariableNames.contains(element.text)
            val color = if (isGlobal) HtlHighlighterColors.GLOBAL_OBJECT else HtlHighlighterColors.BLOCK_VARIABLE
            holder.highlightText(element.textRange, color)
        }
    }

}
