package co.nums.intellij.aem.htl.completion.provider

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.psi.impl.source.tree.TreeUtil
import com.intellij.psi.tree.*
import com.intellij.util.ProcessingContext

abstract class UniqueIdentifiersProviderBase : CompletionProvider<CompletionParameters>() {

    protected abstract val candidateLookupElements: List<LookupElement>
    protected abstract val identifiersContainerElementType: IElementType
    protected abstract val identifiedElementType: IElementType

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext, result: CompletionResultSet) {
        val existingIdentifiers = findExistingIdentifiers(parameters)
        candidateLookupElements
                .filterNot { it.lookupString in existingIdentifiers }
                .forEach { result.addElement(it) }
    }

    private fun findExistingIdentifiers(parameters: CompletionParameters): Set<String> {
        val currentNode = parameters.position.node
        val identifiersContainer = TreeUtil.findParent(currentNode, identifiersContainerElementType) ?: return emptySet()
        return identifiersContainer.getChildren(TokenSet.create(identifiedElementType))
                .map { it.firstChildNode }
                .map { it.text }
                .toSet()
    }

}
