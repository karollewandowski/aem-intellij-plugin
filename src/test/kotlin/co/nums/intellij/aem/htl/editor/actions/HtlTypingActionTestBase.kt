package co.nums.intellij.aem.htl.editor.actions

import co.nums.intellij.aem.htl.HtlTestBase
import co.nums.intellij.aem.htl.file.HtlFileType

abstract class HtlTypingActionTestBase : HtlTestBase() {

    protected fun doTest(given: String, typeChar: Char, expected: String) {
        myFixture.configureByText(HtlFileType, given)
        myFixture.type(typeChar)
        myFixture.checkResult(expected)
    }

}
