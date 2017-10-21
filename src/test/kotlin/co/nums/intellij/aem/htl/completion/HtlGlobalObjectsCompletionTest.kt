package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.HtlGlobalObject

class HtlGlobalObjectsCompletionTest : HtlCompletionTestBase() {

    private val allGlobalObjectsNames = HtlGlobalObject.values().map { it.identifier }.toTypedArray()

    fun testSimpleExpression() = checkByTextContainsAll(
            "<div>$DOLLAR{<caret>}</div>",
            *allGlobalObjectsNames)

    fun testFilteredByNamePart() = checkByTextContainsAll(
            "<div>$DOLLAR{pa<caret>}</div>",
            "pageManager", "pageProperties", "currentPage", "inheritedPageProperties", "resourcePage")

    fun testInLogicalOperator() = checkByTextContainsAll(
            "<div>$DOLLAR{someValue || <caret>}</div>",
            *allGlobalObjectsNames)

    fun testInComparison() = checkByTextContainsAll(
            "<div>$DOLLAR{someValue > <caret>}</div>",
            *allGlobalObjectsNames)

    fun testAfterNegation() = checkByTextContainsAll(
            "<div>$DOLLAR{!<caret>}</div>",
            *allGlobalObjectsNames)

    fun testInDotPropertyAccess() = checkByTextContainsAll(
            "<div>$DOLLAR{someValue.<caret>}</div>",
            *emptyArray()) // only properties should be listed

    fun testInStringInBracketPropertyAccess() = checkByTextContainsAll(
            "<div>$DOLLAR{someValue['<caret>']}</div>",
            *emptyArray()) // only properties should be listed

    fun testInBracketPropertyAccess() = checkByTextContainsAll(
            "<div>$DOLLAR{someValue[<caret>]}</div>",
            *allGlobalObjectsNames)

    fun testInOptionIdentifier() = checkByTextContainsAll(
            "<div>$DOLLAR{ @ <caret>}</div>",
            *emptyArray()) // only options should be listed

    fun testInOptionValue() = checkByTextContainsAll(
            "<div>$DOLLAR{ @ option1=<caret>}</div>",
            *allGlobalObjectsNames)

}
