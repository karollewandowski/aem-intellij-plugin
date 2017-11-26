package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.service.jcrRoots
import com.intellij.openapi.vfs.VirtualFile

class HtlTemplatesFilesCompletionTest : HtlCompletionTestBase() {

    private lateinit var jcrRoot: VirtualFile

    override fun setUp() {
        super.setUp()
        val dummyFile = myFixture.addFileToProject("jcr_root/dummy.html", "")
        jcrRoot = dummyFile.parent!!.virtualFile
        myFixture.project.jcrRoots.markAsJcrRoot(jcrRoot)
    }

    override fun tearDown() {
        myFixture.project.jcrRoots.unmarkAsJcrRoot(jcrRoot)
        super.tearDown()
    }

    fun testShouldCompleteTemplateFileContainingCorrectTemplateDefinition() {
        myFixture.addFileToProject("jcr_root/apps/templates-tests/template.html", "<sly data-sly-template.test></sly>")

        checkContainsAll(
                """<div data-sly-use.testTemplates="<caret>"></div>""",
                "template.html"
        )
    }

    fun testShouldCompleteTemplateFileContainingMultipleCorrectTemplateDefinitions() {
        myFixture.addFileToProject(
                "jcr_root/apps/templates-tests/nested-template.html", """
                <sly data-sly-template.test1></sly>
                <sly data-sly-template.test2></sly>""")

        checkContainsAll(
                """<div data-sly-use.testTemplates="<caret>"></div>""",
                "nested-template.html"
        )
    }

    fun testShouldCompleteTemplateFileContainingNestedCorrectTemplateDefinition() {
        myFixture.addFileToProject(
                "jcr_root/apps/templates-tests/nested-template.html", """
                <sly data-sly-use.testUse>
                    <sly data-sly-template.test></sly>
                </sly>""")

        checkContainsAll(
                """<div data-sly-use.testTemplates="<caret>"></div>""",
                "nested-template.html"
        )
    }

    fun testShouldNotCompleteWithoutTemplateDefinition() {
        myFixture.addFileToProject("jcr_root/apps/templates-tests/no-template.html", "<sly data-sly-NOT-template.test></sly>")

        checkDoesNotContainAnyOf(
                """<div data-sly-use.testTemplates="<caret>"></div>""",
                "no-template.html"
        )
    }

    fun testShouldNotCompleteCurrentTemplateFile() {
        // TODO
    }

}
