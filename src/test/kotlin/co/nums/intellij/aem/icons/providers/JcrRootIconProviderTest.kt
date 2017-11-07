package co.nums.intellij.aem.icons.providers

import co.nums.intellij.aem.icons.AemIcons
import co.nums.intellij.aem.service.JcrRoots
import co.nums.intellij.aem.test.util.whenGetService
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.mockito.Mockito.RETURNS_DEEP_STUBS
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

private const val ANY_FLAGS = 0

class JcrRootIconProviderTest {

    private lateinit var project: Project
    private lateinit var jcrRoots: JcrRoots

    private lateinit var cut: JcrRootIconProvider

    @BeforeEach
    fun setUp() {
        project = mock(Project::class.java, RETURNS_DEEP_STUBS)
        jcrRoots = mock(JcrRoots::class.java)
        whenGetService<JcrRoots>(project).thenReturn(jcrRoots)
        cut = JcrRootIconProvider()
    }

    @Test
    fun shouldReturnHtlRootIconWhenPsiElementIsJcrRoot() {
        val psiDirectory = mock(PsiDirectory::class.java, RETURNS_DEEP_STUBS)
        `when`(psiDirectory.virtualFile).thenReturn(mock(VirtualFile::class.java))
        `when`(psiDirectory.project).thenReturn(project)
        `when`(jcrRoots.isJcrContentRoot(psiDirectory.virtualFile)).thenReturn(true)

        val icon = cut.getIcon(psiDirectory, ANY_FLAGS)

        assertThat(icon).isEqualTo(AemIcons.JCR_ROOT_DIR)
    }

    @Test
    fun shouldReturnNullIconWhenPsiElementIsNotDirectory() {
        val psiElement = mock(PsiElement::class.java, RETURNS_DEEP_STUBS)
        `when`(psiElement.project).thenReturn(project)

        val icon = cut.getIcon(psiElement, ANY_FLAGS)

        assertThat(icon).isNull()
    }

    @Test
    fun shouldReturnNullIconWhenPsiDirectoryIsNotJcrRoot() {
        val psiDirectory = mock(PsiDirectory::class.java, RETURNS_DEEP_STUBS)
        `when`(psiDirectory.virtualFile).thenReturn(mock(VirtualFile::class.java))
        `when`(psiDirectory.project).thenReturn(project)
        `when`(jcrRoots.isJcrContentRoot(psiDirectory.virtualFile)).thenReturn(false)

        val icon = cut.getIcon(psiDirectory, ANY_FLAGS)

        assertThat(icon).isNull()
    }

}
