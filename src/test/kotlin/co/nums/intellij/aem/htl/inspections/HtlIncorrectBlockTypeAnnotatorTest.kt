package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlIncorrectBlockTypeAnnotatorTest : HtlAnnotatorTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/inspections/incorrect_htl_block/annotations/fixtures"
    override val relativeDataPath = "jcr_root"

    fun testDataSlyPrefix() = checkAnnotation()
    fun testDataSlyTstTypo() = checkAnnotation()
    fun testUnfinishedDataSlyResourc() = checkAnnotation()
    fun testMultipleAnnotationsInSingleFile() = checkAnnotation()

}
