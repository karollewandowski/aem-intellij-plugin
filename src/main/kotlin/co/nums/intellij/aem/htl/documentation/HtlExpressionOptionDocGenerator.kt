package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.definitions.HtlExpressionOption
import com.intellij.psi.PsiElement

object HtlExpressionOptionDocGenerator {

    private val expressionOptionsDocs = HtlExpressionOption.values().associate { Pair(it.identifier, it.description) }

    fun generateDoc(element: PsiElement) = expressionOptionsDocs[element.text]

}
