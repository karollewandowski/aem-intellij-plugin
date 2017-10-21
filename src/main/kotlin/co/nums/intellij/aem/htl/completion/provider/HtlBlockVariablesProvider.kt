package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.data.blocks.HtlBlockVariable
import co.nums.intellij.aem.htl.definitions.BlockIdentifierType.BLOCK_VARIABLE
import co.nums.intellij.aem.htl.definitions.BlockIdentifierType.HOISTED_VARIABLE
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.search.HtlSearch
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.lang.StdLanguages
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlTag
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

    private fun HtlBlockVariable.hasApplicableScopeFor(currentElement: PsiElement): Boolean {
        return when (this.identifierType) {
            HOISTED_VARIABLE -> currentElement.isAfterDeclaration(this)
            BLOCK_VARIABLE -> currentElement.isInsideBlock(this)
            else -> false
        }
    }

    private fun PsiElement.isAfterDeclaration(variable: HtlBlockVariable): Boolean {
        val blockRangeStartOffset = variable.definer.textRange.startOffset
        val currentElementStartOffset = this.textRange.startOffset
        return currentElementStartOffset > blockRangeStartOffset
    }

    private fun PsiElement.isInsideBlock(variable: HtlBlockVariable): Boolean {
        val outerTagRange = (variable.definer.context as? XmlTag)?.textRange ?: return false
        val currentElementStartOffset = this.textRange.startOffset
        return outerTagRange.startOffset < currentElementStartOffset && currentElementStartOffset < outerTagRange.endOffset
    }

    private fun HtlBlockVariable.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(identifier)
                .bold()
                .withTypeText(dataType)
                .withIcon(HtlIcons.HTL_VARIABLE)
    }

}
