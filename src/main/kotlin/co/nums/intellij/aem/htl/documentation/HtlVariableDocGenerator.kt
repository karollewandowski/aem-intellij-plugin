package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.definitions.HtlGlobalObject
import com.intellij.psi.PsiElement

object HtlVariableDocGenerator {

    private val globalObjectsDocs = HtlGlobalObject.values().associate { Pair(it.identifier, it.getDocString()) }

    private fun HtlGlobalObject.getDocString() =
        "<code>$type</code>${if (description != null) "<p>$description</p>" else "" }"

    fun generateDoc(element: PsiElement) = globalObjectsDocs[element.text]

}
