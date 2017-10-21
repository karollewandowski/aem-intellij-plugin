package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.*

class HtlListPropertiesBracketAccessCompletionTest : HtlCompletionTestBase() {

    override val dataPath = "co/nums/intellij/aem/htl/completion/list/properties/fixtures"

    private val allListProperties = HtlPredefinedProperty.values()
            .filter { it.context == HtlPredefinedPropertyContext.LIST }
            .map { it.identifier }
            .toTypedArray()

    fun testExplicitListVariableProperties() = checkByTextContainsAll("""
            <div data-sly-list.element="$DOLLAR{anyList}">
                $DOLLAR{elementList['<caret>']}
            </div>""",
            *allListProperties)

    fun testExplicitNestedListVariableProperties() = checkByTextContainsAll("""
            <div data-sly-list.element="$DOLLAR{anyList}">
                <div data-sly-list="$DOLLAR{anyList.anySublist}">
                    $DOLLAR{elementList['<caret>']}
                </div>
            </div>""",
            *allListProperties)

    fun testImplicitListVariableProperties() = checkByTextContainsAll("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{itemList['<caret>']}
            </div>""",
            *allListProperties)

    fun testImplicitNestedListVariableProperties() = checkByTextContainsAll("""
            <div data-sly-list="$DOLLAR{anyList}">
                <div data-sly-list.element="$DOLLAR{anyList.anySublist}">
                    $DOLLAR{itemList['<caret>']}
                </div>
            </div>""",
            *allListProperties)

    fun testPropertyInLogicalExpression() = checkByTextContainsAll("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{itemList[someOtherValue || '<caret>']}
            </div>""",
            *allListProperties)

    fun testListVariablePropertiesFilteredBySingleLetterI() = checkByTextContainsAll("""
            <div data-sly-list.element="$DOLLAR{anyList}">
                $DOLLAR{elementList['i<caret>']}
            </div>""",
            "index", "first", "middle")

    fun testPropertyAccessAfterGlobalPropertiesObject() = checkByTextDoesNotContainAnyOf("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{properties['<caret>']}
            </div>""",
            *allListProperties)

    fun testPropertyAccessAfterGlobalPagePropertiesObject() = checkByTextDoesNotContainAnyOf("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{pageProperties['<caret>']}
            </div>""",
            *allListProperties)

    fun testPropertyAccessAfterGlobalInheritedPagePropertiesObject() = checkByTextDoesNotContainAnyOf("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{inheritedPageProperties['<caret>']}
            </div>""",
            *allListProperties)

    fun testMiddleListVariablePropertyAutoCompleted() = checkAutoCompleted()

}
