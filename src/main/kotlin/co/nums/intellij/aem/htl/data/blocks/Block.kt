package co.nums.intellij.aem.htl.data.blocks

import co.nums.intellij.aem.htl.HtlBlocks

data class Block(
        val type: String,
        val identifierType: BlockIdentifierType,
        val insertHandlerType: String?,
        val doc: BlockDocumentation
) {
    fun isIterable() = HtlBlocks.ITERABLE.contains(type.toLowerCase())
}

data class BlockDocumentation(
        val description: String,
        val element: String?,
        val elementContent: String?,
        val attributeValue: String?,
        val attributeIdentifier: String?
)
