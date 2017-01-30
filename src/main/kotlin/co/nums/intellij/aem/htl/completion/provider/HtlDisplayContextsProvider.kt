package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.completion.provider.data.expressions.DisplayContext
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.utils.JsonReader
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

object HtlDisplayContextsProvider : CompletionProvider<CompletionParameters>() {

    val displayContextElements = loadDisplayContexts()

    private fun loadDisplayContexts(): Set<LookupElement> {
        return JsonReader.readJson<Array<DisplayContext>>("definitions/htl-display-contexts.json")
                .map { it.toLookupElement() }
                .toSet()
    }

    private fun DisplayContext.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(this.name)
                .withIcon(HtlIcons.HTL_DISPLAY_CONTEXT)
                .withTypeText("HTL display context", true)
    }

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, resultSet: CompletionResultSet) =
            resultSet.addAllElements(displayContextElements)

}
