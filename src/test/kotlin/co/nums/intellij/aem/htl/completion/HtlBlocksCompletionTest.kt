package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.definitions.HtlBlock

class HtlBlocksCompletionTest : HtlCompletionTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/completion/blocks/fixtures"

    private val allBlocks = HtlBlock.values().map { it.type }.toTypedArray()

    fun testAllHtlBlocksWhenDataTyped() = checkContainsAll(*allBlocks)
    fun testAllHtlBlocksWhenSlyTyped() = checkContainsAll(*allBlocks)
    fun testHtlBlocksFilteredByNamePart() = checkContainsAll(
            "data-sly-template", "data-sly-test", "data-sly-text", "data-sly-attribute")

}
