package co.nums.intellij.aem.htl.psi

import com.intellij.extapi.psi.ASTWrapperPsiElement
import com.intellij.lang.ASTNode
import com.intellij.psi.PsiComment
import com.intellij.psi.tree.IElementType

abstract class HtlCommentMixin(node: ASTNode) : ASTWrapperPsiElement(node), PsiComment {

    override fun getTokenType(): IElementType {
        return node.elementType
    }

}
