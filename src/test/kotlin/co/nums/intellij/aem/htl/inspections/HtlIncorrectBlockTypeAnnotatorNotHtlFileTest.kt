package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlIncorrectBlockTypeAnnotatorNotHtlFileTest : HtlAnnotatorTestBase() {

    fun testDataSlyPrefix() = checkAnnotationsInHtmlFile(
            "<div data-sly-=\"\"></div>")

    fun testDataSlyTstTypo() = checkAnnotationsInHtmlFile(
            "<div data-sly-tst=\"\"></div>")

    fun testUnfinishedDataSlyResourc() = checkAnnotationsInHtmlFile("""
            <div data-sly-="">
                <div data-sly-tst=""></div>
                <div data-sly-resourc=""></div>
            </div>
            """)

    fun testMultipleAnnotationsInSingleFile() = checkAnnotationsInHtmlFile(
            "<div data-sly-resourc=\"\"></div>")

}
