package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.definitions.HtlGlobalObject
import co.nums.intellij.aem.htl.psi.HtlVariable
import com.intellij.lang.annotation.*
import com.intellij.psi.PsiElement

class HtlVariablesHighlighter : Annotator {

    private val globalVariableNames = HtlGlobalObject.values().map { it.identifier }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is HtlVariable) {
            val isGlobal = element.text in globalVariableNames
            val color = if (isGlobal) HtlHighlighterColors.GLOBAL_OBJECT else HtlHighlighterColors.BLOCK_VARIABLE
            holder.highlightText(element.textRange, color)
        }
    }

}
