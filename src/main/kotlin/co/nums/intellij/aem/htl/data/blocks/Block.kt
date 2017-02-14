package co.nums.intellij.aem.htl.data.blocks

import co.nums.intellij.aem.htl.HtlBlocks

data class Block(
        val name: String,
        val identifierType: BlockIdentifierType,
        val insertHandlerType: String?,
        val doc: BlockDocumentation
) {
    fun isIterable() = HtlBlocks.ITERABLE.contains(name.toLowerCase())
}

data class BlockDocumentation(
        val description: String,
        val element: String?,
        val elementContent: String?,
        val attributeValue: String?,
        val attributeIdentifier: String?
)
