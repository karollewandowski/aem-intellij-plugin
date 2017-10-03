package co.nums.intellij.aem.htl

abstract class HtlAnnotatorTestBase : HtlTestBase() {

    protected fun checkQuickFix(fixName: String) = testByFile { applyQuickFix(fixName) }

    private fun applyQuickFix(actionName: String) {
        val action = myFixture.findSingleIntention(actionName)
        myFixture.launchAction(action)
    }

    protected fun checkAnnotation() {
        myFixture.configureByFile(getFilePath())
        myFixture.checkHighlighting(false, false, false)
    }

}
