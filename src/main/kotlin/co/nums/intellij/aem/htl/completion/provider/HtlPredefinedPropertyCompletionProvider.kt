package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.completion.provider.data.globalobjects.PredefinedProperty
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.utils.JsonReader
import com.intellij.codeInsight.completion.CompletionParameters
import com.intellij.codeInsight.completion.CompletionProvider
import com.intellij.codeInsight.completion.CompletionResultSet
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.util.ProcessingContext


object HtlPredefinedPropertyCompletionProvider : CompletionProvider<CompletionParameters>() {

    private val predefinedPropertiesElements = loadPredefinedProperties()

    private fun loadPredefinedProperties(): List<LookupElement> {
        val predefinedProperties = JsonReader.readJson<Array<PredefinedProperty>>("definitions/htl-predefined-properties.json")
        return predefinedProperties.map { it.toLookupElement() }
    }

    private fun PredefinedProperty.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(this.name)
                .withIcon(HtlIcons.HTL_PREDEFINED_PROPERTY)
                .withTypeText("HTL property", true)
    }

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, resultSet: CompletionResultSet) =
            resultSet.addAllElements(predefinedPropertiesElements)

}
