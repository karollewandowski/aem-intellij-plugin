package co.nums.intellij.aem.htl.extensions

import co.nums.intellij.aem.htl.definitions.*
import co.nums.intellij.aem.htl.psi.*
import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil
import com.intellij.lang.StdLanguages
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
    return HtlGlobalObject.predefinedPropertiesHoldersNames.contains(referencedVariable.text)
}

fun PsiElement.isListPropertyAccess(): Boolean {
    val referencedVariable = HtlPsiUtil.getReferencedVariableElement(this) ?: return false
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
