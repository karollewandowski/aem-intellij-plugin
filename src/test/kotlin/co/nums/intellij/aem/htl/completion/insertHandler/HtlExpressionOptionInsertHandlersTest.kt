package co.nums.intellij.aem.htl.completion.insertHandler

import co.nums.intellij.aem.htl.DOLLAR

class HtlExpressionOptionInsertHandlersTest : HtlInsertHandlerTestBase() {

    fun testI18nCompletion() = testCompletion(
            "<div>$DOLLAR{ @ i18n<caret>}",
            "<div>$DOLLAR{ @ i18n<caret>}")

    fun testFormatCompletion() = testCompletion(
            "<div>$DOLLAR{ @ format<caret>}",
            "<div>$DOLLAR{ @ format=[<caret>]}")

    fun testSchemeCompletion() = testCompletion(
            "<div>$DOLLAR{ @ scheme<caret>}",
            "<div>$DOLLAR{ @ scheme='<caret>'}")

    fun testDomainCompletion() = testCompletion(
            "<div>$DOLLAR{ @ domain<caret>}",
            "<div>$DOLLAR{ @ domain='<caret>'}")

    fun testLocaleCompletion() = testCompletion(
            "<div>$DOLLAR{ @ locale<caret>}",
            "<div>$DOLLAR{ @ locale='<caret>'}")

    fun testContextCompletion() = testCompletion(
            "<div>$DOLLAR{ @ context<caret>}",
            "<div>$DOLLAR{ @ context='<caret>'}")

    fun testHintCompletion() = testCompletion(
            "<div>$DOLLAR{ @ hint<caret>}",
            "<div>$DOLLAR{ @ hint='<caret>'}")

    fun testJoinCompletion() = testCompletion(
            "<div>$DOLLAR{ @ join<caret>}",
            "<div>$DOLLAR{ @ join='<caret>'}")

    fun testPathCompletion() = testCompletionSelectFirstItem(
            "<div>$DOLLAR{ @ path<caret>}",
            "<div>$DOLLAR{ @ path='<caret>'}")

    fun testPrependPathCompletion() = testCompletion(
            "<div>$DOLLAR{ @ prependPath<caret>}",
            "<div>$DOLLAR{ @ prependPath='<caret>'}")

    fun testAppendPathCompletion() = testCompletion(
            "<div>$DOLLAR{ @ appendPath<caret>}",
            "<div>$DOLLAR{ @ appendPath='<caret>'}")

    fun testSelectorsCompletion() = testCompletionSelectFirstItem(
            "<div>$DOLLAR{ @ selectors<caret>}",
            "<div>$DOLLAR{ @ selectors='<caret>'}")

    fun testAddSelectorsCompletion() = testCompletion(
            "<div>$DOLLAR{ @ addSelectors<caret>}",
            "<div>$DOLLAR{ @ addSelectors='<caret>'}")

    fun testRemoveSelectorsCompletion() = testCompletion(
            "<div>$DOLLAR{ @ removeSelectors<caret>}",
            "<div>$DOLLAR{ @ removeSelectors='<caret>'}")

    fun testExtensionCompletion() = testCompletion(
            "<div>$DOLLAR{ @ extension<caret>}",
            "<div>$DOLLAR{ @ extension='<caret>'}")

    fun testSuffixCompletion() = testCompletionSelectFirstItem(
            "<div>$DOLLAR{ @ suffix<caret>}",
            "<div>$DOLLAR{ @ suffix='<caret>'}")

    fun testPrependSuffixCompletion() = testCompletion(
            "<div>$DOLLAR{ @ prependSuffix<caret>}",
            "<div>$DOLLAR{ @ prependSuffix='<caret>'}")

    fun testAppendSuffixCompletion() = testCompletion(
            "<div>$DOLLAR{ @ appendSuffix<caret>}",
            "<div>$DOLLAR{ @ appendSuffix='<caret>'}")

    fun testQueryCompletion() = testCompletionSelectFirstItem(
            "<div>$DOLLAR{ @ query<caret>}",
            "<div>$DOLLAR{ @ query='<caret>'}")

    fun testAddQueryCompletion() = testCompletion(
            "<div>$DOLLAR{ @ addQuery<caret>}",
            "<div>$DOLLAR{ @ addQuery='<caret>'}")

    fun testRemoveQueryCompletion() = testCompletion(
            "<div>$DOLLAR{ @ removeQuery<caret>}",
            "<div>$DOLLAR{ @ removeQuery='<caret>'}")

    fun testFragmentCompletion() = testCompletion(
            "<div>$DOLLAR{ @ fragment<caret>}",
            "<div>$DOLLAR{ @ fragment='<caret>'}")

}
