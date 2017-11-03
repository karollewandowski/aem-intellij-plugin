package co.nums.intellij.aem.service

import co.nums.intellij.aem.constants.JCR_SPECIFIC_DIRECTORIES
import co.nums.intellij.aem.test.util.*
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Test
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource

class JcrRootsDetectorImplTest {

    private val testBaseDir = "MOCK_ROOT:/projectRoot"

    private val sut = JcrRootsDetectorImpl()

    @Test
    fun shouldNotDetectJcrRootsWhenGivenRootIsEmpty() {
        val projectRoot = directory("projectRoot")

        val detectJcrRoots = sut.detectJcrRoots(projectRoot)

        assertThat(detectJcrRoots).isEmpty()
    }

    @Test
    fun shouldDetectJcrRootDirectoryAsRootDirectory() {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("jcr_root"))

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).containsOnly("/jcr_root")
    }

    @Test
    fun shouldDetectNestedJcrRootDirectory() {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("sources").withChildren(directory("jcr_root")))

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).containsOnly("/sources/jcr_root")
    }

    @Test
    fun shouldDetectMultipleJcrRoots() {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("sources1").withChildren(
                                directory("jcr_root")),
                        directory("sources2").withChildren(
                                directory("jcr_root")))

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).containsOnly(
                "/sources1/jcr_root",
                "/sources2/jcr_root")
    }

    @Test
    fun shouldDetectMultipleJcrRootsWithDifferentNestingLevel() {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("jcr_root"),
                        directory("sources").withChildren(directory("jcr_root")))

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).containsOnly(
                "/jcr_root",
                "/sources/jcr_root")
    }

    @Test
    fun shouldNotDetectJcrRootNestedInJcrRoot() {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("jcr_root")
                                .withChildren(directory("jcr_root")))

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).containsOnly("/jcr_root")
    }

    @ParameterizedTest
    @MethodSource("jcrSpecificDirectories")
    fun shouldDetectNonStandardJcrRootWhenItContainsJcrSpecificDirectoryWithContentDefinitionFile(jcrSpecificDir: String) {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("non_standard_root").withChildren(
                                directory("apps").withChildren(
                                        file(".content.xml"))))

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).containsOnly("/non_standard_root")
    }

    @ParameterizedTest
    @MethodSource("jcrSpecificDirectories")
    fun shouldNotDetectNonStandardJcrRootWhenItContainsJcrSpecificDirectoryWithoutContentDefinitionFile(jcrSpecificDir: String) {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("non_standard_root").withChildren(
                                directory("apps"))) // no .content.xml file inside

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).isEmpty()
    }

    @Test
    fun shouldNotDetectNonStandardJcrRootWhenItDoesNotContainJcrSpecificDirectoryWithContentDefinitionFile() {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("non_standard_root").withChildren(
                                directory("not-jcr-specific-dir").withChildren(
                                        file(".content.xml"))))

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).isEmpty()
    }

    @Test
    fun shouldHandleDetectionForJcrRootNamedAsStandardJcrDirectory() {
        val projectRoot =
                directory("projectRoot").withChildren(
                        directory("content").withChildren(
                                directory("content").withChildren(
                                        file(".content.xml"))))

        val detectJcrRoots = sut.detectJcrRoots(projectRoot, testBaseDir)

        assertThat(detectJcrRoots).containsOnly("/content")
    }

    companion object {
        @JvmStatic
        fun jcrSpecificDirectories() = JCR_SPECIFIC_DIRECTORIES
    }

}
