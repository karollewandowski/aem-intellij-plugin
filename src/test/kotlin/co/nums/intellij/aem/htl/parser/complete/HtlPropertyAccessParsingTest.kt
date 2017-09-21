package co.nums.intellij.aem.htl.parser.complete

import co.nums.intellij.aem.htl.parser.HtlParsingTestCaseBase

class HtlPropertyAccessParsingTest : HtlParsingTestCaseBase("complete/simple/property_access") {

    fun testDotPropertyAccess() = doTest()
    fun testMultipleDotPropertyAccesses() = doTest()
    fun testBracketPropertyAccess() = doTest()
    fun testMultipleBracketPropertyAccesses() = doTest()
    fun testVariableBracketPropertyAccess() = doTest()
    fun testMultipleVariableBracketPropertyAccesses() = doTest()
    fun testExpressionBracketPropertyAccess() = doTest()
    fun testNumberBracketPropertyAccess() = doTest()
    fun testMultipleNumberBracketPropertyAccesses() = doTest()
    fun testMixedPropertyAccesses() = doTest()

}
