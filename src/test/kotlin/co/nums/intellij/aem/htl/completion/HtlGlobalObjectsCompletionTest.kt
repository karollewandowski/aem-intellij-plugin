package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.HtlGlobalObject

class HtlGlobalObjectsCompletionTest : HtlCompletionTestBase() {

    private val allGlobalObjectsNames = HtlGlobalObject.values().map { it.identifier }.toTypedArray()

    fun testSimpleExpression() = checkContainsAll(
            "<div>$DOLLAR{<caret>}</div>",
            *allGlobalObjectsNames)

    fun testFilteredByNamePart() = checkContainsAll(
            "<div>$DOLLAR{pa<caret>}</div>",
            "pageManager", "pageProperties", "currentPage", "inheritedPageProperties", "resourcePage")

    fun testInLogicalOperator() = checkContainsAll(
            "<div>$DOLLAR{someValue || <caret>}</div>",
            *allGlobalObjectsNames)

    fun testInComparison() = checkContainsAll(
            "<div>$DOLLAR{someValue > <caret>}</div>",
            *allGlobalObjectsNames)

    fun testAfterNegation() = checkContainsAll(
            "<div>$DOLLAR{!<caret>}</div>",
            *allGlobalObjectsNames)

    fun testInDotPropertyAccess() = checkDoesNotContainAnyOf(
            "<div>$DOLLAR{someValue.<caret>}</div>",
            *allGlobalObjectsNames)

    fun testInStringInBracketPropertyAccess() = checkDoesNotContainAnyOf(
            "<div>$DOLLAR{someValue['<caret>']}</div>",
            *allGlobalObjectsNames)

    fun testInBracketPropertyAccess() = checkContainsAll(
            "<div>$DOLLAR{someValue[<caret>]}</div>",
            *allGlobalObjectsNames)

    fun testInOptionIdentifier() = checkDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ <caret>}</div>",
            *allGlobalObjectsNames)

    fun testInOptionValue() = checkContainsAll(
            "<div>$DOLLAR{ @ option1=<caret>}</div>",
            *allGlobalObjectsNames)

}
