package co.nums.intellij.aem.htl.psi.extensions

import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement

fun PsiElement.isHtlString() =
        this is LeafPsiElement
                && (this.elementType === HtlTypes.SINGLE_QUOTED_STRING
                || this.elementType === HtlTypes.DOUBLE_QUOTED_STRING)

fun PsiElement.isHtlExpressionToken() =
        this is LeafPsiElement
                && (this.elementType === HtlTypes.EXPR_START
                || this.elementType === HtlTypes.EXPR_END)
