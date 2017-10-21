package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.definitions.*
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.extensions.*
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext

object HtlPredefinedPropertyCompletionProvider : CompletionProvider<CompletionParameters>() {

    private val listPropertiesElements =
            getPredefinedPropertiesElementsByContext(HtlPredefinedPropertyContext.LIST)

    private val globalObjectsPropertiesElements =
            getPredefinedPropertiesElementsByContext(HtlPredefinedPropertyContext.GLOBAL_PROPERTIES_OBJECT)

    private fun getPredefinedPropertiesElementsByContext(context: HtlPredefinedPropertyContext) = HtlPredefinedProperty.values()
            .filter { it.context == context }
            .map { it.toLookupElement() }

    private fun HtlPredefinedProperty.toLookupElement() =
            LookupElementBuilder.create(identifier)
                    .bold()
                    .withIcon(HtlIcons.HTL_PREDEFINED_PROPERTY)
                    .withTypeText(type)

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
        when {
            parameters.position.isListPropertyAccess() -> result.addAllElements(listPropertiesElements)
            parameters.position.isGlobalObjectPropertyAccess() -> result.addAllElements(globalObjectsPropertiesElements)
        }
    }

}
