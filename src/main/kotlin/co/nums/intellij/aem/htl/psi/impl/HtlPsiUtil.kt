package co.nums.intellij.aem.htl.psi.impl

import co.nums.intellij.aem.htl.psi.*
import com.intellij.lang.StdLanguages
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute

object HtlPsiUtil {

    /**
     * Returns outer HTML attribute quote if HTL expression is inside of
     * attribute.

     * @param htlElement element from expression to check
     * *
     * @return outer HTML attribute quote or `null` if element is not
     * * enclosed by attribute
     */
    fun getOuterHtmlAttributeQuote(htlElement: PsiElement): Char? {
        val htlExpression = PsiTreeUtil.getParentOfType(htlElement, HtlExpression::class.java) ?: return null
        val outerAttribute = htlExpression.getOuterXmlAttribute() ?: return null
        return outerAttribute.valueElement?.text?.getOrNull(0)
    }

    private fun HtlExpression.getOuterXmlAttribute(): XmlAttribute? {
        val offset = this.textOffset
        if (offset > 0) {
            val viewProvider = this.containingFile.viewProvider
            val previousElement = viewProvider.findElementAt(offset - 1, StdLanguages.HTML) ?: return null
            return PsiTreeUtil.getParentOfType(previousElement, XmlAttribute::class.java)
        }
        return null
    }

    /**
     * Returns outer HTL block's type that the HTL element is inside of.

     * @param htlElement element from expression to check
     * *
     * @return block's type or `null` if element is not in block
     */
    fun getOuterBlockType(htlElement: PsiElement): String? {
        val htlExpression = PsiTreeUtil.getParentOfType(htlElement, HtlExpression::class.java) ?: return null
        val outerAttribute = htlExpression.getOuterXmlAttribute() ?: return null
        return outerAttribute.localName.substringBefore(".").toLowerCase()
    }

    /**
     * Returns referenced variable identifier if property refers to variable.
     * Otherwise returns `null`.
     *
     * @param htlPropertyIdentifier identifier in property access element
     *
     * @return referenced variable identifier or `null`
     */
    fun getReferencedVariableElement(htlPropertyIdentifier: PsiElement): PsiElement? {
        val dotOrBracket = PsiTreeUtil.prevLeaf(htlPropertyIdentifier) ?: return null
        val referencedElement = PsiTreeUtil.prevLeaf(dotOrBracket) ?: return null
        if ((referencedElement as? LeafPsiElement)?.elementType === HtlTypes.IDENTIFIER && referencedElement.parent is HtlVariable) {
            return referencedElement
        }
        return null
    }

}
