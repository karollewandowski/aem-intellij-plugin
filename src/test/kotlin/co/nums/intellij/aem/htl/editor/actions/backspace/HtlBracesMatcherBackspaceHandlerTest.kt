package co.nums.intellij.aem.htl.editor.actions.backspace

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.editor.actions.HtlTypingActionTestBase

class HtlBracesMatcherBackspaceHandlerTest : HtlTypingActionTestBase() {

    fun testShouldDeleteClosingParenthesesWhenBeginningDeleted() = doTest(
            given = """$DOLLAR{(<caret>)}""",
            typeChar = '\b',
            expected = """$DOLLAR{<caret>}"""
    )

    fun testShouldNotDeleteOpeningParenthesesWhenClosingDeleted() = doTest(
            given = """$DOLLAR{()<caret>}""",
            typeChar = '\b',
            expected = """$DOLLAR{(<caret>}"""
    )

    fun testShouldDeleteClosingBracketWhenBeginningDeleted() = doTest(
            given = """$DOLLAR{[<caret>]}""",
            typeChar = '\b',
            expected = """$DOLLAR{<caret>}"""
    )

    fun testShouldNotDeleteOpeningBracketWhenClosingDeleted() = doTest(
            given = """$DOLLAR{[]<caret>}""",
            typeChar = '\b',
            expected = """$DOLLAR{[<caret>}"""
    )

}
