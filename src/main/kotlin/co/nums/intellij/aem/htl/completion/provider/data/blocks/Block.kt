package co.nums.intellij.aem.htl.completion.provider.data.blocks

data class Block(
        val name: String,
        val insertHandlerType: String?,
        val doc: BlockDocumentation
)

data class BlockDocumentation(
        val description: String,
        val element: String?,
        val elementContent: String?,
        val attributeValue: String?,
        val attributeIdentifier: String?
)
