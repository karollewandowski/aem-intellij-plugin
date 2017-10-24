package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.definitions.HtlExpressionOption
import co.nums.intellij.aem.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.codeInsight.lookup.*
import com.intellij.psi.tree.IElementType

/**
 * Provides HTL built-in expression options.
 */
object HtlExpressionOptionsCompletionProvider : UniqueIdentifiersProviderBase() {

    override val candidateLookupElements = HtlExpressionOption.values().map { it.toLookupElement() }

    private fun HtlExpressionOption.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(identifier)
                .withIcon(HtlIcons.HTL_EXPRESSION_OPTION)
                .withTypeText("HTL expression option", true)
                .bold()
                .withInsertHandler(insertHandler)
    }

    override val identifiersContainerElementType: IElementType = HtlTypes.OPTION_LIST

    override val identifiedElementType: IElementType = HtlTypes.OPTION

}
