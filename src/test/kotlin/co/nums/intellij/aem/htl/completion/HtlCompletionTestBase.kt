package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.HtlTestBase
import co.nums.intellij.aem.htl.file.HtlFileType
import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.openapi.fileTypes.FileType
import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.ListAssert

abstract class HtlCompletionTestBase : HtlTestBase() {

    protected fun checkContainsAll(text: String, vararg items: String) {
        completionVariants(HtlFileType, text).containsAll(items.asIterable())
    }

    protected fun checkDoesNotContainAnyOf(text: String, vararg items: String) {
        completionVariants(HtlFileType, text).doesNotContainAnyElementsOf(items.asIterable())
    }

    protected fun checkInHtmlFileDoesNotContainAnyOf(text: String, vararg items: String) {
        completionVariants(HtmlFileType.INSTANCE, text).doesNotContainAnyElementsOf(items.asIterable())
    }

    private fun completionVariants(fileType: FileType, text: String): ListAssert<String> {
        myFixture.configureByText(fileType, text)
        val variants = myFixture.completeBasic().map { it.lookupString }
        return assertThat(variants).`as`("completions")
    }

    protected fun checkAutoCompleted(before: String, after: String) = testByText(before, after) { myFixture.completeBasic() }

}
