package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.*

class HtlListPropertiesBracketAccessCompletionTest : HtlCompletionTestBase() {

    private val allListProperties = HtlPredefinedProperty.values()
            .filter { it.context == HtlPredefinedPropertyContext.LIST }
            .map { it.identifier }
            .toTypedArray()

    fun testExplicitListVariableProperties() = checkContainsAll("""
            <div data-sly-list.element="$DOLLAR{anyList}">
                $DOLLAR{elementList['<caret>']}
            </div>""",
            *allListProperties)

    fun testExplicitNestedListVariableProperties() = checkContainsAll("""
            <div data-sly-list.element="$DOLLAR{anyList}">
                <div data-sly-list="$DOLLAR{anyList.anySublist}">
                    $DOLLAR{elementList['<caret>']}
                </div>
            </div>""",
            *allListProperties)

    fun testImplicitListVariableProperties() = checkContainsAll("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{itemList['<caret>']}
            </div>""",
            *allListProperties)

    fun testImplicitNestedListVariableProperties() = checkContainsAll("""
            <div data-sly-list="$DOLLAR{anyList}">
                <div data-sly-list.element="$DOLLAR{anyList.anySublist}">
                    $DOLLAR{itemList['<caret>']}
                </div>
            </div>""",
            *allListProperties)

    fun testPropertyInLogicalExpression() = checkContainsAll("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{itemList[someOtherValue || '<caret>']}
            </div>""",
            *allListProperties)

    fun testListVariablePropertiesFilteredBySingleLetterI() = checkContainsAll("""
            <div data-sly-list.element="$DOLLAR{anyList}">
                $DOLLAR{elementList['i<caret>']}
            </div>""",
            "index", "first", "middle")

    fun testPropertyAccessAfterGlobalPropertiesObject() = checkDoesNotContainAnyOf("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{properties['<caret>']}
            </div>""",
            *allListProperties)

    fun testPropertyAccessAfterGlobalPagePropertiesObject() = checkDoesNotContainAnyOf("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{pageProperties['<caret>']}
            </div>""",
            *allListProperties)

    fun testPropertyAccessAfterGlobalInheritedPagePropertiesObject() = checkDoesNotContainAnyOf("""
            <div data-sly-list="$DOLLAR{anyList}">
                $DOLLAR{inheritedPageProperties['<caret>']}
            </div>""",
            *allListProperties)

    fun testMiddleListVariablePropertyAutoCompleted() = checkAutoCompleted("""
            <div data-sly-list.element="$DOLLAR{anyList}">
                $DOLLAR{elementList['mid<caret>']}
            </div>
            """, """
            <div data-sly-list.element="$DOLLAR{anyList}">
                $DOLLAR{elementList['middle<caret>']}
            </div>
            """)

}
