package co.nums.intellij.aem.htl

object HtlBlocks {

    const val PREFIX = "data-sly-"

    const val ATTRIBUTE = "data-sly-attribute"
    const val CALL = "data-sly-call"
    const val ELEMENT = "data-sly-element"
    const val INCLUDE = "data-sly-include"
    const val LIST = "data-sly-list"
    const val REPEAT = "data-sly-repeat"
    const val RESOURCE = "data-sly-resource"
    const val TEMPLATE = "data-sly-template"
    const val TEST = "data-sly-test"
    const val TEXT = "data-sly-text"
    const val UNWRAP = "data-sly-unwrap"
    const val USE = "data-sly-use"

    val ITERABLE = setOf(LIST, REPEAT)
    val VARIABLE_BLOCKS = setOf(HtlBlocks.USE, HtlBlocks.TEST, HtlBlocks.LIST, HtlBlocks.REPEAT, HtlBlocks.TEMPLATE)
    val SCOPED_VARIABLE_BLOCKS = setOf(HtlBlocks.LIST, HtlBlocks.REPEAT)
    val HOISTED_VARIABLE_BLOCKS = setOf(HtlBlocks.USE, HtlBlocks.TEST)

    fun isIterableBlock(name: String) = ITERABLE.contains(name.toLowerCase())
    fun isVariableBlock(name: String) = VARIABLE_BLOCKS.contains(name.toLowerCase())
    fun isScopedVariableBlock(name: String) = SCOPED_VARIABLE_BLOCKS.contains(name.toLowerCase())
    fun isHoistedVariableBlock(name: String) = HOISTED_VARIABLE_BLOCKS.contains(name.toLowerCase())
    fun isTemplateVariableBlock(name: String) = TEMPLATE == name.toLowerCase()

}
