package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.PsiElement

object HtlExpressionOptionDocGenerator {

    private val expressionOptionsDocs = HtlDefinitions.expressionOptions.associate { Pair(it.name, it.description) }

    fun generateDoc(element: PsiElement) = expressionOptionsDocs[element.text]

}
