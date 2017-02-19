package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.PsiElement

object HtlDisplayContextDocGenerator {

    private val displayContextsDocs = HtlDefinitions.displayContexts.associate { Pair(it.name, it.description) }

    fun generateDoc(element: PsiElement): String? {
        val displayContextName = element.text.removeSurrounding("'").removeSurrounding("\"")
        return displayContextsDocs[displayContextName]
    }

}
