package co.nums.intellij.aem.htl.highlighting

import co.nums.intellij.aem.htl.HtlTestBase
import co.nums.intellij.aem.htl.file.HtlFileType
import com.intellij.codeInsight.daemon.impl.HighlightInfo
import com.intellij.openapi.editor.colors.TextAttributesKey
import org.assertj.core.api.Assertions

abstract class HtlHighlightingTestBase : HtlTestBase() {

    protected fun testHighlighting(text: String, vararg expectedHighlights: Pair<String, TextAttributesKey>) {
        myFixture.configureByText(HtlFileType, text)

        val highlightInfos = myFixture.doHighlighting()

        assertThat(highlightInfos).containsExactly(*expectedHighlights)
    }

    private fun assertThat(highlightInfos: List<HighlightInfo>) =
            Assertions.assertThat(highlightInfos)
                    .extracting<Pair<String, TextAttributesKey>> { Pair(it.text, it.forcedTextAttributesKey) }

    protected fun String.withType(type: TextAttributesKey): Pair<String, TextAttributesKey> = Pair(this, type)

}
