package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.definitions.HtlPredefinedProperty
import co.nums.intellij.aem.htl.extensions.*
import com.intellij.psi.PsiElement

object HtlPredefinedPropertyDocGenerator {

    private val predefinedPropertiesDocs = HtlPredefinedProperty.values().associate { Pair(it.identifier, it.getDocString()) }

    private fun HtlPredefinedProperty.getDocString() =
            "<code>$type</code>${if (description != null) "<p>$description</p>" else "" }"

    fun generateDoc(element: PsiElement): String? {
        return when {
            element.isPredefinedPropertyAccess() -> predefinedPropertiesDocs[element.text]
            else -> null
        }
    }

    private fun PsiElement.isPredefinedPropertyAccess() = isGlobalObjectPropertyAccess() || isListPropertyAccess()

}
