package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.search.HtlJavaSearch
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.util.ProcessingContext

object HtlJavaUseApiClassesProvider : CompletionProvider<CompletionParameters>() {

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        val typedPrefix = getTypedPrefix(parameters)
        result.addAllElements(javaUseApiClasses(parameters.position.project, typedPrefix))
    }

    private fun getTypedPrefix(parameters: CompletionParameters): String {
        val offsetInCurrentElement = parameters.offset - parameters.position.textOffset
        return parameters.position.text.substring(0, offsetInCurrentElement)
    }

    private fun javaUseApiClasses(project: Project, typedPrefix: String): Collection<LookupElement> {
        return (HtlJavaSearch.useApiImplementers(project) + HtlJavaSearch.slingModels(project))
                .filter { it.matches(typedPrefix) }
                .map { it.toLookupElement() }
    }

    private fun PsiClass.matches(typedPrefix: String) =
            this.qualifiedName?.startsWith(typedPrefix) ?: false || this.name?.startsWith(typedPrefix) ?: false

    private fun PsiClass.toLookupElement() = JavaPsiClassReferenceElement(this).setIcon(HtlIcons.HTL_USE_OBJECT)

}
