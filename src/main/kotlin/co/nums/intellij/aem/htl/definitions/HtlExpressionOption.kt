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
            description = "Value(s) to apply on the formatting pattern provided in expression.",
            insertHandler = HtlExprOptionBracketsInsertHandler
    ),
    SCHEME(
            identifier = "scheme",
            description = "Sets the scheme part of URI provided in expression. Empty value removes the scheme.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    DOMAIN(
            identifier = "domain",
            description = "Sets the domain part (host and port) of URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    LOCALE(
            identifier = "locale",
            description = "Overrides language from the source.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    CONTEXT(
            identifier = "context",
            description = "Escaping policy to apply on expression value.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    HINT(
            identifier = "hint",
            description = "Information about the context for the translators.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    JOIN(
            identifier = "join",
            description = "Separator to use when joining array elements.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    PATH(
            identifier = "path",
            description = "Sets resource path of URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    PREPEND_PATH(
            identifier = "prependPath",
            description = "Path to add before resource path of URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    APPEND_PATH(
            identifier = "appendPath",
            description = "Path to add after resource path of URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    SELECTORS(
            identifier = "selectors",
            description = "Sets selectors part of URI provided in expression. Empty value removes all selectors.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    ADD_SELECTORS(
            identifier = "addSelectors",
            description = "Selectors to add to URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    REMOVE_SELECTORS(
            identifier = "removeSelectors",
            description = "Selectors to remove from URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    EXTENSION(
            identifier = "extension",
            description = "Sets the extension of URI provided in expression. Empty value removes the extension.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    SUFFIX(
            identifier = "suffix",
            description = "Sets the suffix part of URI provided in expression. Empty value removes the suffix.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    PREPEND_SUFFIX(
            identifier = "prependSuffix",
            description = "Suffix to add before the suffix part of URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    APPEND_SUFFIX(
            identifier = "appendSuffix",
            description = "Suffix to add after the suffix part of URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    QUERY(
            identifier = "query",
            description = "Sets the query part of URI provided in expression. Value should be a map. Empty value removes the query part.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    ADD_QUERY(
            identifier = "addQuery",
            description = "Adds or extends the query part of URI provided in expression. Value should be a map.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    REMOVE_QUERY(
            identifier = "removeQuery",
            description = "Identifiers of query parameters to remove from URI provided in expression.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    ),
    FRAGMENT(
            identifier = "fragment",
            description = "Sets the fragment part of URI provided in expression. Empty value removes the fragment part.",
            insertHandler = HtlExprOptionQuotesInsertHandler
    )

}
