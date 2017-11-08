package co.nums.intellij.aem.htl

import com.intellij.testFramework.fixtures.JavaCodeInsightTestFixture
import java.io.File
import java.nio.file.*

interface HtlTestCase {

    fun getTestDataPath(): String

    companion object {
        val testResourcesPath = "src/test/resources"
    }

}

fun HtlTestCase.pathToSourceTestFile(name: String): Path =
        Paths.get("${HtlTestCase.testResourcesPath}/${getTestDataPath()}/$name.html")

fun HtlTestCase.pathToGoldTestFile(name: String): Path =
        Paths.get("${HtlTestCase.testResourcesPath}/${getTestDataPath()}/$name.txt")

fun JavaCodeInsightTestFixture.addAemClass(className: String) {
    this.addClass(File("src/test/resources/co/nums/intellij/aem/aem-classes/$className").readText())
}
