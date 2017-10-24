package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.definitions.HtlGlobalObject
import co.nums.intellij.aem.icons.HtlIcons
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

object HtlGlobalObjectsCompletionProvider : CompletionProvider<CompletionParameters>() {

    private val globalObjectsElements = HtlGlobalObject.values().map { it.toLookupElement() }

    private fun HtlGlobalObject.toLookupElement() =
            LookupElementBuilder.create(identifier)
                    .withIcon(HtlIcons.HTL_GLOBAL_OBJECT)
                    .withTypeText(type)
                    .bold()

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) =
            result.addAllElements(globalObjectsElements)

}
