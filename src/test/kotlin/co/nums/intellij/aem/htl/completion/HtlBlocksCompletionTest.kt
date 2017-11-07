package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.definitions.HtlBlock

class HtlBlocksCompletionTest : HtlCompletionTestBase() {

    private val allBlocks = HtlBlock.values().map { it.type }.toTypedArray()

    fun testAllHtlBlocksWhenDataTyped() = checkContainsAll(
            """<div data<caret>""",
            *allBlocks)

    fun testAllHtlBlocksWhenSlyTyped() = checkContainsAll(
            """<div sly<caret>""",
            *allBlocks)

    fun testHtlBlocksFilteredByNamePart() = checkContainsAll(
            """<div te<caret>""",
            "data-sly-template", "data-sly-test", "data-sly-text", "data-sly-attribute")

    fun testShouldNotCompleteAlreadyTypedBlocks() = checkContainsAll(
            """<div data-sly-text="any" te<caret>""",
            "data-sly-template", "data-sly-test", "data-sly-attribute")

}
