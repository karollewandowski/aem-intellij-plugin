package co.nums.intellij.aem.htl.editor.comments

class HtlLineUncommentTest : HtlCommenterTestBase("line/uncomment") {

    fun testSingleLineUncommentAtBeginningOfLine() = doTest()
    fun testSingleLineUncommentInMiddleOfLine() = doTest()
    fun testSingleLineUncommentAtEndOfLine() = doTest()

    fun testMultiLineUncommentAtBeginningOfFile() = doTest()
    fun testMultiLineUncommentInMiddleOfFile() = doTest()
    fun testMultiLineUncommentAtEndOfFile() = doTest()
    fun testMultiLineUncommentForEntireFile() = doTest()

}
