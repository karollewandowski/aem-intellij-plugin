package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.definitions.HtlDisplayContext
import co.nums.intellij.aem.icons.HtlIcons
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.util.ProcessingContext

object HtlDisplayContextsProvider : CompletionProvider<CompletionParameters>() {

    private val displayContextElements = HtlDisplayContext.values().map { it.toLookupElement() }

    private fun HtlDisplayContext.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(type)
                .withIcon(HtlIcons.HTL_DISPLAY_CONTEXT)
                .withTypeText("HTL display context", true)
                .bold()
    }

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) =
            result.addAllElements(displayContextElements)

}
