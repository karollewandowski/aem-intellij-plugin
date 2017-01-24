package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.icons.HtlIcons
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

object HtlDisplayContextsProvider : CompletionProvider<CompletionParameters>() {

    private val displayContextElements = setOf(
            displayContextElement("attribute"),
            displayContextElement("attributeName"),
            displayContextElement("comment"),
            displayContextElement("elementName"),
            displayContextElement("html"),
            displayContextElement("number"),
            displayContextElement("scriptComment"),
            displayContextElement("scriptRegExp"),
            displayContextElement("scriptString"),
            displayContextElement("scriptToken"),
            displayContextElement("styleComment"),
            displayContextElement("styleString"),
            displayContextElement("styleToken"),
            displayContextElement("text"),
            displayContextElement("unsafe"),
            displayContextElement("uri")
    )

    private fun displayContextElement(displayContextName: String) =
            LookupElementBuilder.create(displayContextName)
                    .withIcon(HtlIcons.HTL_DISPLAY_CONTEXT)
                    .withTypeText("HTL display context", true)

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, resultSet: CompletionResultSet) =
            resultSet.addAllElements(displayContextElements)

}
