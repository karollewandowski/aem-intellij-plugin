package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.psi.search.HtlJavaSearch
import co.nums.intellij.aem.icons.HtlIcons
import com.intellij.codeInsight.completion.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiClass
import com.intellij.util.ProcessingContext

object HtlJavaUseApiClassesProvider : CompletionProvider<CompletionParameters>() {

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        result.addAllElements(javaUseApiClasses(parameters.position.project))
    }

    private fun javaUseApiClasses(project: Project) = HtlJavaSearch.useApiClasses(project).map { it.toLookupElement() }

    private fun PsiClass.toLookupElement() = JavaPsiClassReferenceElement(this).setIcon(HtlIcons.HTL_USE_OBJECT)

}
