package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.definitions.HtlPredefinedProperty
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.extensions.isGlobalObjectPropertyAccess
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

object HtlPredefinedPropertyCompletionProvider : CompletionProvider<CompletionParameters>() {

    private val predefinedPropertiesElements = HtlPredefinedProperty.values().map { it.toLookupElement() }

    private fun HtlPredefinedProperty.toLookupElement() =
            LookupElementBuilder.create(identifier)
                    .bold()
                    .withIcon(HtlIcons.HTL_PREDEFINED_PROPERTY)
                    .withTypeText(type)

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
        if (parameters.position.isGlobalObjectPropertyAccess()) {
            result.addAllElements(predefinedPropertiesElements)
        }
    }

}
