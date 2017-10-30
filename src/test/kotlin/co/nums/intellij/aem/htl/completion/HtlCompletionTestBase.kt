package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.HtlTestBase
import co.nums.intellij.aem.htl.file.HtlFileType
import com.intellij.ide.highlighter.HtmlFileType
import org.assertj.core.api.Assertions.assertThat

open class HtlCompletionTestBase : HtlTestBase() {

    protected fun checkContainsAll(text: String, vararg items: String) {
        myFixture.configureByText(HtlFileType, text)
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").containsAll(items.asIterable())
    }

    protected fun checkDoesNotContainAnyOf(text: String, vararg items: String) {
        myFixture.configureByText(HtlFileType, text)
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").doesNotContainAnyElementsOf(items.asIterable())
    }

    protected fun checkInHtmlFileDoesNotContainAnyOf(text: String, vararg items: String) {
        myFixture.configureByText(HtmlFileType.INSTANCE, text)
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").doesNotContainAnyElementsOf(items.asIterable())
    }

    protected fun checkAutoCompleted(before: String, after: String) = testByText(before, after) { myFixture.completeBasic() }

}
