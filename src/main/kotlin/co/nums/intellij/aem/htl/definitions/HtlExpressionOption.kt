package co.nums.intellij.aem.htl.definitions

import co.nums.intellij.aem.htl.completion.provider.insertHandler.*
import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.lookup.LookupElement

enum class HtlExpressionOption(
        val identifier: String,
        val description: String,
        val insertHandler: InsertHandler<LookupElement>? = null
) {

    I18N(
            identifier = "i18n",
            description = "Translates string to the resource language."
    ),
    FORMAT(
            identifier = "format",
            insertHandler = HtlExprOptionBracketsInsertHandler,
            description = "Value(s) to apply on the formatting pattern provided in expression."
    ),
    SCHEME(
            identifier = "scheme",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Sets the scheme part of URI provided in expression. Empty value removes the scheme."
    ),
    DOMAIN(
            identifier = "domain",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Sets the domain part (host and port) of URI provided in expression."
    ),
    LOCALE(
            identifier = "locale",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Overrides language from the source."
    ),
    CONTEXT(
            identifier = "context",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Escaping policy to apply on expression value."
    ),
    HINT(
            identifier = "hint",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Information about the context for the translators."
    ),
    JOIN(
            identifier = "join",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Separator to use when joining array elements."
    ),
    PATH(
            identifier = "path",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Sets resource path of URI provided in expression."
    ),
    PREPEND_PATH(
            identifier = "prependPath",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Path to add before resource path of URI provided in expression."
    ),
    APPEND_PATH(
            identifier = "appendPath",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Path to add after resource path of URI provided in expression."
    ),
    SELECTORS(
            identifier = "selectors",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Sets selectors part of URI provided in expression. Empty value removes all selectors."
    ),
    ADD_SELECTORS(
            identifier = "addSelectors",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Selectors to add to URI provided in expression."
    ),
    REMOVE_SELECTORS(
            identifier = "removeSelectors",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Selectors to remove from URI provided in expression."
    ),
    EXTENSION(
            identifier = "extension",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Sets the extension of URI provided in expression. Empty value removes the extension."
    ),
    SUFFIX(
            identifier = "suffix",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Sets the suffix part of URI provided in expression. Empty value removes the suffix."
    ),
    PREPEND_SUFFIX(
            identifier = "prependSuffix",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Suffix to add before the suffix part of URI provided in expression."
    ),
    APPEND_SUFFIX(
            identifier = "appendSuffix",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Suffix to add after the suffix part of URI provided in expression."
    ),
    QUERY(
            identifier = "query",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Sets the query part of URI provided in expression. Value should be a map. Empty value removes the query part."
    ),
    ADD_QUERY(
            identifier = "addQuery",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Adds or extends the query part of URI provided in expression. Value should be a map."
    ),
    REMOVE_QUERY(
            identifier = "removeQuery",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Identifiers of query parameters to remove from URI provided in expression."
    ),
    FRAGMENT(
            identifier = "fragment",
            insertHandler = HtlExprOptionQuotesInsertHandler,
            description = "Sets the fragment part of URI provided in expression. Empty value removes the fragment part."
    )

}
