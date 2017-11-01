package co.nums.intellij.aem.htl.editor.comments

import co.nums.intellij.aem.htl.HtlTestBase

abstract class HtlCommenterTestBase : HtlTestBase() {

    abstract val commentType: String

    protected fun doCommenterTest(before: String, after: String) =
            testByText(before, after) { myFixture.performEditorAction(commentType) }

}
