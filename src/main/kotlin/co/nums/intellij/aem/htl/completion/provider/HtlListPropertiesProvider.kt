package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.definitions.HtlListProperty
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.extensions.isListPropertyAccess
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.util.ProcessingContext

object HtlListPropertiesProvider : CompletionProvider<CompletionParameters>() {

    private val listPropertiesElements = HtlListProperty.values().map { it.toLookupElement() }

    private fun HtlListProperty.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(this.identifier)
                .bold()
                .withIcon(HtlIcons.HTL_PREDEFINED_PROPERTY)
                .withTypeText(this.type)
    }

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        if (parameters.position.isListPropertyAccess()) {
            result.addAllElements(listPropertiesElements)
        }
    }

}
