package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.data.blocks.HtlBlockVariable
import co.nums.intellij.aem.htl.definitions.BlockIdentifierType.ELEMENT_CHILDREN_SCOPE_VARIABLE
import co.nums.intellij.aem.htl.definitions.BlockIdentifierType.ELEMENT_SCOPE_VARIABLE
import co.nums.intellij.aem.htl.definitions.BlockIdentifierType.GLOBAL_VARIABLE
import co.nums.intellij.aem.htl.extensions.getTemplateDefinitionAttribute
import co.nums.intellij.aem.htl.psi.search.HtlSearch
import co.nums.intellij.aem.icons.HtlIcons
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.StdLanguages
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.*
import com.intellij.util.ProcessingContext

object HtlBlockVariablesProvider : CompletionProvider<CompletionParameters>() {

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        val htmlFile = parameters.originalFile.viewProvider.getPsi(StdLanguages.HTML)
        val currentElement = parameters.position
        val outerTemplateRange = currentElement.getTemplateDefinitionAttribute(htmlFile)?.parent?.textRange
        val variableElements = HtlSearch.blockVariables(htmlFile)
                .filter { currentElement.isInScopeOf(it, outerTemplateRange) }
                .map { it.toLookupElement() }
        result.addAllElements(variableElements)
    }

    private fun PsiElement.isInScopeOf(variable: HtlBlockVariable, templateRange: TextRange?) =
            variable.notOutOf(templateRange) && when (variable.identifierType) {
                GLOBAL_VARIABLE -> this.isInsideOrAfterDeclarationBlockElement(variable)
                ELEMENT_SCOPE_VARIABLE -> this.isInBlockElement(variable)
                ELEMENT_CHILDREN_SCOPE_VARIABLE -> this.isInBlockElementChildren(variable)
                else -> false
            }

    private fun HtlBlockVariable.notOutOf(templateRange: TextRange?) =
            templateRange?.contains(this.definer.textOffset) ?: true

    private fun PsiElement.isInsideOrAfterDeclarationBlockElement(variable: HtlBlockVariable): Boolean {
        val blockRangeStart = (variable.definer.context as? XmlTag)?.textRange?.startOffset ?: return false
        val currentElementStartOffset = this.textRange.startOffset
        return currentElementStartOffset > blockRangeStart && !variable.definer.textRange.contains(currentElementStartOffset)
    }

    private fun PsiElement.isInBlockElement(variable: HtlBlockVariable): Boolean {
        val outerTagRange = (variable.definer.context as? XmlTag)?.textRange ?: return false
        val currentElementStartOffset = this.textRange.startOffset
        return outerTagRange.startOffset < currentElementStartOffset && currentElementStartOffset < outerTagRange.endOffset
                && !variable.definer.textRange.contains(currentElementStartOffset)
    }

    private fun PsiElement.isInBlockElementChildren(variable: HtlBlockVariable): Boolean {
        val outerTag = variable.definer.context as? XmlTag ?: return false
        if (outerTag.isEmpty) return false
        val childrenScopeStart = outerTag.children
                .filterIsInstance(XmlToken::class.java)
                .firstOrNull { it.tokenType == XmlTokenType.XML_TAG_END }
                ?.textOffset ?: return false
        val currentElementStartOffset = this.textRange.startOffset
        return childrenScopeStart < currentElementStartOffset && currentElementStartOffset < outerTag.textRange.endOffset
    }

    private fun HtlBlockVariable.toLookupElement() =
            LookupElementBuilder.create(identifier)
                    .withIcon(HtlIcons.HTL_VARIABLE)
                    .withTypeText(dataType)
                    .bold()

}
