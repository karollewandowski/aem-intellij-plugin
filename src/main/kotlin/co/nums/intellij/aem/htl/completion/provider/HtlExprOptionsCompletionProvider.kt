package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprOptionBracketsInsertHandler
import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprOptionQuotesInsertHandler
import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.HtlElementTypes
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.psi.tree.IElementType

/**
 * Provides HTL built-in expression options.
 */
object HtlExprOptionsCompletionProvider : UniqueIdentifiersProviderBase() {

    override val candidateLookupElements: Set<LookupElement> = setOf(
            expressionOption("i18n"),
            expressionOption("format").withInsertHandler(HtlExprOptionBracketsInsertHandler),
            expressionOption("scheme").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("domain").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("locale").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("context").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("hint").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("join").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("path").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("prependPath").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("appendPath").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("selectors").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("addSelectors").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("removeSelectors").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("extension").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("suffix").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("prependSuffix").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("appendSuffix").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("query").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("addQuery").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("removeQuery").withInsertHandler(HtlExprOptionQuotesInsertHandler),
            expressionOption("fragment").withInsertHandler(HtlExprOptionQuotesInsertHandler)
    )

    private fun expressionOption(name: String) =
            LookupElementBuilder.create(name)
                    .withIcon(HtlIcons.HTL_EXPRESSION_OPTION)
                    .withTypeText("HTL expression option", true)

    override val identifiersContainerElementType: IElementType = HtlElementTypes.OPTION_LIST

    override val identifiedElementType: IElementType = HtlElementTypes.OPTION

}
