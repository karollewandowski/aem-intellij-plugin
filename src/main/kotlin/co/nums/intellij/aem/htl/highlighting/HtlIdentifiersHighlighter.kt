package co.nums.intellij.aem.htl.highlighting

import co.nums.intellij.aem.htl.definitions.HtlGlobalObject
import co.nums.intellij.aem.htl.extensions.*
import co.nums.intellij.aem.htl.psi.*
import com.intellij.lang.annotation.*
import com.intellij.psi.PsiElement

class HtlIdentifiersHighlighter : Annotator {

    private val globalVariablesNames = HtlGlobalObject.values().map { it.identifier }

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        when (element) {
            is HtlVariable -> holder.highlightVariable(element)
            is HtlOptionName -> holder.highlightOptionName(element)
            is HtlDotPropertyAccess -> holder.highlightVariableProperty(element)
        }
    }

    private fun AnnotationHolder.highlightVariable(element: HtlVariable) {
        if (element.isLocalTemplateCall()) {
            highlightText(element.textRange, HtlHighlighterColors.TEMPLATE_IDENTIFIER)
        } else {
            val isGlobal = element.text in globalVariablesNames
            val color = if (isGlobal) HtlHighlighterColors.GLOBAL_OBJECT else HtlHighlighterColors.VARIABLE
            highlightText(element, color)
        }
    }

    private fun AnnotationHolder.highlightOptionName(element: PsiElement) {
        val color = if (element.isTemplateBlockParam()) HtlHighlighterColors.VARIABLE else HtlHighlighterColors.OPTION_NAME
        highlightText(element, color)
    }

    private fun AnnotationHolder.highlightVariableProperty(element: PsiElement) {
        val color = when {
            element.isTemplateReference() -> HtlHighlighterColors.TEMPLATE_IDENTIFIER
            else -> HtlHighlighterColors.PROPERTY_ACCESS
        }
        val textRange = element.textRange
        highlightText(textRange.startOffset + 1, textRange.endOffset, color)
    }

}
