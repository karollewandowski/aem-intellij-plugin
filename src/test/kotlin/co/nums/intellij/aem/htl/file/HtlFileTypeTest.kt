package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.service.JcrRoots
import co.nums.intellij.aem.settings.AemSettings
import co.nums.intellij.aem.test.util.whenGetService
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.VirtualFile
import mockit.*
import mockit.integration.junit4.JMockit
import org.assertj.core.api.Assertions.assertThat
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

@RunWith(JMockit::class)
class HtlFileTypeTest {

    @Test
    fun testShouldReturnFalseFromIsMyFileTypeWhenGivenFileIsDirectory() {
        val directory = mockFile(directory = true)

        assertThat(HtlFileType.isMyFileType(directory)).isFalse()
    }

    @Test
    fun testShouldReturnFalseFromIsMyFileTypeWhenFileProjectIsNull(@Mocked projectLocator: ProjectLocator) {
        val file = mockFile(directory = false)
        projectLocator.mockProject(file, null)

        assertThat(HtlFileType.isMyFileType(file)).isFalse()
    }

    @Test
    fun testShouldReturnFalseFromIsMyFileTypeWhenAemSupportIsDisabledForProject(@Mocked projectLocator: ProjectLocator) {
        val file = mockFile(directory = false)
        val project = mock(Project::class.java, RETURNS_DEEP_STUBS)
        projectLocator.mockProject(file, project)
        project.setAemSupportEnabled(false)

        assertThat(HtlFileType.isMyFileType(file)).isFalse()
    }

    @Test
    fun testShouldReturnFalseFromIsMyFileTypeWhenFileIsHtlFileNotInJcrRoot(@Mocked projectLocator: ProjectLocator) {
        val file = mockFile(directory = false)
        `when`(file.extension).thenReturn("html")
        val project = mock(Project::class.java, RETURNS_DEEP_STUBS)
        projectLocator.mockProject(file, project)
        project.setAemSupportEnabled(true)
        project.mockJcrRoots()

        assertThat(HtlFileType.isMyFileType(file)).isFalse()
    }

    @Test
    fun testShouldReturnTrueFromIsMyFileTypeWhenFileIsHtlFileInJcrRoot(@Mocked projectLocator: ProjectLocator) {
        val file = mockFile(directory = false)
        `when`(file.extension).thenReturn("html")
        val project = mock(Project::class.java, RETURNS_DEEP_STUBS)
        projectLocator.mockProject(file, project)
        project.setAemSupportEnabled(true)
        project.mockJcrRoots(file)

        assertThat(HtlFileType.isMyFileType(file)).isTrue()
    }

    private fun mockFile(directory: Boolean): VirtualFile {
        val file = mock(VirtualFile::class.java)
        `when`(file.isDirectory).thenReturn(directory)
        return file
    }

    private fun Project.setAemSupportEnabled(enabled: Boolean) {
        val aemSettings = mock(AemSettings::class.java)
        `when`(aemSettings.aemSupportEnabled).thenReturn(enabled)
        whenGetService<AemSettings>(this).thenReturn(aemSettings)
    }

    private fun ProjectLocator.mockProject(file: VirtualFile, project: Project?) {
        val projectLocator = this
        object : Expectations(ProjectLocator::class.java) {
            init {
                ProjectLocator.getInstance(); result = projectLocator
                projectLocator.guessProjectForFile(file); result = project
            }
        }
    }

    private fun Project.mockJcrRoots(file: VirtualFile? = null) {
        val jcrRoots = mock(JcrRoots::class.java)
        if (file != null) {
            `when`(jcrRoots.contains(file)).thenReturn(true)
        }
        whenGetService<JcrRoots>(this).thenReturn(jcrRoots)
    }

}
