package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR

class HtlTemplateVariablesCompletionTest : HtlCompletionTestBase() {

    fun testShouldCompleteTemplateVariable() = checkContainsAll(
            """<template data-sly-template.myTemplate="$DOLLAR{ @ param}">
                $DOLLAR{<caret>}
            </template>""",
            "param")

    fun testShouldCompleteTemplateMultipleVariables() = checkContainsAll(
            """<template data-sly-template.myTemplate="$DOLLAR{ @ param1, param2}">
                $DOLLAR{<caret>}
            </template>""",
            "param1", "param2")

    fun testShouldNotCompleteTemplateVariableBeforeTemplate() = checkDoesNotContainAnyOf(
            """$DOLLAR{<caret>}<template data-sly-template.myTemplate="$DOLLAR{ @ param}"></template>""",
            "param")

    fun testShouldNotCompleteTemplateVariableAfterTemplate() = checkDoesNotContainAnyOf(
            """<template data-sly-template.myTemplate="$DOLLAR{ @ param}"></template>$DOLLAR{<caret>}""",
            "param")

}
