package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlExpressionOptionDocumentationProviderTest : HtlDocumentationProviderTest() {

    fun testI18nOptionDoc() = doTest(
            """
            $DOLLAR{ @ i18n='any'}
                        ^
            """,
            "Translates string to the resource language."
    )

    fun testFormatOptionDoc() = doTest(
            """
            $DOLLAR{ @ format='any'}
                          ^
            """,
            "Value(s) to apply on the formatting pattern provided in expression."
    )

    fun testSchemeOptionDoc() = doTest(
            """
            $DOLLAR{ @ scheme='any'}
                          ^
            """,
            "Sets the scheme part of URI provided in expression. Empty value removes the scheme."
    )

    fun testDomainOptionDoc() = doTest(
            """
            $DOLLAR{ @ domain='any'}
                         ^
            """,
            "Sets the domain part (host and port) of URI provided in expression."
    )

    fun testLocaleOptionDoc() = doTest(
            """
            $DOLLAR{ @ locale='any'}
                         ^
            """,
            "Overrides language from the source."
    )

    fun testContextOptionDoc() = doTest(
            """
            $DOLLAR{ @ context='any'}
                          ^
            """,
            "Escaping policy to apply on expression value."
    )

    fun testHintOptionDoc() = doTest(
            """
            $DOLLAR{ @ hint='any'}
                        ^
            """,
            "Information about the context for the translators."
    )

    fun testJoinOptionDoc() = doTest(
            """
            $DOLLAR{ @ join='any'}
                        ^
            """,
            "Separator to use when joining array elements."
    )

    fun testPathOptionDoc() = doTest(
            """
            $DOLLAR{ @ path='any'}
                        ^
            """,
            "Sets resource path of URI provided in expression."
    )

    fun testPrependPathOptionDoc() = doTest(
            """
            $DOLLAR{ @ prependPath='any'}
                            ^
            """,
            "Path to add before resource path of URI provided in expression."
    )

    fun testAppendPathOptionDoc() = doTest(
            """
            $DOLLAR{ @ appendPath='any'}
                           ^
            """,
            "Path to add after resource path of URI provided in expression."
    )

    fun testSelectorsOptionDoc() = doTest(
            """
            $DOLLAR{ @ selectors='any'}
                           ^
            """,
            "Sets selectors part of URI provided in expression. Empty value removes all selectors."
    )

    fun testAddSelectorsOptionDoc() = doTest(
            """
            $DOLLAR{ @ addSelectors='any'}
                           ^
            """,
            "Selectors to add to URI provided in expression."
    )

    fun testRemoveSelectorsOptionDoc() = doTest(
            """
            $DOLLAR{ @ removeSelectors='any'}
                            ^
            """,
            "Selectors to remove from URI provided in expression."
    )

    fun testExtensionOptionDoc() = doTest(
            """
            $DOLLAR{ @ extension='any'}
                           ^
            """,
            "Sets the extension of URI provided in expression. Empty value removes the extension."
    )

    fun testSuffixOptionDoc() = doTest(
            """
            $DOLLAR{ @ suffix='any'}
                         ^
            """,
            "Sets the suffix part of URI provided in expression. Empty value removes the suffix."
    )

    fun testPrependSuffixOptionDoc() = doTest(
            """
            $DOLLAR{ @ prependSuffix='any'}
                            ^
            """,
            "Suffix to add before the suffix part of URI provided in expression."
    )

    fun testAppendSuffixOptionDoc() = doTest(
            """
            $DOLLAR{ @ appendSuffix='any'}
                           ^
            """,
            "Suffix to add after the suffix part of URI provided in expression."
    )

    fun testQueryOptionDoc() = doTest(
            """
            $DOLLAR{ @ query='any'}
                         ^
            """,
            "Sets the query part of URI provided in expression. Value should be a map. Empty value removes the query part."
    )

    fun testAddQueryOptionDoc() = doTest(
            """
            $DOLLAR{ @ addQuery='any'}
                          ^
            """,
            "Adds or extends the query part of URI provided in expression. Value should be a map."
    )

    fun testRemoveQueryOptionDoc() = doTest(
            """
            $DOLLAR{ @ removeQuery='any'}
                            ^
            """,
            "Identifiers of query parameters to remove from URI provided in expression."
    )

    fun testFragmentOptionDoc() = doTest(
            """
            $DOLLAR{ @ fragment='any'}
                          ^
            """,
            "Sets the fragment part of URI provided in expression. Empty value removes the fragment part."
    )

}
