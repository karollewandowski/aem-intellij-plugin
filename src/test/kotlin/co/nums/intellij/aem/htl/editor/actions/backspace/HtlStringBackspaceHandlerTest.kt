package co.nums.intellij.aem.htl.editor.actions.backspace

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.editor.actions.HtlTypingActionTestBase

class HtlStringBackspaceHandlerTest : HtlTypingActionTestBase() {

    fun testShouldDeleteClosingSingleQuoteWhenStringIsEmptyAndFirstOneDeleted() = doTest(
            given = """$DOLLAR{'<caret>'}""",
            typeChar = '\b',
            expected = """$DOLLAR{<caret>}"""
    )

    fun testShouldNotDeleteOpeningSingleQuoteWhenStringIsEmptyAndClosingDeleted() = doTest(
            given = """$DOLLAR{''<caret>}""",
            typeChar = '\b',
            expected = """$DOLLAR{'<caret>}"""
    )

    fun testShouldDeleteClosingDoubleQuoteWhenStringIsEmptyAndFirstOneDeleted() = doTest(
            given = """$DOLLAR{"<caret>"}""",
            typeChar = '\b',
            expected = """$DOLLAR{<caret>}"""
    )

    fun testShouldNotDeleteOpeningDoubleQuoteWhenStringIsEmptyAndClosingDeleted() = doTest(
            given = """$DOLLAR{""<caret>}""",
            typeChar = '\b',
            expected = """$DOLLAR{"<caret>}"""
    )

}
