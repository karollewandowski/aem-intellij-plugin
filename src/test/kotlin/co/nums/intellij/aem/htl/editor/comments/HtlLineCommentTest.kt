package co.nums.intellij.aem.htl.editor.comments

class HtlLineCommentTest : HtlCommenterTestBase("line/comment") {

    fun testSingleLineCommentAtBeginningOfLine() = doTest()
    fun testSingleLineCommentInMiddleOfLine() = doTest()
    fun testSingleLineCommentAtEndOfLine() = doTest()

    fun testMultiLineCommentAtBeginningOfFile() = doTest()
    fun testMultiLineCommentInMiddleOfFile() = doTest()
    fun testMultiLineCommentAtEndOfFile() = doTest()
    fun testMultiLineCommentForEntireFile() = doTest()

}
