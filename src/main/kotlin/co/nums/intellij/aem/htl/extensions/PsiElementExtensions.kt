package co.nums.intellij.aem.htl.extensions

import co.nums.intellij.aem.htl.definitions.*
import co.nums.intellij.aem.htl.psi.*
import com.intellij.lang.StdLanguages
import com.intellij.psi.*
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.*

/**
 * Returns outer HTML attribute quote if HTL expression is inside of
 * attribute.
 *
 * @return outer HTML attribute quote or `null` if element is not enclosed by attribute
 */
fun PsiElement.getOuterHtmlAttributeQuote(): Char? {
    val htlExpression = PsiTreeUtil.getParentOfType(this, HtlExpression::class.java) ?: return null
    val outerAttribute = htlExpression.getOuterXmlAttribute() ?: return null
    return outerAttribute.valueElement?.text?.getOrNull(0)
}

private fun HtlExpression.getOuterXmlAttribute(): XmlAttribute? {
    val offset = this.textOffset
    if (offset > 0) {
        val previousElement = containingFile.viewProvider.findElementAt(offset - 1, StdLanguages.HTML) ?: return null
        return PsiTreeUtil.getParentOfType(previousElement, XmlAttribute::class.java)
    }
    return null
}

/**
 * Returns outer HTL block's type that the HTL element is inside of.
 *
 * @return block's type or `null` if element is not in block
 */
fun PsiElement.getOuterBlockType(): String? {
    val htlExpression = PsiTreeUtil.getParentOfType(this, HtlExpression::class.java) ?: return null
    val outerAttribute = htlExpression.getOuterXmlAttribute() ?: return null
    return outerAttribute.localName.substringBefore(".").toLowerCase()
}

fun PsiElement.getTemplateDefinitionAttribute(file: PsiFile): XmlAttribute? {
    val htlExpression = PsiTreeUtil.getParentOfType(this, HtlExpression::class.java, false)
            ?: return null
    val offset = htlExpression.textOffset
    if (offset > 0) {
        val previousElement = file.viewProvider.findElementAt(offset - 1, StdLanguages.HTML)
                ?: return null
        var currentTag = PsiTreeUtil.getParentOfType(previousElement, XmlTag::class.java)
        while (currentTag != null) {
            currentTag.attributes
                    .firstOrNull { it.localName.startsWith("${HtlBlock.TEMPLATE.type}.") }
                    ?.let { return it }
            currentTag = PsiTreeUtil.getParentOfType(currentTag, XmlTag::class.java)
        }
    }
    return null
}

/**
 * Returns referenced variable identifier if property refers to variable.
 * Otherwise returns `null`.
 *
 * @return referenced variable identifier or `null`
 */
fun PsiElement.getReferencedVariableElement(): PsiElement? {
    val propertyAccess = PsiTreeUtil.getParentOfType(this, HtlPropertyAccess::class.java) ?: return null
    val referencedElement = PsiTreeUtil.prevLeaf(propertyAccess) ?: return null
    if ((referencedElement as? LeafPsiElement)?.elementType === HtlTypes.IDENTIFIER && referencedElement.parent is HtlVariable) {
        return referencedElement
    }
    return null
}

fun PsiElement.isPartOfHtlString() = this.parent is HtlStringLiteral

fun PsiElement.isHtlExpressionToken() =
        this is LeafPsiElement
                && (this.elementType === HtlTypes.EXPR_START
                || this.elementType === HtlTypes.EXPR_END)

fun PsiElement.isGlobalObjectPropertyAccess(): Boolean {
    val referencedVariable = this.getReferencedVariableElement() ?: return false
    return HtlGlobalObject.predefinedPropertiesHoldersIdentifiers.contains(referencedVariable.text)
}

fun PsiElement.isListPropertyAccess(): Boolean {
    val referencedVariable = this.getReferencedVariableElement() ?: return false
    return referencedVariable.text.endsWith("List") && referencedVariable.isDeclaredAsIterable()
}

private fun PsiElement.isDeclaredAsIterable(): Boolean {
    val variableIdentifier = this.text.substringBefore("List")
    val htmlPsiElement = this.containingFile.viewProvider.findElementAt(this.textOffset, StdLanguages.HTML)
    return PsiTreeUtil.findFirstParent(htmlPsiElement, { it.declaresIterableVariable(variableIdentifier) }) != null
}

private fun PsiElement.declaresIterableVariable(variableIdentifier: String): Boolean {
    if (this is XmlTag) {
        return this.attributes.any { it.isIterableVariableDeclaration(variableIdentifier) }
    }
    return false
}

private fun XmlAttribute.isIterableVariableDeclaration(variableIdentifier: String): Boolean {
    val blockName = this.localName
    if (variableIdentifier == "item" && isHtlIterableBlock(blockName)) {
        return true
    }
    if (blockName.contains('.')) {
        val blockType = blockName.substringBefore('.')
        if (isHtlIterableBlock(blockType)) {
            return blockName.substringAfter('.') == variableIdentifier
        }
    }
    return false
}

fun PsiElement.isTemplateBlockParam() = HtlBlock.TEMPLATE.type == this.getOuterBlockType()

fun PsiElement.isLocalTemplateCall() = this.isTemplateCallExpression() && isAtExpressionBoundaries(this, this)

private fun PsiElement.isTemplateCallExpression() = this.getOuterBlockType() == HtlBlock.CALL.type && !this.isInOption()

private fun PsiElement.isInOption() = PsiTreeUtil.getParentOfType(this, HtlOption::class.java, false) != null

fun PsiElement.isTemplateReference(): Boolean {
    if (!this.isTemplateCallExpression()) return false
    val propertyAccess = PsiTreeUtil.getParentOfType(this, HtlPropertyAccess::class.java, false) ?: return false
    val accessedVariable = PsiTreeUtil.prevVisibleLeaf(propertyAccess) ?: return false
    return isAtExpressionBoundaries(accessedVariable, this)
}

private fun isAtExpressionBoundaries(leftElement: PsiElement, rightElement: PsiElement): Boolean {
    val prev = PsiTreeUtil.prevVisibleLeaf(leftElement)?.node?.elementType ?: return false
    val next = PsiTreeUtil.nextVisibleLeaf(rightElement)?.node?.elementType ?: return false
    if (prev == HtlTypes.EXPR_START && (next == HtlTypes.OPTIONS_SEPARATOR || next == HtlTypes.EXPR_END)) {
        return true
    }
    return false
}
