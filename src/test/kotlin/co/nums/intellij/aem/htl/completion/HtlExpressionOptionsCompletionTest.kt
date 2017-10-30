package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.HtlExpressionOption

class HtlExpressionOptionsCompletionTest : HtlCompletionTestBase() {

    private val allOptions = HtlExpressionOption.values()
            .map { it.identifier }
            .toTypedArray()

    fun testExpressionOption() = checkContainsAll(
            "<div>$DOLLAR{ @ <caret>}</div>",
            *allOptions)

    fun testMultipleExpressionOptions() = checkContainsAll(
            "<div>$DOLLAR{ @ option1='test value', <caret>}</div>",
            *allOptions)

    fun testIdentifierInExpressionPart() = checkDoesNotContainAnyOf(
            "<div>$DOLLAR{<caret>}</div>",
            *allOptions)

    fun testIdentifierInOptionValue() = checkDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ option1=<caret>}</div>",
            *allOptions)

}
