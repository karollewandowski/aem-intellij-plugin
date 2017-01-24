package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprBlockInsertHandler
import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlSimpleBlockInsertHandler
import co.nums.intellij.aem.htl.icons.HtlIcons
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.tree.IElementType
import com.intellij.psi.xml.XmlElementType

/**
 * Provides HTL blocks (HTML data-sly-* attributes).
 */
object HtlBlocksCompletionProvider : UniqueIdentifiersProviderBase() {

    override val candidateLookupElements: Set<LookupElement> = setOf(
            block("data-sly-attribute").withInsertHandler(HtlExprBlockInsertHandler),
            block("data-sly-call").withInsertHandler(HtlExprBlockInsertHandler),
            block("data-sly-element").withInsertHandler(HtlExprBlockInsertHandler),
            block("data-sly-include").withInsertHandler(HtlSimpleBlockInsertHandler),
            block("data-sly-list").withInsertHandler(HtlExprBlockInsertHandler),
            block("data-sly-repeat").withInsertHandler(HtlExprBlockInsertHandler),
            block("data-sly-resource").withInsertHandler(HtlSimpleBlockInsertHandler),
            block("data-sly-template"),
            block("data-sly-test").withInsertHandler(HtlExprBlockInsertHandler),
            block("data-sly-text").withInsertHandler(HtlExprBlockInsertHandler),
            block("data-sly-unwrap"),
            block("data-sly-use").withInsertHandler(HtlSimpleBlockInsertHandler)
    )

    private fun block(name: String) =
            LookupElementBuilder.create(name)
                    .withBoldness(true)
                    .withIcon(HtlIcons.HTL_BLOCK)
                    .withTypeText("HTL block", true)

    override val identifiersContainerElementType: IElementType = XmlElementType.HTML_TAG

    override val identifiedElementType: IElementType = XmlElementType.XML_ATTRIBUTE

}
