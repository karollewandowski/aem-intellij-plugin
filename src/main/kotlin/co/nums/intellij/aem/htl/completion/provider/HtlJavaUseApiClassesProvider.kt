package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.search.HtlJavaSearch
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.completion.JavaPsiClassReferenceElement
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.util.ProcessingContext

object HtlJavaUseApiClassesProvider : CompletionProvider<CompletionParameters>() {

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        result.addAllElements(javaUseApiClasses(parameters.position.project))
    }

    private fun javaUseApiClasses(project: Project): Collection<LookupElement> {
        return (HtlJavaSearch.useApiImplementers(project) + HtlJavaSearch.slingModels(project))
                .map { it.toLookupElement() }
    }

    private fun PsiClass.toLookupElement() = JavaPsiClassReferenceElement(this).setIcon(HtlIcons.HTL_USE_OBJECT)

}
