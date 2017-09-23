package co.nums.intellij.aem.htl.parser.complete

import co.nums.intellij.aem.htl.parser.HtlParsingTestCaseBase

class HtlSimpleExpressionsParsingTest : HtlParsingTestCaseBase("complete/simple") {

    fun testEmptyExpression() = doTest()
    fun testVariable() = doTest()
    fun testNegatedValue() = doTest()
    fun testArrayOfIntegerNumbers() = doTest()
    fun testArrayOfFloatNumbers() = doTest()
    fun testArrayOfStrings() = doTest()
    fun testArrayOfArrays() = doTest()
    fun testArrayOfMixedValues() = doTest()
    fun testEmptyArray() = doTest()
    fun testLogicalAndOperation() = doTest()
    fun testLogicalOrOperation() = doTest()
    fun testTernaryOperator() = doTest()

}
