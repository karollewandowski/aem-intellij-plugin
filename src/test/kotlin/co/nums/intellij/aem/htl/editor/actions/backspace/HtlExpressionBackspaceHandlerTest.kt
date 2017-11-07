package co.nums.intellij.aem.htl.editor.actions.backspace

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.editor.actions.HtlTypingActionTestBase

class HtlExpressionBackspaceHandlerTest: HtlTypingActionTestBase() {

    fun testShouldDeleteExpressionEndBraceWhenStartBraceDeleted() = doTest(
            given = """$DOLLAR{<caret>}""",
            typeChar = '\b',
            expected = """$DOLLAR<caret>"""
    )

    fun testShouldNotDeleteExpressionStartBraceWhenEndBraceDeleted() = doTest(
            given = """$DOLLAR{}<caret>""",
            typeChar = '\b',
            expected = """$DOLLAR{<caret>"""
    )

}
