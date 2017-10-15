package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.completion.provider.insertHandler.HtlExprOptionBracketsInsertHandler
import co.nums.intellij.aem.htl.completion.provider.insertHandler.HtlExprOptionQuotesInsertHandler
import co.nums.intellij.aem.htl.data.expressions.ExpressionOption
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.HtlTypes
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.tree.IElementType

/**
 * Provides HTL built-in expression options.
 */
object HtlExprOptionsCompletionProvider : UniqueIdentifiersProviderBase() {

    private val insertHandlers = mapOf(
            "quotes" to HtlExprOptionQuotesInsertHandler,
            "brackets" to HtlExprOptionBracketsInsertHandler
    )

    override val candidateLookupElements = HtlDefinitions.expressionOptions.map { it.toLookupElement() }

    private fun ExpressionOption.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(this.name)
                .withIcon(HtlIcons.HTL_EXPRESSION_OPTION)
                .withTypeText("HTL expression option", true)
                .bold()
                .withInsertHandler(insertHandlers[this.insertHandlerType])
    }

    override val identifiersContainerElementType: IElementType = HtlTypes.OPTION_LIST

    override val identifiedElementType: IElementType = HtlTypes.OPTION

}
