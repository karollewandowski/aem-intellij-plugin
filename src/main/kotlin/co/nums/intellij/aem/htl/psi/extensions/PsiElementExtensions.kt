package co.nums.intellij.aem.htl.psi.extensions

import co.nums.intellij.aem.htl.psi.HtlTypes
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.PsiElement
import com.intellij.psi.impl.source.tree.LeafPsiElement
import com.intellij.psi.xml.XmlAttribute

val htlBlocksNames = HtlDefinitions.blocks.map { it.name }

fun PsiElement.isHtlString() =
        this is LeafPsiElement
                && (this.elementType === HtlTypes.SINGLE_QUOTED_STRING
                || this.elementType === HtlTypes.DOUBLE_QUOTED_STRING)

fun PsiElement.isHtlExpressionToken() =
        this is LeafPsiElement
                && (this.elementType === HtlTypes.EXPR_START
                || this.elementType === HtlTypes.EXPR_END)

fun PsiElement?.isHtlBlock() =
        this != null && this.context is XmlAttribute
                && htlBlocksNames.contains(this.text.substringBefore(".").toLowerCase())
