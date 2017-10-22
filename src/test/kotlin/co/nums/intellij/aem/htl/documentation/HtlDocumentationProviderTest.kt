package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.HtlTestBase
import co.nums.intellij.aem.htl.file.HtlFileType
import com.intellij.codeInsight.documentation.DocumentationManager
import com.intellij.openapi.editor.LogicalPosition
import com.intellij.psi.PsiElement
import com.intellij.psi.util.PsiTreeUtil

const val CARET_MARKER = "^"

abstract class HtlDocumentationProviderTest : HtlTestBase() {

    protected fun doTest(code: String, expected: String)
            = doTest(code, expected, HtlDocumentationProvider::generateDoc, dollarConstantUsed = false)

    protected fun doTestWithDollarConstant(code: String, expected: String)
            = doTest(code, expected, HtlDocumentationProvider::generateDoc, dollarConstantUsed = true)

    private inline fun doTest(code: String,
                              expected: String,
                              block: HtlDocumentationProvider.(PsiElement, PsiElement?) -> String?,
                              dollarConstantUsed: Boolean) {
        myFixture.configureByText(HtlFileType, code)

        val (originalElement, _, offset) = findElementWithDataAndOffsetInEditor<PsiElement>(dollarConstantUsed)
        val element = DocumentationManager.getInstance(project)
                .findTargetElement(myFixture.editor, offset, myFixture.file, originalElement)!!

        val actual = HtlDocumentationProvider().block(element, originalElement)?.trim()
        assertSameLines(expected.trimIndent(), actual)
    }

    protected inline fun <reified T : PsiElement> findElementWithDataAndOffsetInEditor(dollarConstantUsed: Boolean): Triple<T, String, Int> {
        val (elementAtMarker, data, offset) = run {
            val text = myFixture.file.text
            val markerOffset = text.indexOf(CARET_MARKER)
            check(markerOffset != -1) { "No '$CARET_MARKER' marker:\n$text" }
            check(text.indexOf(CARET_MARKER, startIndex = markerOffset + 1) == -1) {
                "More than one `$CARET_MARKER` marker:\n$text"
            }

            val data = text.drop(markerOffset).removePrefix(CARET_MARKER).takeWhile { it != '\n' }.trim()
            val logicalMarkerOffset = markerOffset + CARET_MARKER.length - (if (dollarConstantUsed) "DOLLAR" else "").length - 1
            val markerPosition = myFixture.editor.offsetToLogicalPosition(logicalMarkerOffset)
            val previousLine = LogicalPosition(markerPosition.line - 1, markerPosition.column)
            val elementOffset = myFixture.editor.logicalPositionToOffset(previousLine)
            Triple(myFixture.file.findElementAt(elementOffset)!!, data, elementOffset)
        }
        val element = elementAtMarker.parentOfType<T>(strict = false)
                ?: error("No ${T::class.java.simpleName} at ${elementAtMarker.text}")
        return Triple(element, data, offset)
    }

    inline fun <reified T : PsiElement> PsiElement.parentOfType(strict: Boolean = true, minStartOffset: Int = -1): T? =
            PsiTreeUtil.getParentOfType(this, T::class.java, strict, minStartOffset)

}
