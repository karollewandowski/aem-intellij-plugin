package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlWrongStringQuotesQuickFixTest : HtlAnnotatorTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/inspections/wrong_string_quotes/fixes/fixtures"
    override val relativeDataPath = "jcr_root"

    fun testEmptySingleQuotedStringCaretAtBeginning() = checkFixQuotesQuickFix()
    fun testEmptySingleQuotedStringCaretInMiddle() = checkFixQuotesQuickFix()
    fun testEmptySingleQuotedStringCaretAtEnd() = checkFixQuotesQuickFix()

    fun testEmptyDoubleQuotedStringCaretAtBeginning() = checkFixQuotesQuickFix()
    fun testEmptyDoubleQuotedStringCaretInMiddle() = checkFixQuotesQuickFix()
    fun testEmptyDoubleQuotedStringCaretAtEnd() = checkFixQuotesQuickFix()

    fun testNotEmptySingleQuotedStringCaretAtBeginning() = checkFixQuotesQuickFix()
    fun testNotEmptySingleQuotedStringCaretInMiddle() = checkFixQuotesQuickFix()
    fun testNotEmptySingleQuotedStringCaretAtEnd() = checkFixQuotesQuickFix()

    fun testNotEmptyDoubleQuotedStringCaretAtBeginning() = checkFixQuotesQuickFix()
    fun testNotEmptyDoubleQuotedStringCaretInMiddle() = checkFixQuotesQuickFix()
    fun testNotEmptyDoubleQuotedStringCaretAtEnd() = checkFixQuotesQuickFix()

    private fun checkFixQuotesQuickFix() = checkQuickFix("Fix quotes")

}
