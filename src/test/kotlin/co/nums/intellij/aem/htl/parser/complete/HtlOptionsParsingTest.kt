package co.nums.intellij.aem.htl.parser.complete

import co.nums.intellij.aem.htl.parser.HtlParsingTestCaseBase

class HtlOptionsParsingTest : HtlParsingTestCaseBase("complete/simple/options") {

    fun testMultipleOptions() = doTest()
    fun testOptionWithComparisons() = doTest()
    fun testOptionWithLogicalOperations() = doTest()
    fun testOptionsWithPropertyAccess() = doTest()
    fun testOptionWithTernaryOperator() = doTest()

}
