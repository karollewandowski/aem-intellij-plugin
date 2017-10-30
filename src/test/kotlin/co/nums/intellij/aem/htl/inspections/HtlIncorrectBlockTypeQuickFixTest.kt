package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlIncorrectBlockTypeQuickFixTest : HtlAnnotatorTestBase() {

    fun testDataSlyPrefixCaretAtBeginning() = checkQuickFix(
            "Change to 'data-sly-attribute'",
            """<div <caret>data-sly-=""></div>""",
            """<div <caret>data-sly-attribute=""></div>"""
    )

    fun testDataSlyPrefixCaretInMiddle() = checkQuickFix(
            "Change to 'data-sly-attribute'",
            """<div data-s<caret>ly-=""></div>""",
            """<div data-s<caret>ly-attribute=""></div>"""
    )

    fun testDataSlyPrefixCaretAtEnd() = checkQuickFix(
            "Change to 'data-sly-attribute'",
            """<div data-sly-<caret>=""></div>""",
            """<div data-sly-<caret>attribute=""></div>"""
    )

    fun testDataSlyTstTypo() = checkQuickFix(
            "Change to 'data-sly-test'",
            """<div data-sly-tst<caret>=""></div>""",
            """<div data-sly-test<caret>=""></div>"""
    )

    fun testUnfinishedDataSlyResourc() = checkQuickFix(
            "Change to 'data-sly-resource'",
            """<div data-sly-resourc<caret>=""></div>""",
            """<div data-sly-resourc<caret>e=""></div>"""
    )

}
