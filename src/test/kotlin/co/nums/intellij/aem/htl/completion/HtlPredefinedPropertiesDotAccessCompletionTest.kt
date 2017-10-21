package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.*

class HtlPredefinedPropertiesDotAccessCompletionTest : HtlCompletionTestBase() {

    private val allPredefinedProperties = HtlPredefinedProperty.values()
            .filter { it.context == HtlPredefinedPropertyContext.GLOBAL_PROPERTIES_OBJECT }
            .map { it.identifier }
            .toTypedArray()

    fun testPropertiesGlobalObject() =
            checkByTextContainsAll("<div>$DOLLAR{properties.<caret></div>}", *allPredefinedProperties)

    fun testPagePropertiesGlobalObject() =
            checkByTextContainsAll("<div>$DOLLAR{pageProperties.<caret></div>}", *allPredefinedProperties)

    fun testInheritedPagePropertiesGlobalObject() =
            checkByTextContainsAll("<div>$DOLLAR{inheritedPageProperties.<caret></div>}", *allPredefinedProperties)

    fun testPropertiesGlobalObjectInAttributeProperty() =
            checkByTextContainsAll("""<div style="$DOLLAR{properties.<caret>}"></div>""", *allPredefinedProperties)

    fun testPredefinedPropertiesAfterImplicitListIdentifier() = checkByTextDoesNotContainAnyOf("""
        <div data-sly-list="$DOLLAR{anyList}">
            $DOLLAR{itemList.<caret>}
        </div>
        """, *allPredefinedProperties)

    fun testPredefinedPropertiesAfterExplicitListIdentifier() = checkByTextDoesNotContainAnyOf("""
        <div data-sly-list.product="$DOLLAR{anyList}">
            $DOLLAR{productList.<caret>}
        </div>
        """, *allPredefinedProperties)

}
