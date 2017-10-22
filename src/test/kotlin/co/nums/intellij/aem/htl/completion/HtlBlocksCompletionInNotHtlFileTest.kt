package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.definitions.HtlBlock

class HtlBlocksCompletionInNotHtlFileTest : HtlCompletionTestBase() {

    // with 'jcr_root' in data path and empty relative path file will be loaded as plain HTML
    override val dataPath = "co/nums/intellij/aem/htl/completion/blocks/fixtures/jcr_root"
    override val relativeDataPath = ""

    private val allBlocks = HtlBlock.values().map { it.type }.toTypedArray()

    fun testAllHtlBlocksWhenDataTyped() = checkDoesNotContainAnyOf(*allBlocks)
    fun testAllHtlBlocksWhenSlyTyped() = checkDoesNotContainAnyOf(*allBlocks)
    fun testHtlBlocksFilteredByNamePart() = checkDoesNotContainAnyOf(*allBlocks)

}
