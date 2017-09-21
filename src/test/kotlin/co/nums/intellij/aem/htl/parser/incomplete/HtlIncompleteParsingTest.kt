package co.nums.intellij.aem.htl.parser.incomplete

import co.nums.intellij.aem.htl.parser.HtlParsingTestCaseBase

class HtlIncompleteParsingTest : HtlParsingTestCaseBase("incomplete") {

    fun testUnclosedSingleQuotedString() = doTest()
    fun testUnclosedDoubleQuotedString() = doTest()
    fun testUnclosedArray() = doTest()
    fun testUnclosedParentheses() = doTest()
    fun testTernaryOperatorWithoutSecondBranch() = doTest()
    // testUnclosedPropertyAccess
    // testUnclosedNestedArray?

}
