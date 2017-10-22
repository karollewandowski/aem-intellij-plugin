package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlDisplayContextsDocumentationProviderTest : HtlDocumentationProviderTest() {

    fun testAttributeDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='attribute'}
                                    ^
            """,
            "Applies HTML attribute value escaping."
    )

    fun testAttributeNameDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='attributeName'}
                                     ^
            """,
            "Outputs nothing if the value doesn't correspond to the HTML attribute name syntax. Doesn't allow <code>style</code> and <code>on*</code> attributes."
    )

    fun testCommentDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='comment'}
                                   ^
            """,
            "Applies HTML comment escaping."
    )

    fun testElementNameDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='elementName'}
                                     ^
            """,
            "Allows only element names that are white-listed, outputs <code>div</code> otherwise."
    )

    fun testHtmlDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='html'}
                                 ^
            """,
            "Removes markup that may contain XSS risks."
    )

    fun testNumberDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='number'}
                                  ^
            """,
            "Outputs zero if the value is not a number."
    )

    fun testScriptCommentDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='scriptComment'}
                                     ^
            """,
            "Outputs nothing if value is trying to break out of the JavaScript comment context."
    )

    fun testScriptRegExpDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='scriptRegExp'}
                                     ^
            """,
            "Applies JavaScript regular expression escaping."
    )

    fun testScriptStringDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='scriptString'}
                                     ^
            """,
            "Applies JavaScript string escaping."
    )

    fun testScriptTokenDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='scriptToken'}
                                     ^
            """,
            "Outputs nothing if the value doesn't correspond to the JavaScript token syntax."
    )

    fun testStyleCommentDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='styleComment'}
                                    ^
            """,
            "Outputs nothing if value is trying to break out of the CSS comment context."
    )

    fun testStyleStringDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='styleString'}
                                    ^
            """,
            "Applies CSS string escaping."
    )

    fun testStyleTokenDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='styleToken'}
                                    ^
            """,
            "Outputs nothing if the value doesn't correspond to the CSS token syntax."
    )

    fun testTextDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='text'}
                                 ^
            """,
            "Escapes all HTML tokens."
    )

    fun testUnsafeDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='unsafe'}
                                  ^
            """,
            "Disables XSS protection."
    )

    fun testUriDisplayContextDoc() = doTest(
            """
            $DOLLAR{ @ context='uri'}
                                 ^
            """,
            "Outputs nothing if the value contains XSS risks."
    )

}
