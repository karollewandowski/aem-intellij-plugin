package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.data.expressions.DisplayContext
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

object HtlDisplayContextsProvider : CompletionProvider<CompletionParameters>() {

    val displayContextElements = HtlDefinitions.displayContexts.map { it.toLookupElement() }

    private fun DisplayContext.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(this.name)
                .withIcon(HtlIcons.HTL_DISPLAY_CONTEXT)
                .withTypeText("HTL display context", true)
                .bold()
    }

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, resultSet: CompletionResultSet) =
            resultSet.addAllElements(displayContextElements)

}
