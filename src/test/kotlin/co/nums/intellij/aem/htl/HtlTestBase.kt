package co.nums.intellij.aem.htl

import com.intellij.testFramework.fixtures.LightPlatformCodeInsightFixtureTestCase

abstract class HtlTestBase : LightPlatformCodeInsightFixtureTestCase() {

    abstract val dataPath: String
    open val relativeDataPath = ""
    private val filePath: String
        get() = "$relativeDataPath/${getTestName(true)}.html"

    override fun getTestDataPath() = "${HtlTestCase.testResourcesPath}/$dataPath"

    protected fun doTest(actionId: String) {
        myFixture.configureByFile(filePath)
        myFixture.performEditorAction(actionId)
        myFixture.checkResultByFile(filePath.replace(".html", "_after.html"), true)
    }

}
