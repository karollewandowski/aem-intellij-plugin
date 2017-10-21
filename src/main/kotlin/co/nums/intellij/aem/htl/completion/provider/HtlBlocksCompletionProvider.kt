package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.definitions.HtlBlock
import co.nums.intellij.aem.htl.icons.HtlIcons
import com.intellij.codeInsight.lookup.*
import com.intellij.psi.tree.IElementType
import com.intellij.psi.xml.XmlElementType

/**
 * Provides HTL blocks (HTML data-sly-* attributes).
 */
object HtlBlocksCompletionProvider : UniqueIdentifiersProviderBase() {

    override val candidateLookupElements = HtlBlock.values().map { it.toLookupElement() }

    private fun HtlBlock.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(type)
                .withIcon(HtlIcons.HTL_BLOCK)
                .withTypeText("HTL block", true)
                .bold()
                .withInsertHandler(insertHandler)
    }

    override val identifiersContainerElementType: IElementType = XmlElementType.HTML_TAG

    override val identifiedElementType: IElementType = XmlElementType.XML_ATTRIBUTE

}
