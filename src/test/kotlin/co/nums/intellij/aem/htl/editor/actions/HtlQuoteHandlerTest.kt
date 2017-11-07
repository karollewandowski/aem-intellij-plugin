package co.nums.intellij.aem.htl.editor.actions

import co.nums.intellij.aem.htl.DOLLAR

class HtlQuoteHandlerTest : HtlTypingActionTestBase() {

    fun testShouldAutoCloseSingleQuote() = doTest(
            given = """$DOLLAR{<caret>}""",
            typeChar = '\'',
            expected = """$DOLLAR{'<caret>'}"""
    )

    fun testShouldSkipClosingSingleQuoteWhenTyped() = doTest(
            given = """$DOLLAR{'<caret>'}""",
            typeChar = '\'',
            expected = """$DOLLAR{''<caret>}"""
    )

    fun testShouldAutoCloseDoubleQuote() = doTest(
            given = """$DOLLAR{<caret>}""",
            typeChar = '"',
            expected = """$DOLLAR{"<caret>"}"""
    )

    fun testShouldSkipClosingDoubleQuoteWhenTyped() = doTest(
            given = """$DOLLAR{"<caret>"}""",
            typeChar = '"',
            expected = """$DOLLAR{""<caret>}"""
    )

}
