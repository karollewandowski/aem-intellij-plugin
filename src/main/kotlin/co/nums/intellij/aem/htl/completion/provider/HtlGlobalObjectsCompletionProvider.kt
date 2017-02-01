package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.completion.provider.data.globalobjects.GlobalObject
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

object HtlGlobalObjectsCompletionProvider : CompletionProvider<CompletionParameters>() {

    private val globalObjectsElements = HtlDefinitions.globalObjects.map { it.toLookupElement() }

    private fun GlobalObject.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(this.name)
                .withIcon(HtlIcons.HTL_GLOBAL_OBJECT)
                .withTypeText("HTL global object", true)
    }

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, resultSet: CompletionResultSet) =
            resultSet.addAllElements(globalObjectsElements)

}
