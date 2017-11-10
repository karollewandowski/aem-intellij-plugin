package co.nums.intellij.aem.htl.completion.insertHandler

import co.nums.intellij.aem.htl.DOLLAR

class HtlBlockInsertHandlersTest : HtlInsertHandlerTestBase() {

    fun testAttributeCompletion() = testCompletion(
            """<div data-sly-attribute<caret>""",
            """<div data-sly-attribute="$DOLLAR{<caret>}"""")

    fun testCallCompletion() = testCompletion(
            """<div data-sly-call<caret>""",
            """<div data-sly-call="$DOLLAR{<caret>}"""")

    fun testElementCompletion() = testCompletion(
            """<div data-sly-element<caret>""",
            """<div data-sly-element="$DOLLAR{<caret>}"""")

    fun testIncludeCompletion() = testCompletion(
            """<div data-sly-include<caret>""",
            """<div data-sly-include="<caret>"""")

    fun testListCompletion() = testCompletion(
            """<div data-sly-list<caret>""",
            """<div data-sly-list.="$DOLLAR{}"<caret>""")

    fun testRepeatCompletion() = testCompletion(
            """<div data-sly-repeat<caret>""",
            """<div data-sly-repeat.="$DOLLAR{}"<caret>""")

    fun testResourceCompletion() = testCompletion(
            """<div data-sly-resource<caret>""",
            """<div data-sly-resource="<caret>"""")

    fun testTemplateCompletion() = testCompletion(
            """<div data-sly-template<caret>""",
            """<div data-sly-template<caret>""")

    fun testTestCompletion() = testCompletion(
            """<div data-sly-test<caret>""",
            """<div data-sly-test="$DOLLAR{<caret>}"""")

    fun testTextCompletion() = testCompletion(
            """<div data-sly-text<caret>""",
            """<div data-sly-text="$DOLLAR{<caret>}"""")

    fun testUnwrapCompletion() = testCompletion(
            """<div data-sly-unwrap<caret>""",
            """<div data-sly-unwrap<caret>""")

    // TODO: test "live" templates (see HtlUseBlockInsertHandler) if possible
    fun testUseCompletion() = testCompletion(
            """<div data-sly-use<caret>""",
            """<div data-sly-use.=""<caret>""")

}
