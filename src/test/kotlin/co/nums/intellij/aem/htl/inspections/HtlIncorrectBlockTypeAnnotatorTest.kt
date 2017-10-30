package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class HtlIncorrectBlockTypeAnnotatorTest : HtlAnnotatorTestBase() {

    fun testDataSlyPrefix() = checkAnnotations(
            "<div <error descr=\"Unknown HTL block: data-sly-. Did you mean data-sly-attribute?\">data-sly-</error>=\"\"></div>")

    fun testDataSlyTstTypo() = checkAnnotations(
            "<div <error descr=\"Unknown HTL block: data-sly-tst. Did you mean data-sly-test?\">data-sly-tst</error>=\"\"></div>")

    fun testUnfinishedDataSlyResourc() = checkAnnotations("""
            <div <error descr="Unknown HTL block: data-sly-. Did you mean data-sly-attribute?">data-sly-</error>="">
                <div <error descr="Unknown HTL block: data-sly-tst. Did you mean data-sly-test?">data-sly-tst</error>=""></div>
                <div <error descr="Unknown HTL block: data-sly-resourc. Did you mean data-sly-resource?">data-sly-resourc</error>=""></div>
            </div>
            """)

    fun testMultipleAnnotationsInSingleFile() = checkAnnotations(
            "<div <error descr=\"Unknown HTL block: data-sly-resourc. Did you mean data-sly-resource?\">data-sly-resourc</error>=\"\"></div>")

}
