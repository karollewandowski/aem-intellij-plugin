package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.completion.provider.data.expressions.ExpressionOption
import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprOptionBracketsInsertHandler
import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprOptionQuotesInsertHandler
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.HtlElementTypes
import co.nums.intellij.aem.utils.JsonReader
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

    override val candidateLookupElements = loadExpressionOptions()

    private fun loadExpressionOptions(): Set<LookupElement> {
        return JsonReader.readJson<Array<ExpressionOption>>("definitions/htl-expression-options.json")
                .map { it.toLookupElement() }
                .toSet()
    }

    private fun ExpressionOption.toLookupElement(): LookupElement {
        return LookupElementBuilder.create(this.name)
                .withIcon(HtlIcons.HTL_EXPRESSION_OPTION)
                .withTypeText("HTL expression option", true)
                .withInsertHandler(insertHandlers[this.insertHandlerType])
    }

    override val identifiersContainerElementType: IElementType = HtlElementTypes.OPTION_LIST

    override val identifiedElementType: IElementType = HtlElementTypes.OPTION

}
