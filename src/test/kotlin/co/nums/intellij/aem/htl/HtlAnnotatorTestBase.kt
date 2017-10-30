package co.nums.intellij.aem.htl

import co.nums.intellij.aem.htl.file.HtlFileType
import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.openapi.fileTypes.FileType

abstract class HtlAnnotatorTestBase : HtlTestBase() {

    protected fun checkQuickFix(fixName: String, before: String, after: String) =
            testByText(before.trimIndent(), after.trimIndent()) { applyQuickFix(fixName) }

    private fun applyQuickFix(actionName: String) {
        val action = myFixture.findSingleIntention(actionName)
        myFixture.launchAction(action)
    }

    protected fun checkAnnotations(code: String) = checkAnnotationByText(HtlFileType, code.trimIndent())

    protected fun checkAnnotationsInHtmlFile(code: String) = checkAnnotationByText(HtmlFileType.INSTANCE, code.trimIndent())

    private fun checkAnnotationByText(fileType: FileType, code: String) {
        myFixture.configureByText(fileType, code.trimIndent())
        myFixture.checkHighlighting(false, false, false)
    }

}
