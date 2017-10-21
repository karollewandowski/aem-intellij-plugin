package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.*

class HtlRepeatPropertiesCompletionTest : HtlCompletionTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/completion/repeat/properties/fixtures"

    private val allListProperties = HtlPredefinedProperty.values()
            .filter { it.context == HtlPredefinedPropertyContext.LIST }
            .map { it.identifier }
            .toTypedArray()

    fun testExplicitListVariableProperties() = checkContainsAll(*allListProperties)
    fun testExplicitNestedListVariableProperties() = checkContainsAll(*allListProperties)
    fun testImplicitListVariableProperties() = checkContainsAll(*allListProperties)
    fun testImplicitNestedListVariableProperties() = checkContainsAll(*allListProperties)
    fun testListVariablePropertiesFilteredBySingleLetterI() = checkContainsAll("index", "first", "middle")
    fun testMiddleListVariablePropertyAutoCompleted() = checkAutoCompleted()

    fun testPropertyAccessAfterGlobalPropertiesObject() = checkByTextDoesNotContainAnyOf(
            "$DOLLAR{properties}", allListProperties.asList())
    fun testPropertyAccessAfterGlobalPagePropertiesObject() = checkByTextDoesNotContainAnyOf(
            "$DOLLAR{pageProperties}", allListProperties.asList())
    fun testPropertyAccessAfterGlobalInheritedPagePropertiesObject() = checkByTextDoesNotContainAnyOf(
            "$DOLLAR{inheritedPageProperties}", allListProperties.asList())

}
