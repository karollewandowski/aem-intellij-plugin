package co.nums.intellij.aem.htl.editor.comments

import co.nums.intellij.aem.htl.HtlTestBase
import com.intellij.openapi.actionSystem.IdeActions

abstract class HtlCommenterTestBase(dataSubPath: String) : HtlTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/editor/comments/fixtures/$dataSubPath"
    override val relativeDataPath = "jcr_root"

    protected fun doTest() = doTest(IdeActions.ACTION_COMMENT_LINE)

}
