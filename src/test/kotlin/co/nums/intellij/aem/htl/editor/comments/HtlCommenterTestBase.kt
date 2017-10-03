package co.nums.intellij.aem.htl.editor.comments

import co.nums.intellij.aem.htl.HtlTestBase

abstract class HtlCommenterTestBase(dataSubPath: String) : HtlTestBase() {

    abstract val commentType: String

    override val dataPath = "co/nums/intellij/aem/htl/editor/comments/fixtures/$dataSubPath"
    override val relativeDataPath = "jcr_root"

    protected fun doTest() = testByFile { myFixture.performEditorAction(commentType) }

}
