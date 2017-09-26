package co.nums.intellij.aem.htl.editor.comments

import com.intellij.openapi.actionSystem.IdeActions

class HtlBlockUncommentTest : HtlCommenterTestBase("block/uncomment") {

    override val commentType = IdeActions.ACTION_COMMENT_BLOCK

    fun testSingleLineEmptyBlockUncommentAtBeginningOfLine() = doTest()
    fun testSingleLineEmptyBlockUncommentAtEndOfLine() = doTest()

    fun testSingleLineBlockUncommentAtBeginningOfLine() = doTest()
    fun testSingleLineBlockUncommentInMiddleOfLine() = doTest()
    fun testSingleLineBlockUncommentAtEndOfLine() = doTest()

    fun testMultiLineBlockUncommentAtBeginningOfFile() = doTest()
    fun testMultiLineBlockUncommentInMiddleOfFile() = doTest()
    fun testMultiLineBlockUncommentAtEndOfFile() = doTest()

    fun testSingleLineBlockUncommentContainingSingleLineComment() = doTest()
    fun testMultiLineBlockUncommentContainingSingleLineComment() = doTest()
    fun testMultiLineBlockUncommentContainingMultiLineComment() = doTest()

    fun testMultiLineBlockUncommentEndingAtEmptyLine() = doTest()

}
