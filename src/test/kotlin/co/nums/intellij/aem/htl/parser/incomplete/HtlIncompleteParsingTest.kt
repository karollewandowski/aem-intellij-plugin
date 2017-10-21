package co.nums.intellij.aem.htl.parser.incomplete

import co.nums.intellij.aem.htl.parser.HtlParsingTestCaseBase

class HtlIncompleteParsingTest : HtlParsingTestCaseBase("incomplete") {

    fun testUnclosedSingleQuotedString() = doTest()
    fun testUnclosedDoubleQuotedString() = doTest()
    fun testUnclosedArray() = doTest()
    fun testUnclosedNestedArray() = doTest()
    fun testTernaryOperatorWithoutSecondBranch() = doTest()
    fun testUnclosedParentheses() = doTest()
    fun testUnclosedPropertyAccess() = doTest()
    fun testIncompleteDotPropertyAccess() = doTest()

}
