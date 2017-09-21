package co.nums.intellij.aem.htl.lexer

class HtlComplexLexingTest : HtlLexingTestCaseBase() {

    override fun getTestDataPath() = "co/nums/intellij/aem/htl/lexer/fixtures/complex"

    fun testEscapedExpressions() = doTest()
    fun testCommentedExpressions() = doTest()
    fun testTernaryOperator() = doTest()
    fun testTernaryOperatorBranches() = doTest()
    fun testAllTokensMixed() = doTest()

}
