package co.nums.intellij.aem.htl.extensions

import co.nums.intellij.aem.htl.definitions.*
import co.nums.intellij.aem.htl.psi.*
import com.intellij.lang.StdLanguages
import com.intellij.psi.PsiElement
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
        val viewProvider = this.containingFile.viewProvider
        val previousElement = viewProvider.findElementAt(offset - 1, StdLanguages.HTML) ?: return null
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

// TODO: simplify
fun PsiElement.isLocalTemplateCall(): Boolean {
    if (this.getOuterBlockType() != HtlBlock.CALL.type) return false
    if (this.isInOption()) return false
    val prev = PsiTreeUtil.prevVisibleLeaf(this)?.node?.elementType ?: return false
    val next = PsiTreeUtil.nextVisibleLeaf(this)?.node?.elementType ?: return false
    if (prev == HtlTypes.EXPR_START && (next == HtlTypes.OPTIONS_SEPARATOR || next == HtlTypes.EXPR_END)) {
        return true
    }
    return false
}

private fun PsiElement.isInOption() = PsiTreeUtil.getParentOfType(this, HtlOption::class.java, false) != null

// TODO: simplify
fun PsiElement.isTemplateReference(): Boolean {
    if (this.getOuterBlockType() != HtlBlock.CALL.type) return false
    if (this.isInOption()) return false
    val propertyAccess = PsiTreeUtil.getParentOfType(this, HtlPropertyAccess::class.java, false) ?: return false
    val accessedVariable = PsiTreeUtil.prevVisibleLeaf(propertyAccess) ?: return false
    val prev = PsiTreeUtil.prevVisibleLeaf(accessedVariable)?.node?.elementType ?: return false
    val next = PsiTreeUtil.nextVisibleLeaf(this)?.node?.elementType ?: return false
    if (prev == HtlTypes.EXPR_START && (next == HtlTypes.OPTIONS_SEPARATOR || next == HtlTypes.EXPR_END)) {
        return true
    }
    return false
}
