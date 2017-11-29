package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.definitions.HtlGlobalObject
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
        val isGlobal = element.text in globalVariablesNames
        val color = if (isGlobal) HtlHighlighterColors.GLOBAL_OBJECT else HtlHighlighterColors.BLOCK_VARIABLE
        this.highlightText(element.textRange, color)
    }

    private fun AnnotationHolder.highlightOptionName(element: PsiElement) {
        this.highlightText(element, HtlHighlighterColors.OPTION_NAME)
        val nextLeaf = PsiTreeUtil.nextVisibleLeaf(element) ?: return
        if (nextLeaf.node.elementType == HtlTypes.ASSIGN) {
            this.highlightText(nextLeaf, HtlHighlighterColors.OPTION_NAME)
        }
    }

    private fun AnnotationHolder.highlightVariableProperty(element: PsiElement) {
        val textRange = element.textRange
        this.highlightText(textRange.startOffset + 1, textRange.endOffset, HtlHighlighterColors.PROPERTY_ACCESS)
    }

}
