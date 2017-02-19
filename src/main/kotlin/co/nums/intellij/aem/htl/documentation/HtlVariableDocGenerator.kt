package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.data.globalobjects.GlobalObject
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.PsiElement

object HtlVariableDocGenerator {

    private val globalObjectsDocs = HtlDefinitions.globalObjects.associate { Pair(it.name, it.getDocString()) }

    private fun GlobalObject.getDocString(): String {
        var docString = "<code>${this.type}</code>"
        if (this.description != null) {
            docString += "<p>${this.description}</p>"
        }
        return docString
    }

    fun generateDoc(element: PsiElement) = globalObjectsDocs[element.text]

}
