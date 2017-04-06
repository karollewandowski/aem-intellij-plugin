package co.nums.intellij.aem.htl.psi.mixins

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment

abstract class HtlCommentMixin(node: ASTNode) : ASTWrapperPsiElement(node), PsiComment {

    override fun getTokenType() = node.elementType

}
