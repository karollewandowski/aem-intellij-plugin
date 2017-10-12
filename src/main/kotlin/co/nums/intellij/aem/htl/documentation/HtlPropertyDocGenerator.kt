package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.data.expressions.HtlListProperty
import co.nums.intellij.aem.htl.psi.extensions.*
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.PsiElement

object HtlPropertyDocGenerator {

    private val predefinedPropertiesDocs = HtlDefinitions.predefinedProperties.associate { Pair(it.name, "<code>${it.type}</code>") }
    private val listPropertiesDocs = HtlListProperty.values().associate { Pair(it.name, it.description) }

    fun generateDoc(element: PsiElement): String? {
        return when {
            element.isGlobalObjectPropertyAccess() -> predefinedPropertiesDocs[element.text]
            element.isListPropertyAccess() -> listPropertiesDocs[element.text]
            else -> null
        }
    }

}
