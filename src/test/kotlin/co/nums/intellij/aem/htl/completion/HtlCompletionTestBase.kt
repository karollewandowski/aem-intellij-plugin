package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.HtlTestBase
import org.assertj.core.api.Assertions.assertThat

open class HtlCompletionTestBase : HtlTestBase() {

    protected fun checkContainsAll(vararg items: String) {
        myFixture.configureByFile(getFilePath())
        val variants = myFixture.completeBasic().map { it.lookupString }
        assertThat(variants).`as`("completions").containsAll(items.asList())
    }

    protected fun checkAutoCompleted() = testByFile { myFixture.completeBasic() }

}
