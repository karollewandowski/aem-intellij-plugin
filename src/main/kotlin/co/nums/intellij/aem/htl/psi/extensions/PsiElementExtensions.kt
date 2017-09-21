package co.nums.intellij.aem.htl.psi.extensions

import co.nums.intellij.aem.htl.HtlBlocks
import co.nums.intellij.aem.htl.psi.*
import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.lang.StdLanguages
import com.intellij.openapi.util.Condition
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.*

fun PsiElement.isPartOfHtlString() = this.parent is HtlStringLiteral

fun PsiElement.isHtlExpressionToken() =
        this is LeafPsiElement
                && (this.elementType === HtlTypes.EXPR_START
                || this.elementType === HtlTypes.EXPR_END)


fun PsiElement.isGlobalObjectPropertyAccess(): Boolean {
    val referencedVariable = HtlPsiUtil.getReferencedVariableElement(this) ?: return false
    return HtlDefinitions.globalPropertyObjectsNames.contains(referencedVariable.text)
}

fun PsiElement.isListPropertyAccess(): Boolean {
    val referencedVariable = HtlPsiUtil.getReferencedVariableElement(this) ?: return false
    return referencedVariable.text.endsWith("List") && referencedVariable.isDeclaredAsIterable()
}

private fun PsiElement.isDeclaredAsIterable(): Boolean {
    val variableIdentifier = this.text.substringBefore("List")
    val htmlPsiElement = this.containingFile.viewProvider.findElementAt(this.textOffset, StdLanguages.HTML)
    return PsiTreeUtil.findFirstParent(htmlPsiElement, Condition { it.declaresIterableVariable(variableIdentifier) }) != null
}

private fun PsiElement.declaresIterableVariable(variableIdentifier: String): Boolean {
    if (this is XmlTag) {
        return this.attributes.any { it.isIterableVariableDeclaration(variableIdentifier) }
    }
    return false
}

private fun XmlAttribute.isIterableVariableDeclaration(variableIdentifier: String): Boolean {
    val blockName = this.localName
    if (variableIdentifier == "item" && HtlBlocks.ITERABLE.contains(blockName)) {
        return true
    }
    if (blockName.contains('.')) {
        val blockType = blockName.substringBefore('.')
        if (HtlBlocks.ITERABLE.contains(blockType)) {
            return blockName.substringAfter('.') == variableIdentifier
        }
    }
    return false
}
