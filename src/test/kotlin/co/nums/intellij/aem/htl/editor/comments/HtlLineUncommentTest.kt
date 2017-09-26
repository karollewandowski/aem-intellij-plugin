package co.nums.intellij.aem.htl.editor.comments

import com.intellij.openapi.actionSystem.IdeActions

class HtlLineUncommentTest : HtlCommenterTestBase("line/uncomment") {

    override val commentType = IdeActions.ACTION_COMMENT_LINE

    fun testSingleLineUncommentAtBeginningOfLine() = doTest()
    fun testSingleLineUncommentInMiddleOfLine() = doTest()
    fun testSingleLineUncommentAtEndOfLine() = doTest()
    fun testSingleLineUncommentContainingSingleLineComment() = doTest()

    fun testMultiLineUncommentAtBeginningOfFile() = doTest()
    fun testMultiLineUncommentInMiddleOfFile() = doTest()
    fun testMultiLineUncommentAtEndOfFile() = doTest()
    fun testMultiLineUncommentForEntireFile() = doTest()

}
