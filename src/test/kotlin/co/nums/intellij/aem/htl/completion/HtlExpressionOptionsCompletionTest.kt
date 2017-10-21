package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.HtlExpressionOption

class HtlExpressionOptionsCompletionTest : HtlCompletionTestBase() {

    private val allOptions = HtlExpressionOption.values()
            .map { it.identifier }
            .toTypedArray()

    fun testExpressionOption() = checkByTextContainsAll(
            "<div>$DOLLAR{ @ <caret>}</div>",
            *allOptions)

    fun testMultipleExpressionOptions() = checkByTextContainsAll(
            "<div>$DOLLAR{ @ option1='test value', <caret>}</div>",
            *allOptions)

    fun testIdentifierInExpressionPart() = checkByTextDoesNotContainAnyOf(
            "<div>$DOLLAR{<caret>}</div>",
            *allOptions)

    fun testIdentifierInOptionValue() = checkByTextDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ option1=<caret>}</div>",
            *allOptions)

}
