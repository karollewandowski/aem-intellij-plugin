package co.nums.intellij.aem.htl.highlighting

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.definitions.HtlBlock
import co.nums.intellij.aem.htl.extensions.*
import co.nums.intellij.aem.htl.psi.HtlStringLiteral
import com.intellij.lang.annotation.*
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.*

class HtlUseDeclarationsHighlighter : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.containingFile.isHtl() && element is XmlAttribute
                && element.name.startsWith(HtlBlock.USE.type, ignoreCase = true)) {
            highlightUseDeclaration(holder, element.valueElement)
        }
    }

    private fun highlightUseDeclaration(holder: AnnotationHolder, attributeValue: XmlAttributeValue?) {
        val valueStart = attributeValue?.textOffset ?: return
        val htlFile = attributeValue.containingFile.viewProvider.getPsi(HtlLanguage) ?: return
        val htlExpressionStart = htlFile.findElementAt(valueStart) ?: return
        val elementToHighlight = findElementToHighlight(htlExpressionStart, attributeValue)
        if (elementToHighlight != null) {
            holder.highlightText(
                    elementToHighlight.textRange.startOffset + 1,
                    elementToHighlight.textRange.endOffset - 1,
                    HtlHighlighterColors.USE_DECLARATION)
        }
    }

    private fun findElementToHighlight(htlExpressionStart: PsiElement, attributeValue: XmlAttributeValue?): PsiElement? {
        if (htlExpressionStart.isHtlExpressionToken()) {
            val htlExpression = htlExpressionStart.parent ?: return null
            return PsiTreeUtil.findChildOfType(htlExpression, HtlStringLiteral::class.java)
        } else {
            return attributeValue
        }
    }

}
