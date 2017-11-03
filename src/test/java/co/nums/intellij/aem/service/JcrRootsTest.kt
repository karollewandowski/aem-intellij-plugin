package co.nums.intellij.aem.service

import co.nums.intellij.aem.test.util.*
import com.intellij.openapi.project.Project
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock

class JcrRootsTest {

    private val testDir = directory("any-base-dir")
    private val testBasePath = "MOCK_ROOT:"
    private lateinit var project: Project
    private lateinit var jcrRootsDetector: JcrRootsDetector
    private lateinit var state: JcrRoots.State

    private lateinit var sut: JcrRoots

    @BeforeEach
    fun setUp() {
        project = mock(Project::class.java)
        `when`(project.basePath).thenReturn(testBasePath)
        `when`(project.baseDir).thenReturn(testDir)
        jcrRootsDetector = mock(JcrRootsDetector::class.java)
        state = JcrRoots.State()
    }

    @Test
    fun isContentRootShouldReturnFalseWhenNotDetectedAndNotMarkedDirectoryPassed() {
        sut = createSut()

        assertThat(sut.isJcrContentRoot(directory("jcr_root"))).isFalse()
    }

    @Test
    fun isContentRootShouldReturnTrueWhenDetectedDirectoryPassed() {
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))

        sut = createSut()

        assertThat(sut.isJcrContentRoot(directory("jcr_root"))).isTrue()
    }

    @Test
    fun isContentRootShouldReturnTrueWhenMarkedDirectoryPassed() {
        state.markedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.isJcrContentRoot(directory("jcr_root"))).isTrue()
    }

    @Test
    fun isContentRootShouldReturnTrueWhenDetectedAndMarkedDirectoryPassed() {
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))
        state.markedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.isJcrContentRoot(directory("jcr_root"))).isTrue()
    }

    @Test
    fun isContentRootShouldReturnFalseWhenDetectedButUnmarkedDirectoryPassed() {
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))
        state.unmarkedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.isJcrContentRoot(directory("jcr_root"))).isFalse()
    }

    @Test
    fun isContentRootShouldReturnFalseWhenNotDetectedAndUnmarkedDirectoryPassed() {
        state.unmarkedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.isJcrContentRoot(directory("jcr_root"))).isFalse()
    }

    @Test
    fun isEmptyShouldReturnTrueWhenNoDetectedAndMarkedRoots() {
        sut = createSut()

        assertThat(sut.isEmpty()).isTrue()
    }

    @Test
    fun isEmptyShouldReturnFalseWhenDetectedRoots() {
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))

        sut = createSut()

        assertThat(sut.isEmpty()).isFalse()
    }

    @Test
    fun isEmptyShouldReturnTrueWhenDetectedRootButItIsUnmarked() {
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))
        state.unmarkedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.isEmpty()).isTrue()
    }

    @Test
    fun isEmptyShouldReturnFalseWhenNotDetectedRootsButItIsMarked() {
        state.markedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.isEmpty()).isFalse()
    }

    @Test
    fun containsShouldReturnFalseWhenNoDetectedOrMarkedRoots() {
        sut = createSut()

        assertThat(sut.contains(directory("jcr_root"))).isFalse()
    }

    @Test
    fun containsShouldReturnTrueWhenDetectedAndRootDirectoryPassed() {
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))

        sut = createSut()

        assertThat(sut.contains(directory("jcr_root"))).isTrue()
    }

    @Test
    fun containsShouldReturnTrueWhenDetectedAndChildDirectoryPassed() {
        val child = directory("child")
        directory("jcr_root").withChildren(child)
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))

        sut = createSut()

        assertThat(sut.contains(child)).isTrue()
    }

    @Test
    fun containsShouldReturnTrueWhenMarkedDirectoryPassed() {
        state.markedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.contains(directory("jcr_root"))).isTrue()
    }

    @Test
    fun containsShouldReturnTrueWhenChildOfMarkedDirectoryPassed() {
        val child = directory("child")
        directory("jcr_root").withChildren(child)
        state.markedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.contains(child)).isTrue()
    }

    @Test
    fun containsShouldReturnTrueWhenDetectedAndMarkedDirectoryPassed() {
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))
        state.markedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.contains(directory("jcr_root"))).isTrue()
    }

    @Test
    fun containsShouldReturnTrueWhenChildOfDetectedAndMarkedDirectoryPassed() {
        val child = directory("child")
        directory("jcr_root").withChildren(child)
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))
        state.markedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.contains(child)).isTrue()
    }

    @Test
    fun containsShouldReturnFalseWhenDetectedButUnmarkedDirectoryPassed() {
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))
        state.unmarkedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.contains(directory("jcr_root"))).isFalse()
    }

    @Test
    fun containsShouldReturnFalseWhenChildOfDetectedButUnmarkedDirectoryPassed() {
        val child = directory("child")
        directory("jcr_root").withChildren(child)
        `when`(jcrRootsDetector.detectJcrRoots(testDir, testBasePath)).thenReturn(setOf("/jcr_root"))
        state.unmarkedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.contains(child)).isFalse()
    }

    @Test
    fun containsShouldReturnFalseWhenNotDetectedAndUnmarkedDirectoryPassed() {
        state.unmarkedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.contains(directory("jcr_root"))).isFalse()
    }

    @Test
    fun containsShouldReturnFalseWhenChildOfNotDetectedAndUnmarkedDirectoryPassed() {
        val child = directory("child")
        directory("jcr_root").withChildren(child)
        state.unmarkedJcrContentRoots.add("/jcr_root")

        sut = createSut()

        assertThat(sut.contains(child)).isFalse()
    }

    // TODO: add tests for marking

    // TODO: add tests for unmarking

    private fun createSut(): JcrRoots {
        val sut = JcrRoots(project, jcrRootsDetector)
        sut.loadState(state)
        return sut
    }

}
