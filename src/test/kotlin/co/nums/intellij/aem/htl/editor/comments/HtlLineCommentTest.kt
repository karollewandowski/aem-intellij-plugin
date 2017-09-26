package co.nums.intellij.aem.htl.editor.comments

import com.intellij.openapi.actionSystem.IdeActions

class HtlLineCommentTest : HtlCommenterTestBase("line/comment") {

    override val commentType = IdeActions.ACTION_COMMENT_LINE

    fun testSingleLineCommentAtBeginningOfLine() = doTest()
    fun testSingleLineCommentInMiddleOfLine() = doTest()
    fun testSingleLineCommentAtEndOfLine() = doTest()
    fun testSingleLineCommentContainingSingleLineComment() = doTest()

    fun testMultiLineCommentAtBeginningOfFile() = doTest()
    fun testMultiLineCommentInMiddleOfFile() = doTest()
    fun testMultiLineCommentAtEndOfFile() = doTest()
    fun testMultiLineCommentForEntireFile() = doTest()

}
