package co.nums.intellij.aem.htl.lexer

import co.nums.intellij.aem.htl.*
import com.intellij.lexer.Lexer
import com.intellij.openapi.util.io.FileUtil
import com.intellij.openapi.util.text.StringUtil
import com.intellij.testFramework.*
import java.io.IOException

abstract class HtlLexingTestCaseBase : LexerTestCase(), HtlTestCase {

    override fun getDirPath() = throw UnsupportedOperationException()

    override fun createLexer() = HtlLexerAdapter()

    protected fun doTest(lexer: Lexer = createLexer()) {
        val filePath = pathToSourceTestFile(getTestName(true))
        var text = ""
        try {
            val fileText = FileUtil.loadFile(filePath.toFile(), Charsets.UTF_8)
            text = StringUtil.convertLineSeparators(if (shouldTrim()) fileText.trim() else fileText)
        } catch (e: IOException) {
            fail("Could not load file $filePath: ${e.message}")
        }
        doTest(text, null, lexer)
    }

    override fun doTest(text: String, expected: String?, lexer: Lexer) {
        val result = printTokens(text, 0, lexer)
        if (expected != null) {
            UsefulTestCase.assertSameLines(expected, result)
        } else {
            UsefulTestCase.assertSameLinesWithFile(pathToGoldTestFile(getTestName(true)).toFile().canonicalPath, result)
        }
    }

}
