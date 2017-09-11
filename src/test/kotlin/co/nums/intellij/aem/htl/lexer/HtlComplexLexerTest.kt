package co.nums.intellij.aem.htl.lexer

import co.nums.intellij.aem.htl.HtlTestCase

class HtlComplexLexerTest : HtlLexingTestCaseBase(), HtlTestCase {

    override fun getTestDataPath() = "co/nums/intellij/aem/htl/lexer/fixtures/complex"

    fun testEscapedExpressions() = doTest()
    fun testCommentedExpressions() = doTest()
    fun testTernaryOperator() = doTest()
    fun testAllTokensMixed() = doTest()

}
