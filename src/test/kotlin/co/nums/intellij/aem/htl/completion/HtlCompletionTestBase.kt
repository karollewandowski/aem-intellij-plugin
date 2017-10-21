package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.HtlTestBase
import co.nums.intellij.aem.htl.file.HtlFileType
import org.assertj.core.api.Assertions.assertThat

open class HtlCompletionTestBase : HtlTestBase() {

    protected fun checkContainsAll(vararg items: String) {
        myFixture.configureByFile(getFilePath())
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").containsAll(items.asList())
    }

    protected fun checkByTextContainsAll(text: String, items: List<String>) {
        myFixture.configureByText(HtlFileType, text)
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").containsAll(items)
    }

    protected fun checkByTextDoesNotContainAnyOf(text: String, items: List<String>) {
        myFixture.configureByText(HtlFileType, text)
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").doesNotContainAnyElementsOf(items)
    }

    protected fun checkAutoCompleted() = testByFile { myFixture.completeBasic() }

}
