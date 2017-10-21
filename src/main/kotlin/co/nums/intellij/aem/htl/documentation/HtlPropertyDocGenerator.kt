package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.definitions.*
import co.nums.intellij.aem.htl.psi.extensions.*
import com.intellij.psi.PsiElement

object HtlPropertyDocGenerator {

    private val predefinedPropertiesDocs = HtlPredefinedProperty.values().associate { Pair(it.identifier, "<code>${it.type}</code>") }
    private val listPropertiesDocs = HtlListProperty.values().associate { Pair(it.identifier, it.description) }

    fun generateDoc(element: PsiElement): String? {
        return when {
            element.isGlobalObjectPropertyAccess() -> predefinedPropertiesDocs[element.text]
            element.isListPropertyAccess() -> listPropertiesDocs[element.text]
            else -> null
        }
    }

}
