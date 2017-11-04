package co.nums.intellij.aem.action

import co.nums.intellij.aem.icons.AemIcons
import co.nums.intellij.aem.service.JcrRoots
import co.nums.intellij.aem.test.util.*
import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.Disposable
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE_ARRAY
import com.intellij.openapi.application.*
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager
import org.junit.jupiter.api.*
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock
import org.mockito.Mockito.verify
import org.mockito.Mockito.verifyZeroInteractions

class MarkAsJcrRootActionTest {

    private lateinit var event: AnActionEvent
    private lateinit var project: Project
    private lateinit var jcrRoots: JcrRoots

    private lateinit var sut: MarkAsJcrRootAction

    @BeforeEach
    fun setUp() {
        event = mock(AnActionEvent::class.java, RETURNS_DEEP_STUBS)
        project = mock(Project::class.java, RETURNS_DEEP_STUBS)
        `when`(event.project).thenReturn(project)
        jcrRoots = mock(JcrRoots::class.java)
        whenGetService<JcrRoots>(project).thenReturn(jcrRoots)
        sut = MarkAsJcrRootAction()
    }

    @Test
    fun shouldHideActionWhenDirsIsNull() {
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(null)

        sut.update(event)

        verify(event.presentation).isEnabledAndVisible = false
    }

    @Test
    fun shouldHideActionWhenDirsIsEmpty() {
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(emptyArray())

        sut.update(event)

        verify(event.presentation).isEnabledAndVisible = false
    }

    @Test
    fun shouldHideActionWhenProjectIsNull() {
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(arrayOf(directory("any_dir")))
        `when`(event.project).thenReturn(null)

        sut.update(event)

        verify(event.presentation).isEnabledAndVisible = false
    }

    @Test
    fun shouldHideActionWhenAnyOfFilesIsNotDir() {
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(arrayOf(
                directory("dir1"),
                file("not_dir"),
                directory("dir2")))

        sut.update(event)

        verify(event.presentation).isEnabledAndVisible = false
    }

    @Test
    fun shouldShowMarkAsJcrRootAction() {
        val testSelectedDirs = arrayOf(directory("dir1"), directory("dir2"))
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(testSelectedDirs)
        testSelectedDirs.forEach { `when`(jcrRoots.contains(it)).thenReturn(false) }

        sut.update(event)

        verify(event.presentation).icon = AemIcons.JCR_ROOT_DIR
    }

    @Test
    fun shouldDisableMarkAsJcrRootActionWhenAtLeastOneDirIsMarkedAlready() {
        val jcrRoot = directory("jcr_root")
        val notMarkedDirs = arrayOf(directory("dir1"), directory("dir2"))
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(notMarkedDirs + jcrRoot)
        notMarkedDirs.forEach { `when`(jcrRoots.contains(it)).thenReturn(false) }
        `when`(jcrRoots.contains(jcrRoot)).thenReturn(true)

        sut.update(event)

        verify(event.presentation).isEnabledAndVisible = false
    }

    @Test
    fun shouldShowUnmarkAsJcrRootAction() {
        val testSelectedDirs = arrayOf(directory("dir1"), directory("dir2"))
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(testSelectedDirs)
        testSelectedDirs.forEach { `when`(jcrRoots.isJcrContentRoot(it)).thenReturn(true) }

        sut.update(event)

        verify(event.presentation).icon = AemIcons.JCR_ROOT_DIR
    }

    @Test
    fun shouldDisableUnmarkAsJcrRootActionWhenAtLeastOneDirIsNotJcrRoot() {
        val notJcrRoot = directory("jcr_root")
        val jcrRootsDirs = arrayOf(directory("dir1"), directory("dir2"))
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(jcrRootsDirs + notJcrRoot)
        jcrRootsDirs.forEach { `when`(this.jcrRoots.contains(it)).thenReturn(true) }
        `when`(jcrRoots.contains(notJcrRoot)).thenReturn(false)

        sut.update(event)

        verify(event.presentation).isEnabledAndVisible = false
    }

    @Test
    fun shouldNotPerformAnyActionWhenPresentationIsDisabled() {
        `when`(event.presentation.isEnabledAndVisible).thenReturn(false)

        sut.actionPerformed(event)

        verifyZeroInteractions(jcrRoots)
    }

    @Test
    fun shouldNotPerformAnyActionWhenProjectIsNull() {
        `when`(event.presentation.isEnabledAndVisible).thenReturn(true)
        `when`(event.project).thenReturn(null)

        sut.actionPerformed(event)

        verifyZeroInteractions(jcrRoots)
    }

    @Test
    fun shouldNotPerformAnyActionWhenDirsIsNull() {
        `when`(event.presentation.isEnabledAndVisible).thenReturn(true)
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(null)

        sut.actionPerformed(event)

        verifyZeroInteractions(jcrRoots)
    }

    @Test
    fun shouldNotPerformAnyActionWhenDirsIsEmpty() {
        `when`(event.presentation.isEnabledAndVisible).thenReturn(true)
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(emptyArray())

        sut.actionPerformed(event)

        verifyZeroInteractions(jcrRoots)
    }

    @Test
    fun shouldNotPerformAnyActionWhenAnyOfSelectedFilesIsNotDirectory() {
        `when`(event.presentation.isEnabledAndVisible).thenReturn(true)
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(arrayOf(directory("dir1"), file("not_dir"), directory("dir2")))

        sut.actionPerformed(event)

        verifyZeroInteractions(jcrRoots)
    }

    @Test
    fun shouldMarkDirectoriesAsJcrRoots() {
        val dirsToMark = arrayOf(directory("dir1"), directory("dir2").withChildren(file("test.html")))
        `when`(event.presentation.isEnabledAndVisible).thenReturn(true)
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(dirsToMark)
        dirsToMark.forEach { `when`(jcrRoots.contains(it)).thenReturn(false) }
        whenGetService<ProjectView>(project).thenReturn(mock(ProjectView::class.java, RETURNS_DEEP_STUBS))
        `when`(project.getComponent(PsiManager::class.java)).thenReturn(mock(PsiManager::class.java))
        ApplicationManager.setApplication(mock(Application::class.java), mock(Disposable::class.java))

        sut.actionPerformed(event)

        dirsToMark.forEach { verify(jcrRoots).markAsJcrRoot(it) }
    }

    @Test
    fun shouldUnmarkDirectoriesAsJcrRoots() {
        val dirsToUnmark = arrayOf(directory("dir1").withChildren(file("test.html")), directory("dir2"))
        `when`(event.presentation.isEnabledAndVisible).thenReturn(true)
        `when`(event.getData(VIRTUAL_FILE_ARRAY)).thenReturn(dirsToUnmark)
        dirsToUnmark.forEach { `when`(jcrRoots.contains(it)).thenReturn(true) }
        dirsToUnmark.forEach { `when`(jcrRoots.isJcrContentRoot(it)).thenReturn(true) }
        whenGetService<ProjectView>(project).thenReturn(mock(ProjectView::class.java, RETURNS_DEEP_STUBS))
        `when`(project.getComponent(PsiManager::class.java)).thenReturn(mock(PsiManager::class.java))
        ApplicationManager.setApplication(mock(Application::class.java), mock(Disposable::class.java))

        sut.actionPerformed(event)

        dirsToUnmark.forEach { verify(jcrRoots).unmarkAsJcrRoot(it) }
    }

}
