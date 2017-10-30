package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlWrongStringQuotesQuickFixTest : HtlAnnotatorTestBase() {

    fun testEmptySingleQuotedStringCaretAtBeginning() = checkFixQuotesQuickFix(
            """<div data-sly-text='$DOLLAR{<caret>''}'></div>""",
            """<div data-sly-text='$DOLLAR{<caret>""}'></div>"""
    )

    fun testEmptySingleQuotedStringCaretInMiddle() = checkFixQuotesQuickFix(
            """<div data-sly-text='$DOLLAR{'<caret>'}'></div>""",
            """<div data-sly-text='$DOLLAR{"<caret>"}'></div>"""
    )

    fun testEmptySingleQuotedStringCaretAtEnd() = checkFixQuotesQuickFix(
            """<div data-sly-text='$DOLLAR{''<caret>}'></div>""",
            """<div data-sly-text='$DOLLAR{""<caret>}'></div>"""
    )

    fun testEmptyDoubleQuotedStringCaretAtBeginning() = checkFixQuotesQuickFix(
            """<div data-sly-text="$DOLLAR{<caret>""}"></div>""",
            """<div data-sly-text="$DOLLAR{<caret>''}"></div>"""
    )

    fun testEmptyDoubleQuotedStringCaretInMiddle() = checkFixQuotesQuickFix(
            """<div data-sly-text="$DOLLAR{"<caret>"}"></div>""",
            """<div data-sly-text="$DOLLAR{'<caret>'}"></div>"""
    )

    fun testEmptyDoubleQuotedStringCaretAtEnd() = checkFixQuotesQuickFix(
            """<div data-sly-text="$DOLLAR{""<caret>}"></div>""",
            """<div data-sly-text="$DOLLAR{''<caret>}"></div>"""
    )

    fun testNotEmptySingleQuotedStringCaretAtBeginning() = checkFixQuotesQuickFix(
            """<div data-sly-text='$DOLLAR{<caret>'not empty string'}'></div>""",
            """<div data-sly-text='$DOLLAR{<caret>"not empty string"}'></div>"""
    )

    fun testNotEmptySingleQuotedStringCaretInMiddle() = checkFixQuotesQuickFix(
            """<div data-sly-text='$DOLLAR{'not emp<caret>ty string'}'></div>""",
            """<div data-sly-text='$DOLLAR{"not emp<caret>ty string"}'></div>"""
    )

    fun testNotEmptySingleQuotedStringCaretAtEnd() = checkFixQuotesQuickFix(
            """<div data-sly-text='$DOLLAR{'not empty string'<caret>}'></div>""",
            """<div data-sly-text='$DOLLAR{"not empty string"<caret>}'></div>"""
    )

    fun testNotEmptyDoubleQuotedStringCaretAtBeginning() = checkFixQuotesQuickFix(
            """<div data-sly-text="$DOLLAR{<caret>"not empty string"}"></div>""",
            """<div data-sly-text="$DOLLAR{<caret>'not empty string'}"></div>"""
    )

    fun testNotEmptyDoubleQuotedStringCaretInMiddle() = checkFixQuotesQuickFix(
            """<div data-sly-text="$DOLLAR{"not emp<caret>ty string"}"></div>""",
            """<div data-sly-text="$DOLLAR{'not emp<caret>ty string'}"></div>"""
    )

    fun testNotEmptyDoubleQuotedStringCaretAtEnd() = checkFixQuotesQuickFix(
            """<div data-sly-text="$DOLLAR{"not empty string"<caret>}"></div>""",
            """<div data-sly-text="$DOLLAR{'not empty string'<caret>}"></div>"""
    )

    private fun checkFixQuotesQuickFix(before: String, after: String) = checkQuickFix("Fix quotes", before, after)

}
