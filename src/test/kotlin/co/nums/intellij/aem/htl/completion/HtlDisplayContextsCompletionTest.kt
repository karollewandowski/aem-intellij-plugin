package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.HtlDisplayContext

class HtlDisplayContextsCompletionTest : HtlCompletionTestBase() {

    private val allDisplayContextsNames = HtlDisplayContext.values().map { it.type }.toTypedArray()

    fun testAfterContextOptionNotInString() = checkByTextDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ context=<caret>}</div>",
            *allDisplayContextsNames)

    fun testAfterContextOptionInString() = checkByTextContainsAll(
            "<div>$DOLLAR{ @ context='<caret>'}</div>",
            *allDisplayContextsNames)

    fun testInDefaultStringContextOption() = checkByTextContainsAll(
            "<div>$DOLLAR{ @ context=someValue || '<caret>'}</div>",
            *allDisplayContextsNames)

    fun testAfterNotContextOption() = checkByTextDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ somethingElse=<caret>}</div>",
            *allDisplayContextsNames)

    fun testAfterOptionIncludingContextInName() = checkByTextDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ notContext=<caret>}</div>",
            *allDisplayContextsNames)

}
