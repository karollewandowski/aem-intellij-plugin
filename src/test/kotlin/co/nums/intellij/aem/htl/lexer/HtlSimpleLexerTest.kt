package co.nums.intellij.aem.htl.lexer

import co.nums.intellij.aem.htl.HtlTestCase

class HtlSimpleLexerTest : HtlLexingTestCaseBase(), HtlTestCase {

    override fun getTestDataPath() = "co/nums/intellij/aem/htl/lexer/fixtures/simple"

    // not expressions
    fun testEscapedExpressions() = doTest()
    fun testHtlComment() = doTest()

    // literals
    fun testBooleanLiterals() = doTest()
    fun testStringLiterals() = doTest()
    fun testSingleQuotedUnclosedStringLiteral() = doTest()
    fun testDoubleQuotedUnclosedStringLiteral() = doTest()
    fun testNumberLiterals() = doTest()
    fun testIdentifierLiterals() = doTest()

    // operators
    fun testLogicalOperators() = doTest()
    fun testComparisonOperators() = doTest()
    fun testDotOperator() = doTest()
    fun testAssignOperator() = doTest()
    fun testTernaryOperatorParts() = doTest()

    // other
    fun testParentheses() = doTest()
    fun testBrackets() = doTest()
    fun testOptionsSeparator() = doTest()
    fun testCommaSeparator() = doTest()

}
