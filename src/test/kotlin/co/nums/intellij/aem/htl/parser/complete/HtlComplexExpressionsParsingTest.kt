package co.nums.intellij.aem.htl.parser.complete

import co.nums.intellij.aem.htl.parser.HtlParsingTestCaseBase

class HtlComplexExpressionsParsingTest : HtlParsingTestCaseBase("complete/complex") {

    fun testLogicalOperationsWithComparisons() = doTest()
    fun testLogicalOperationWithNestedTernaryOperator() = doTest()
    fun testTernaryOperatorWithLogicalOperation() = doTest()
    fun testNegatedComparisonsInLogicalOperations() = doTest()
    fun testTernaryOperatorWithBinaryOperationWithComparisons() = doTest()
    fun testNegatedTernaryOperator() = doTest()
    fun testDefaultStringWithOption() = doTest()

}
