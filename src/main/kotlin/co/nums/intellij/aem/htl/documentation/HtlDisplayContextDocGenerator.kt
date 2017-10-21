package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.definitions.HtlDisplayContext
import com.intellij.psi.PsiElement

object HtlDisplayContextDocGenerator {

    private val displayContextsDocs = HtlDisplayContext.values().associate { Pair(it.type, it.description) }

    fun generateDoc(element: PsiElement): String? {
        val displayContextName = element.text.removeSurrounding("'").removeSurrounding("\"")
        return displayContextsDocs[displayContextName]
    }

}
