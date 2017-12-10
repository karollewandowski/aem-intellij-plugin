package co.nums.intellij.aem.htl.highlighting

import co.nums.intellij.aem.htl.definitions.HtlGlobalObject
import co.nums.intellij.aem.htl.extensions.*
import co.nums.intellij.aem.htl.psi.*
import com.intellij.lang.annotation.*
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

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
        if (element.isTemplateBlockParam()) {
            highlightText(element, HtlHighlighterColors.VARIABLE)
        } else {
            highlightOption(element)
        }
    }

    private fun AnnotationHolder.highlightOption(element: PsiElement) {
        val nextLeaf = PsiTreeUtil.nextVisibleLeaf(element)
        val highlightEndOffset = if (nextLeaf != null && nextLeaf.node.elementType == HtlTypes.ASSIGN) {
            nextLeaf.textRange.endOffset
        } else {
            element.textRange.endOffset
        }
        highlightText(element.textRange.startOffset, highlightEndOffset, HtlHighlighterColors.OPTION_NAME)
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
