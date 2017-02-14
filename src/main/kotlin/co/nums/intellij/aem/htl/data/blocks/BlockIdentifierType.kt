package co.nums.intellij.aem.htl.data.blocks

enum class BlockIdentifierType {

    NONE,
    ATTRIBUTE_NAME,
    TEMPLATE_NAME,
    BLOCK_VARIABLE,
    HOISTED_VARIABLE;

    fun isVariable() = (this == HOISTED_VARIABLE || this == BLOCK_VARIABLE)

}
