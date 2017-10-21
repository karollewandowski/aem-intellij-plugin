package co.nums.intellij.aem.htl

import co.nums.intellij.aem.htl.file.HtlFileType
import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase

const val DOLLAR = '$'

abstract class HtlTestBase : LightPlatformCodeInsightFixtureTestCase() {

    open val dataPath = ""
    open val relativeDataPath = "jcr_root"

    override fun getTestDataPath() = "${HtlTestCase.testResourcesPath}/$dataPath"

    protected fun testByFile(action: () -> Unit) {
        myFixture.configureByFile(getFilePath())
        action()
        myFixture.checkResultByFile(getFilePath().replace(".html", "_after.html"), true)
    }

    protected fun testByText(before: String, after: String, action: () -> Unit) {
        myFixture.configureByText(HtlFileType, before.trimIndent())
        action()
        myFixture.checkResult(after.trimIndent())
    }

    protected fun getFilePath() = "$relativeDataPath/${getTestName(true)}.html"

}
