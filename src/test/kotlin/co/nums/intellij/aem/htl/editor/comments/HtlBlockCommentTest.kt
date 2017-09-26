package co.nums.intellij.aem.htl.editor.comments

import com.intellij.openapi.actionSystem.IdeActions

class HtlBlockCommentTest : HtlCommenterTestBase("block/comment") {

    override val commentType = IdeActions.ACTION_COMMENT_BLOCK

    fun testSingleLineEmptyBlockCommentAtBeginningOfLine() = doTest()
    fun testSingleLineEmptyBlockCommentInMiddleOfLine() = doTest()
    fun testSingleLineEmptyBlockCommentAtEndOfLine() = doTest()

    fun testSingleLineBlockCommentAtBeginningOfLine() = doTest()
    fun testSingleLineBlockCommentInMiddleOfLine() = doTest()
    fun testSingleLineBlockCommentAtEndOfLine() = doTest()

    fun testMultiLineBlockCommentAtBeginningOfFile() = doTest()
    fun testMultiLineBlockCommentInMiddleOfFile() = doTest()
    fun testMultiLineBlockCommentAtEndOfFile() = doTest()

    fun testSingleLineBlockCommentContainingSingleLineComment() = doTest()
    fun testMultiLineBlockCommentContainingSingleLineComment() = doTest()
    fun testMultiLineBlockCommentContainingMultiLineComment() = doTest()

    fun testMultiLineBlockCommentEndingAtEmptyLine() = doTest()

}
