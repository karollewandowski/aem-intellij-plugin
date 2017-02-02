package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.completion.provider.data.blocks.Block
import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprBlockInsertHandler
import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlSimpleBlockInsertHandler
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.tree.IElementType
import com.intellij.psi.xml.XmlElementType

/**
 * Provides HTL blocks (HTML data-sly-* attributes).
 */
object HtlBlocksCompletionProvider : UniqueIdentifiersProviderBase() {

    private val insertHandlers = mapOf(
            "expression" to HtlExprBlockInsertHandler,
            "quotes" to HtlSimpleBlockInsertHandler
    )

    override val candidateLookupElements = HtlDefinitions.blocks.map { it.toLookupElement() }

    private fun Block.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(this.name)
                .withIcon(HtlIcons.HTL_BLOCK)
                .withTypeText("HTL block", true)
                .bold()
                .withInsertHandler(insertHandlers[this.insertHandlerType])
    }

    override val identifiersContainerElementType: IElementType = XmlElementType.HTML_TAG

    override val identifiedElementType: IElementType = XmlElementType.XML_ATTRIBUTE

}
