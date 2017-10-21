package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.data.globalobjects.GlobalObject
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.util.ProcessingContext

object HtlGlobalObjectsCompletionProvider : CompletionProvider<CompletionParameters>() {

    private val globalObjectsElements = HtlDefinitions.globalObjects.map { it.toLookupElement() }

    private fun GlobalObject.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(name)
                .withIcon(HtlIcons.HTL_GLOBAL_OBJECT)
                .withTypeText(type)
                .bold()
    }

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) =
            result.addAllElements(globalObjectsElements)

}
