package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.definitions.HtlListProperty

class HtlRepeatPropertiesCompletionTest : HtlCompletionTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/completion/repeat/properties/fixtures"

    private val allListProperties = HtlListProperty.values().map { it.identifier }.toTypedArray()

    fun testExplicitListVariableProperties() = checkContainsAll(*allListProperties)
    fun testExplicitNestedListVariableProperties() = checkContainsAll(*allListProperties)
    fun testImplicitListVariableProperties() = checkContainsAll(*allListProperties)
    fun testImplicitNestedListVariableProperties() = checkContainsAll(*allListProperties)
    fun testListVariablePropertiesFilteredBySingleLetterI() = checkContainsAll("index", "first", "middle")
    fun testMiddleListVariablePropertyAutoCompleted() = checkAutoCompleted()

}
