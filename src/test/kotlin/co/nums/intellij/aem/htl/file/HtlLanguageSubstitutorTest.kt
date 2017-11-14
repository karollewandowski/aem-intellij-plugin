package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.service.JcrRoots
import co.nums.intellij.aem.settings.AemSettings
import co.nums.intellij.aem.test.util.whenGetService
import com.intellij.lang.Language
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.BeforeEach
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.converter.*
import org.junit.jupiter.params.provider.CsvSource
import org.mockito.Mockito
import org.mockito.Mockito.`when`
import org.mockito.Mockito.mock


class HtlLanguageSubstitutorTest {

    private lateinit var project: Project
    private lateinit var aemSettings: AemSettings
    private lateinit var jcrRoots: JcrRoots
    private lateinit var testFile: VirtualFile

    private lateinit var sut: HtlLanguageSubstitutor

    @BeforeEach
    fun setUp() {
        project = mock(Project::class.java, Mockito.RETURNS_DEEP_STUBS)
        aemSettings = mock(AemSettings::class.java)
        jcrRoots = mock(JcrRoots::class.java)
        whenGetService<AemSettings>(project).thenReturn(aemSettings)
        whenGetService<JcrRoots>(project).thenReturn(jcrRoots)
        testFile = mock(VirtualFile::class.java)
        sut = HtlLanguageSubstitutor()
    }

    @ParameterizedTest(name = "should return {3} for: aemSupportEnabled={0}, htmlFile={1}, fileUnderJcrRoot={2}")
    @CsvSource(
            "true ,true ,true ,HtlLanguage",
            "true ,true ,false,null",
            "true ,false,true ,null",
            "true ,false,false,null",
            "false ,true ,true ,null",
            "false ,true ,false,null",
            "false ,false,true ,null",
            "false ,false,false,null"
    )
    fun testSubstitution(aemSupportEnabled: Boolean, htmlFile: Boolean, fileUnderJcrRoot: Boolean, @ConvertWith(LanguageArgumentConverter::class) expectedLanguage: Language?) {
        `when`(aemSettings.aemSupportEnabled).thenReturn(aemSupportEnabled)
        `when`(testFile.extension).thenReturn(if (htmlFile) "html" else "txt")
        `when`(jcrRoots.contains(testFile)).thenReturn(fileUnderJcrRoot)

        val actualLanguage = sut.getLanguage(testFile, project)

        assertThat(actualLanguage).isEqualTo(expectedLanguage)
    }

    class LanguageArgumentConverter : SimpleArgumentConverter() {
        override fun convert(source: Any?, targetType: Class<*>) = if (source == "HtlLanguage") HtlLanguage else null
    }

}
