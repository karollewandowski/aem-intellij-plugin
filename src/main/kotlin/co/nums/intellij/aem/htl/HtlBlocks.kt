package co.nums.intellij.aem.htl

object HtlBlocks {

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

}
