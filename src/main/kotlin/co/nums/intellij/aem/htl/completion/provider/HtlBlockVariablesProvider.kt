package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.data.blocks.HtlBlockVariable
import co.nums.intellij.aem.htl.definitions.BlockIdentifierType.ELEMENT_CHILDREN_SCOPE_VARIABLE
import co.nums.intellij.aem.htl.definitions.BlockIdentifierType.ELEMENT_SCOPE_VARIABLE
import co.nums.intellij.aem.htl.definitions.BlockIdentifierType.GLOBAL_VARIABLE
import co.nums.intellij.aem.htl.psi.search.HtlSearch
import co.nums.intellij.aem.icons.HtlIcons
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.lang.StdLanguages
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.*
import com.intellij.util.ProcessingContext

object HtlBlockVariablesProvider : CompletionProvider<CompletionParameters>() {

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        val htmlFile = parameters.originalFile.viewProvider.getPsi(StdLanguages.HTML)
        val currentElement = parameters.position
        val variableElements = HtlSearch.blockVariables(htmlFile)
                .filter { it.hasApplicableScopeFor(currentElement) }
                .map { it.toLookupElement() }
        result.addAllElements(variableElements)
    }

    private fun HtlBlockVariable.hasApplicableScopeFor(currentElement: PsiElement) =
            when (this.identifierType) {
                GLOBAL_VARIABLE -> currentElement.isInsideOrAfterDeclarationBlockElement(this)
                ELEMENT_SCOPE_VARIABLE -> currentElement.isInBlockElement(this)
                ELEMENT_CHILDREN_SCOPE_VARIABLE -> currentElement.isInBlockElementChildren(this)
                else -> false
            }

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
                    .bold()
                    .withTypeText(dataType)
                    .withIcon(HtlIcons.HTL_VARIABLE)

}
