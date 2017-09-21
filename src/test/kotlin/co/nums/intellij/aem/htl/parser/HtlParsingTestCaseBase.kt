package co.nums.intellij.aem.htl.parser

import com.intellij.testFramework.ParsingTestCase

abstract class HtlParsingTestCaseBase(dataPath: String)
    : ParsingTestCase("co/nums/intellij/aem/htl/parser/fixtures/$dataPath", "html", true, HtlParserDefinition()) {

    override fun getTestDataPath() = "src/test/resources"

    // we want to test only HTL language
    override fun checkAllPsiRoots() = false

    fun doTest() = doTest(true)

}
