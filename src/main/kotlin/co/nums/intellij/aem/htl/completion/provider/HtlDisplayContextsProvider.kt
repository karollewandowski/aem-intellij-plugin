package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.data.expressions.DisplayContext
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.util.ProcessingContext

object HtlDisplayContextsProvider : CompletionProvider<CompletionParameters>() {

    val displayContextElements = HtlDefinitions.displayContexts.map { it.toLookupElement() }

    private fun DisplayContext.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(name)
                .withIcon(HtlIcons.HTL_DISPLAY_CONTEXT)
                .withTypeText("HTL display context", true)
                .bold()
    }

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) =
            result.addAllElements(displayContextElements)

}
