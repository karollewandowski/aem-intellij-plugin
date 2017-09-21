package co.nums.intellij.aem.htl.parser.complete

import co.nums.intellij.aem.htl.parser.HtlParsingTestCaseBase

class HtlComparisonsParsingTest : HtlParsingTestCaseBase("complete/simple/comparisons") {

    fun testEqual() = doTest()
    fun testNotEqual() = doTest()
    fun testLower() = doTest()
    fun testLowerEqual() = doTest()
    fun testGreater() = doTest()
    fun testGreaterEqual() = doTest()

}
