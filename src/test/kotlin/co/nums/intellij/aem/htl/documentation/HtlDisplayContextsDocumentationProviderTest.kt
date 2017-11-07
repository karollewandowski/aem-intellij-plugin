package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlDisplayContextsDocumentationProviderTest : HtlDocumentationProviderTestBase() {

    fun testAttributeDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='attribute'}
                                    ^
            """,
            "Applies HTML attribute value escaping."
    )

    fun testAttributeNameDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='attributeName'}
                                     ^
            """,
            "Outputs nothing if the value doesn't correspond to the HTML attribute name syntax. Doesn't allow <code>style</code> and <code>on</code>* attributes."
    )

    fun testCommentDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='comment'}
                                   ^
            """,
            "Applies HTML comment escaping."
    )

    fun testElementNameDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='elementName'}
                                     ^
            """,
            "Allows only element names that are white-listed, outputs <code>div</code> otherwise."
    )

    fun testHtmlDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='html'}
                                 ^
            """,
            "Removes markup that may contain XSS risks."
    )

    fun testNumberDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='number'}
                                  ^
            """,
            "Outputs zero if the value is not a number."
    )

    fun testScriptCommentDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='scriptComment'}
                                     ^
            """,
            "Outputs nothing if value is trying to break out of the JavaScript comment context."
    )

    fun testScriptRegExpDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='scriptRegExp'}
                                     ^
            """,
            "Applies JavaScript regular expression escaping."
    )

    fun testScriptStringDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='scriptString'}
                                     ^
            """,
            "Applies JavaScript string escaping."
    )

    fun testScriptTokenDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='scriptToken'}
                                     ^
            """,
            "Outputs nothing if the value doesn't correspond to the JavaScript token syntax."
    )

    fun testStyleCommentDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='styleComment'}
                                    ^
            """,
            "Outputs nothing if value is trying to break out of the CSS comment context."
    )

    fun testStyleStringDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='styleString'}
                                    ^
            """,
            "Applies CSS string escaping."
    )

    fun testStyleTokenDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='styleToken'}
                                    ^
            """,
            "Outputs nothing if the value doesn't correspond to the CSS token syntax."
    )

    fun testTextDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='text'}
                                 ^
            """,
            "Escapes all HTML tokens."
    )

    fun testUnsafeDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='unsafe'}
                                  ^
            """,
            "Disables XSS protection."
    )

    fun testUriDisplayContextDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{ @ context='uri'}
                                 ^
            """,
            "Outputs nothing if the value contains XSS risks."
    )

}
