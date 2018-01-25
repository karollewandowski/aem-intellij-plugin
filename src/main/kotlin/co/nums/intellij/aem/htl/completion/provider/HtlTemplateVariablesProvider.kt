package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.extensions.getTemplateDefinitionAttribute
import co.nums.intellij.aem.htl.psi.HtlExpression
import co.nums.intellij.aem.icons.HtlIcons
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.util.ProcessingContext

object HtlTemplateVariablesProvider : CompletionProvider<CompletionParameters>() {

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        val templateBlock = parameters.position.getTemplateDefinitionAttribute(parameters.originalFile) ?: return
        if (currentPositionOutOfTemplateTag(parameters, templateBlock)) return
        val expressionOffset = templateBlock.valueElement?.textOffset ?: return
        val htlElement = parameters.originalFile.viewProvider.findElementAt(expressionOffset + 1, HtlLanguage) ?: return
        val htlExpression = PsiTreeUtil.getParentOfType(htlElement, HtlExpression::class.java) ?: return
        htlExpression.optionList?.optionList
                ?.map { it.optionName.text }
                ?.map { it.toLookupElement() }
                ?.let { result.addAllElements(it) }
    }

    private fun currentPositionOutOfTemplateTag(parameters: CompletionParameters, templateBlock: XmlAttribute) =
            parameters.position.textOffset > templateBlock.parent.textRange.endOffset

    private fun String.toLookupElement() =
            LookupElementBuilder.create(this)
                    .withIcon(HtlIcons.HTL_VARIABLE)
                    .withTypeText("Template parameter")
                    .bold()

}
