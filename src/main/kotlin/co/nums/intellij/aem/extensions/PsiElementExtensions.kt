package co.nums.intellij.aem.extensions

import co.nums.intellij.aem.htl.psi.HtlElementTypes
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement

fun PsiElement.isHtlString() =
        this is LeafPsiElement
                && (this.elementType === HtlElementTypes.SINGLE_QUOTED_STRING
                || this.elementType === HtlElementTypes.DOUBLE_QUOTED_STRING)

fun PsiElement.isHtlExpressionToken() =
        this is LeafPsiElement
                && (this.elementType === HtlElementTypes.EXPR_START
                || this.elementType === HtlElementTypes.EXPR_END)
