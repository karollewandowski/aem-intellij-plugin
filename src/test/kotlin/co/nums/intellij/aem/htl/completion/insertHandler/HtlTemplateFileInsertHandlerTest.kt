package co.nums.intellij.aem.htl.completion.insertHandler

import co.nums.intellij.aem.htl.index.HtlTemplatesIndex
import co.nums.intellij.aem.service.jcrRoots
import com.intellij.openapi.vfs.VirtualFile

class HtlTemplateFileInsertHandlerTest:  HtlInsertHandlerTestBase() {

    private lateinit var jcrRoot: VirtualFile

    override fun setUp() {
        super.setUp()
        val dummyFile = myFixture.addFileToProject("jcr_root/dummy.html", "")
        jcrRoot = dummyFile.parent!!.virtualFile
        myFixture.project.jcrRoots.markAsJcrRoot(jcrRoot)
        HtlTemplatesIndex.rebuild()
    }

    override fun tearDown() {
        myFixture.project.jcrRoots.unmarkAsJcrRoot(jcrRoot)
        HtlTemplatesIndex.rebuild()
        super.tearDown()
    }

    fun testShouldInsertFullPathWithoutApps() {
        myFixture.addFileToProject(
                "jcr_root/apps/templates-insert-handler-tests/templates-in-apps.html",
                "<sly data-sly-template.test></sly>")

        testCompletionSelectFirstItem(
                """<div data-sly-use.testTemplates="<caret>"></div>""",
                """<div data-sly-use.testTemplates="templates-insert-handler-tests/templates-in-apps.html<caret>"></div>"""
        )
    }

    fun testShouldInsertFullPathWithoutLibs() {
        myFixture.addFileToProject(
                "jcr_root/libs/templates-insert-handler-tests/templates-in-libs.html",
                "<sly data-sly-template.test></sly>")

        testCompletionSelectFirstItem(
                """<div data-sly-use.testTemplates="<caret>"></div>""",
                """<div data-sly-use.testTemplates="templates-insert-handler-tests/templates-in-libs.html<caret>"></div>"""
        )
    }

    fun testShouldInsertOnlyTemplateLibIfInTheSameDir() {
        // TODO
    }

}
