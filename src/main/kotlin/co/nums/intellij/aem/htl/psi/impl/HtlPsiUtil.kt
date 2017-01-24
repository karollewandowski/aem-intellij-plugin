package co.nums.intellij.aem.htl.psi.impl

import co.nums.intellij.aem.htl.psi.HtlExpression
import com.intellij.lang.StdLanguages
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttributeValue

object HtlPsiUtil {

    /**
     * Returns outer HTML attribute quote if HTL expression is inside of
     * attribute.

     * @param element element from expression to check
     * *
     * @return outer HTML attribute quote or `null` if element is not
     * * enclosed by attribute
     */
    fun getOuterHtmlAttributeQuote(element: PsiElement): Char? {
        val htlExpression = PsiTreeUtil.getParentOfType(element, HtlExpression::class.java) ?: return null
        val outerAttrValue = htlExpression.getOuterXmlAttributeValue() ?: return null
        return outerAttrValue.text.getOrNull(0)
    }

    private fun HtlExpression.getOuterXmlAttributeValue(): XmlAttributeValue? {
        val offset = this.textOffset
        if (offset > 0) {
            val viewProvider = this.containingFile.viewProvider
            val previousElement = viewProvider.findElementAt(offset - 1, StdLanguages.HTML) ?: return null
            return PsiTreeUtil.getParentOfType(previousElement, XmlAttributeValue::class.java)
        }
        return null
    }

}
