package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.psi.HtlVariable
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

class HtlVariablesHighlighter : Annotator {

    private val NO_MESSAGE = null
    private val globalVariableNames = HtlDefinitions.globalObjects.map { it.name }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is HtlVariable) {
            val annotation = holder.createInfoAnnotation(element.textRange, NO_MESSAGE)
            if (globalVariableNames.contains(element.text)) {
                annotation.textAttributes = HtlHighlighterColors.GLOBAL_VARIABLE
            } else {
                annotation.textAttributes = HtlHighlighterColors.BLOCK_VARIABLE
            }
            // TODO (when references will be ready): make unresolved variables red
        }
    }

}
