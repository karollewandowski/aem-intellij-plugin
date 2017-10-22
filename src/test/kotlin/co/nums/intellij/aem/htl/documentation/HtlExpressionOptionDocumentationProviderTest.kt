package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlExpressionOptionDocumentationProviderTest : HtlDocumentationProviderTest() {

    fun testI18nOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ i18n='any'}
                        ^
            """,
            "Translates string to the resource language."
    )

    fun testFormatOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ format='any'}
                          ^
            """,
            "Value(s) to apply on the formatting pattern provided in expression."
    )

    fun testSchemeOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ scheme='any'}
                          ^
            """,
            "Sets the scheme part of URI provided in expression. Empty value removes the scheme."
    )

    fun testDomainOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ domain='any'}
                         ^
            """,
            "Sets the domain part (host and port) of URI provided in expression."
    )

    fun testLocaleOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ locale='any'}
                         ^
            """,
            "Overrides language from the source."
    )

    fun testContextOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='any'}
                          ^
            """,
            "Escaping policy to apply on expression value."
    )

    fun testHintOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ hint='any'}
                        ^
            """,
            "Information about the context for the translators."
    )

    fun testJoinOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ join='any'}
                        ^
            """,
            "Separator to use when joining array elements."
    )

    fun testPathOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ path='any'}
                        ^
            """,
            "Sets resource path of URI provided in expression."
    )

    fun testPrependPathOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ prependPath='any'}
                            ^
            """,
            "Path to add before resource path of URI provided in expression."
    )

    fun testAppendPathOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ appendPath='any'}
                           ^
            """,
            "Path to add after resource path of URI provided in expression."
    )

    fun testSelectorsOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ selectors='any'}
                           ^
            """,
            "Sets selectors part of URI provided in expression. Empty value removes all selectors."
    )

    fun testAddSelectorsOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ addSelectors='any'}
                           ^
            """,
            "Selectors to add to URI provided in expression."
    )

    fun testRemoveSelectorsOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ removeSelectors='any'}
                            ^
            """,
            "Selectors to remove from URI provided in expression."
    )

    fun testExtensionOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ extension='any'}
                           ^
            """,
            "Sets the extension of URI provided in expression. Empty value removes the extension."
    )

    fun testSuffixOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ suffix='any'}
                         ^
            """,
            "Sets the suffix part of URI provided in expression. Empty value removes the suffix."
    )

    fun testPrependSuffixOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ prependSuffix='any'}
                            ^
            """,
            "Suffix to add before the suffix part of URI provided in expression."
    )

    fun testAppendSuffixOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ appendSuffix='any'}
                           ^
            """,
            "Suffix to add after the suffix part of URI provided in expression."
    )

    fun testQueryOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ query='any'}
                         ^
            """,
            "Sets the query part of URI provided in expression. Value should be a map. Empty value removes the query part."
    )

    fun testAddQueryOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ addQuery='any'}
                          ^
            """,
            "Adds or extends the query part of URI provided in expression. Value should be a map."
    )

    fun testRemoveQueryOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ removeQuery='any'}
                            ^
            """,
            "Identifiers of query parameters to remove from URI provided in expression."
    )

    fun testFragmentOptionDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ fragment='any'}
                          ^
            """,
            "Sets the fragment part of URI provided in expression. Empty value removes the fragment part."
    )

}
