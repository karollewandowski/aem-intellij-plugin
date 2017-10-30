package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR
import co.nums.intellij.aem.htl.definitions.HtlDisplayContext

class HtlDisplayContextsCompletionTest : HtlCompletionTestBase() {

    private val allDisplayContextsNames = HtlDisplayContext.values().map { it.type }.toTypedArray()

    fun testAfterContextOptionNotInString() = checkDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ context=<caret>}</div>",
            *allDisplayContextsNames)

    fun testAfterContextOptionInString() = checkContainsAll(
            "<div>$DOLLAR{ @ context='<caret>'}</div>",
            *allDisplayContextsNames)

    fun testInDefaultStringContextOption() = checkContainsAll(
            "<div>$DOLLAR{ @ context=someValue || '<caret>'}</div>",
            *allDisplayContextsNames)

    fun testAfterNotContextOption() = checkDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ somethingElse=<caret>}</div>",
            *allDisplayContextsNames)

    fun testAfterOptionIncludingContextInName() = checkDoesNotContainAnyOf(
            "<div>$DOLLAR{ @ notContext=<caret>}</div>",
            *allDisplayContextsNames)

}
