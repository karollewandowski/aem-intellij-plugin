package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlWrongStringQuotesAnnotatorTest : HtlAnnotatorTestBase() {

    fun testEmptySingleQuotedString() = checkAnnotations(
            """<div data-sly-text='$DOLLAR{<error descr="Quotes must differ from outer attribute's quotes">''</error>}'></div>""")

    fun testEmptyDoubleQuotedString() = checkAnnotations(
            """<div data-sly-text="$DOLLAR{<error descr="Quotes must differ from outer attribute's quotes">""</error>}"></div>""")

    fun testNotEmptySingleQuotedString() = checkAnnotations(
            """<div data-sly-text='$DOLLAR{<error descr="Quotes must differ from outer attribute's quotes">'not empty string'</error>}'></div>""")

    fun testNotEmptyDoubleQuotedString() = checkAnnotations(
            """<div data-sly-text="$DOLLAR{<error descr="Quotes must differ from outer attribute's quotes">"not empty string"</error>}"></div>""")

    fun testMultipleWrongQuotesStringInSingleFile() = checkAnnotations("""
            <div data-sly-text="$DOLLAR{<error descr="Quotes must differ from outer attribute's quotes">""</error>}"></div>
            <div data-sly-text='$DOLLAR{<error descr="Quotes must differ from outer attribute's quotes">''</error>}'></div>
            <div data-sly-text="$DOLLAR{<error descr="Quotes must differ from outer attribute's quotes">"not empty string"</error>}"></div>
            <div data-sly-text='$DOLLAR{<error descr="Quotes must differ from outer attribute's quotes">'not empty string'</error>}'></div>
            """)

}

// TODO: check where \" are used in tests and wrap it in """ """ so escaping is not needed
