package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlIncorrectBlockTypeQuickFixTest : HtlAnnotatorTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/inspections/incorrect_htl_block/fixes/fixtures"

    fun testDataSlyPrefixCaretAtBeginning() = checkQuickFix("Change to 'data-sly-attribute'")
    fun testDataSlyPrefixCaretInMiddle() = checkQuickFix("Change to 'data-sly-attribute'")
    fun testDataSlyPrefixCaretAtEnd() = checkQuickFix("Change to 'data-sly-attribute'")
    fun testDataSlyTstTypo() = checkQuickFix("Change to 'data-sly-test'")
    fun testUnfinishedDataSlyResourc() = checkQuickFix("Change to 'data-sly-resource'")

}
