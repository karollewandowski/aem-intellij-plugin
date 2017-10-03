package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlWrongStringQuotesAnnotatorTest : HtlAnnotatorTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/inspections/wrong_string_quotes/annotations/fixtures"
    override val relativeDataPath = "jcr_root"

    fun testEmptySingleQuotedString() = checkAnnotation()
    fun testEmptyDoubleQuotedString() = checkAnnotation()
    fun testNotEmptySingleQuotedString() = checkAnnotation()
    fun testNotEmptyDoubleQuotedString() = checkAnnotation()
    fun testMultipleWrongQuotesStringInSingleFile() = checkAnnotation()

}
