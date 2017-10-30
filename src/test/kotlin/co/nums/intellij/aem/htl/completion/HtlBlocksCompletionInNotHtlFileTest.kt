package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.definitions.HtlBlock

class HtlBlocksCompletionInNotHtlFileTest : HtlCompletionTestBase() {

    private val allBlocks = HtlBlock.values().map { it.type }.toTypedArray()

    fun testAllHtlBlocksWhenDataTyped() = checkInHtmlFileDoesNotContainAnyOf(
            """<div data<caret>""",
            *allBlocks)

    fun testAllHtlBlocksWhenSlyTyped() = checkInHtmlFileDoesNotContainAnyOf(
            """<div sly<caret>""",
            *allBlocks)

    fun testHtlBlocksFilteredByNamePart() = checkInHtmlFileDoesNotContainAnyOf(
            """<div te<caret>""",
            *allBlocks)

}
