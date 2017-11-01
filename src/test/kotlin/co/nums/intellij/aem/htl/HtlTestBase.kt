package co.nums.intellij.aem.htl

import co.nums.intellij.aem.htl.file.HtlFileType
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase

const val DOLLAR = '$'

abstract class HtlTestBase : LightPlatformCodeInsightFixtureTestCase() {

    protected fun testByText(before: String, after: String, action: () -> Unit) {
        myFixture.configureByText(HtlFileType, before.trimIndent())
        action()
        myFixture.checkResult(after.trimIndent())
    }

}
