package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.HtlTestBase
import co.nums.intellij.aem.htl.file.HtlFileType
import org.assertj.core.api.Assertions.assertThat

open class HtlCompletionTestBase : HtlTestBase() {

    protected fun checkContainsAll(vararg items: String) {
        myFixture.configureByFile(getFilePath())
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").containsAll(items.asIterable())
    }

    protected fun checkDoesNotContainAnyOf(vararg items: String) {
        myFixture.configureByFile(getFilePath())
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").doesNotContainAnyElementsOf(items.asIterable())
    }

    protected fun checkByTextContainsAll(text: String, vararg items: String) {
        myFixture.configureByText(HtlFileType, text)
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").containsAll(items.asIterable())
    }

    protected fun checkByTextDoesNotContainAnyOf(text: String, vararg items: String) {
        myFixture.configureByText(HtlFileType, text)
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").doesNotContainAnyElementsOf(items.asIterable())
    }

    protected fun checkAutoCompleted() = testByFile { myFixture.completeBasic() }

}
