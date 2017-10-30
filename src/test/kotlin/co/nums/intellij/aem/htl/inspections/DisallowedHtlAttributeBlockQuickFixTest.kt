package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class DisallowedHtlAttributeBlockQuickFixTest : HtlAnnotatorTestBase() {

    // change attribute
    fun testChangeStyleAttributeCaretAtBeginning() = checkQuickFix(
            "Change 'data-sly-attribute.style' to 'style'",
            """<div <caret>data-sly-attribute.style="any"></div>""",
            """<div <caret>style="any"></div>""")

    fun testChangeStyleAttributeCaretInMiddle() = checkQuickFix(
            "Change 'data-sly-attribute.style' to 'style'",
            """<div data-sly-<caret>attribute.style="any"></div>""",
            """<div <caret>style="any"></div>""")

    fun testChangeStyleAttributeCaretAtEnd() = checkQuickFix(
            "Change 'data-sly-attribute.style' to 'style'",
            """<div data-sly-attribute.style<caret>="any"></div>""",
            """<div style<caret>="any"></div>""")

    fun testChangeOnClickDashedAttributeCaretAtBeginning() = checkQuickFix(
            "Change 'data-sly-attribute.on-click' to 'on-click'",
            """<div <caret>data-sly-attribute.on-click="any"></div>""",
            """<div <caret>on-click="any"></div>""")

    fun testChangeOnClickDashedAttributeCaretInMiddle() = checkQuickFix(
            "Change 'data-sly-attribute.on-click' to 'on-click'",
            """<div data-sly-<caret>attribute.on-click="any"></div>""",
            """<div <caret>on-click="any"></div>""")

    fun testChangeOnClickDashedAttributeCaretAtEnd() = checkQuickFix(
            "Change 'data-sly-attribute.on-click' to 'on-click'",
            """<div data-sly-attribute.on-click<caret>="any"></div>""",
            """<div on-click<caret>="any"></div>""")

    // remove attribute
    fun testRemoveStyleAttributeCaretAtBeginning() = checkQuickFix(
            "Remove 'data-sly-attribute.style'",
            """<div <caret>data-sly-attribute.style="any"></div>""",
            """<div <caret>></div>""")

    fun testRemoveStyleAttributeCaretInMiddle() = checkQuickFix(
            "Remove 'data-sly-attribute.style'",
            """<div data-sly-<caret>attribute.style="any"></div>""",
            """<div <caret>></div>""")

    fun testRemoveStyleAttributeCaretAtEnd() = checkQuickFix(
            "Remove 'data-sly-attribute.style'",
            """<div data-sly-attribute.style<caret>="any"></div>""",
            """<div <caret>></div>""")

    fun testRemoveOnClickDashedAttributeCaretAtBeginning() = checkQuickFix(
            "Remove 'data-sly-attribute.on-click'",
            """<div <caret>data-sly-attribute.on-click="any"></div>""",
            """<div <caret>></div>""")

    fun testRemoveOnClickDashedAttributeCaretInMiddle() = checkQuickFix(
            "Remove 'data-sly-attribute.on-click'",
            """<div data-sly-<caret>attribute.on-click="any"></div>""",
            """<div <caret>></div>""")

    fun testRemoveOnClickDashedAttributeCaretAtEnd() = checkQuickFix(
            "Remove 'data-sly-attribute.on-click'",
            """<div data-sly-attribute.on-click<caret>="any"></div>""",
            """<div <caret>></div>""")

}
