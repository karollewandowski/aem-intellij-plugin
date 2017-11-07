package co.nums.intellij.aem.htl.editor.braces

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.editor.actions.HtlTypingActionTestBase

class HtlBraceMatcherTest : HtlTypingActionTestBase() {

    fun testShouldAutoCloseExpressionEndBrace() = doTest(
            given = """$DOLLAR<caret>""",
            typeChar = '{',
            expected = """$DOLLAR{<caret>}"""
    )

    fun testShouldSkipExpressionEndBraceWhenTyped() = doTest(
            given = """$DOLLAR{<caret>}""",
            typeChar = '}',
            expected = """$DOLLAR{}<caret>"""
    )

    fun testShouldAutoCloseParentheses() = doTest(
            given = """$DOLLAR{<caret>}""",
            typeChar = '(',
            expected = """$DOLLAR{(<caret>)}"""
    )

    fun testShouldSkipClosingParenthesesWhenTyped() = doTest(
            given = """$DOLLAR{(<caret>)}""",
            typeChar = ')',
            expected = """$DOLLAR{()<caret>}"""
    )

    fun testShouldAutoCloseBracket() = doTest(
            given = """$DOLLAR{<caret>}""",
            typeChar = '[',
            expected = """$DOLLAR{[<caret>]}"""
    )

    fun testShouldSkipClosingBracketWhenTyped() = doTest(
            given = """$DOLLAR{[<caret>]}""",
            typeChar = ']',
            expected = """$DOLLAR{[]<caret>}"""
    )

}
