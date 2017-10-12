package co.nums.intellij.aem.htl

import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase

abstract class HtlTestBase : LightPlatformCodeInsightFixtureTestCase() {

    open val dataPath = ""
    open val relativeDataPath = "jcr_root"

    override fun getTestDataPath() = "${HtlTestCase.testResourcesPath}/$dataPath"

    protected fun testByFile(action: () -> Unit) {
        myFixture.configureByFile(getFilePath())
        action()
        myFixture.checkResultByFile(getFilePath().replace(".html", "_after.html"), true)
    }

    protected fun getFilePath() = "$relativeDataPath/${getTestName(true)}.html"

}
