package co.nums.intellij.aem.htl.completion.insertHandler

import co.nums.intellij.aem.htl.HtlTestBase

open class HtlInsertHandlerTestBase : HtlTestBase() {

    protected fun testCompletion(before: String, after: String) =
            testByText(before, after) {
                myFixture.completeBasic()
            }

    protected fun testCompletionSelectFirstItem(before: String, after: String) =
            testByText(before, after) {
                myFixture.completeBasic()
                myFixture.finishLookup('\n')
            }

}
