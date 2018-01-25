package co.nums.intellij.aem.htl.completion

import co.nums.intellij.aem.htl.DOLLAR

class HtlBlockVariablesCompletionTest : HtlCompletionTestBase() {

    fun testShouldCompleteUseVariable() = checkContainsAll(
            """<div data-sly-use.myUse="any">$DOLLAR{<caret>}</div>""",
            "myUse")

    fun testShouldCompleteUseVariableBeforeDeclarationWhenInTheSameElement() = checkContainsAll(
            """<div test="$DOLLAR{<caret>}" data-sly-use.myUse="any"></div>""",
            "myUse")

    fun testShouldCompleteUseVariableAfterDeclarationWhenInTheSameElement() = checkContainsAll(
            """<div data-sly-use.myUse="any" test="$DOLLAR{<caret>}"></div>""",
            "myUse")

    fun testShouldNotCompleteUseVariableBeforeDeclarationElement() = checkDoesNotContainAnyOf(
            """$DOLLAR{<caret>}<div data-sly-use.myUse="any"></div>""",
            "myUse")

    fun testShouldCompleteListVariables() = checkContainsAll(
            """<div data-sly-list.myItem="any">$DOLLAR{<caret>}</div>""",
            "myItem", "myItemList")

    fun testShouldCompleteImplicitListVariables() = checkContainsAll(
            """<div data-sly-list="any">$DOLLAR{<caret>}</div>""",
            "item", "itemList")

    fun testShouldCompleteRepeatVariables() = checkContainsAll(
            """<div data-sly-repeat.myItem="any">$DOLLAR{<caret>}</div>""",
            "myItem", "myItemList")

    fun testShouldCompleteImplicitRepeatVariables() = checkContainsAll(
            """<div data-sly-repeat="any">$DOLLAR{<caret>}</div>""",
            "item", "itemList")

    fun testShouldCompleteMultipleVariables() = checkContainsAll("""
            <div data-sly-use.myUse="any" data-sly-list.myListItem="any" data-sly-repeat.myRepeatItem="any">
                $DOLLAR{<caret>}
            </div>""",
            "myUse", "myListItem", "myListItemList", "myRepeatItem", "myRepeatItemList")

    fun testShouldNotCompleteListVariableBeforeDeclaration() = checkDoesNotContainAnyOf(
            """<div test="$DOLLAR{<caret>}" data-sly-list.myItem="any"></div>""",
            "myItem", "myItemList")

    fun testShouldNotCompleteListVariableBeforeAfterDeclarationButOnTheSameElement() = checkDoesNotContainAnyOf(
            """<div data-sly-list.myItem="any" test="$DOLLAR{<caret>}"></div>""",
            "myItem", "myItemList")

    fun testShouldCompleteRepeatVariableOnTheSameElementBeforeDeclaration() = checkContainsAll(
            """<div test="$DOLLAR{<caret>}" data-sly-repeat.myItem="any"></div>""",
            "myItem", "myItemList")

    fun testShouldCompleteRepeatVariableOnTheSameElementAfterDeclaration() = checkContainsAll(
            """<div data-sly-repeat.myItem="any" test="$DOLLAR{<caret>}"></div>""",
            "myItem", "myItemList")

    fun testShouldNotCompleteListVariableAfterBlockElement() = checkDoesNotContainAnyOf(
            """<div data-sly-list.myItem="any"></div>$DOLLAR{<caret>}""",
            "myItem", "myItemList")

    fun testShouldNotCompleteRepeatVariableAfterBlockElement() = checkDoesNotContainAnyOf(
            """<div data-sly-repeat.myItem="any"></div>$DOLLAR{<caret>}""",
            "myItem", "myItemList")

    fun testShouldCompleteVariablesInNestedExpressions() = checkContainsAll("""
            <div data-sly-use.myUse="any">
                <div data-sly-list.myListItem="any">
                    <div data-sly-list.myRepeatItem="any">
                        $DOLLAR{<caret>}
                    </div>
                </div>
            </div>""",
            "myUse", "myListItem", "myListItemList", "myRepeatItem", "myRepeatItemList")

    fun testShouldCompleteTestVariableAfterBlockElement() = checkContainsAll(
            """<div data-sly-test.myTest="any"></div>$DOLLAR{<caret>}""",
            "myTest")

    fun testShouldCompleteTestVariableOnTheSameElementBeforeDeclaration() = checkContainsAll(
            """<div test="$DOLLAR{<caret>}" data-sly-test.myTest="any"></div>""",
            "myTest")

    fun testShouldCompleteTestVariableOnTheSameElementAfterDeclaration() = checkContainsAll(
            """<div data-sly-test.myTest="any" test="$DOLLAR{<caret>}"></div>""",
            "myTest")

    fun testShouldCompleteTestVariableAfterDeclarationElement() = checkContainsAll(
            """<div data-sly-test.myTest="any"></div>$DOLLAR{<caret>}""",
            "myTest")

    fun testShouldNotCompleteTestVariableBeforeDeclarationElement() = checkDoesNotContainAnyOf(
            """$DOLLAR{<caret>}<div data-sly-test.myTest="any"></div>""",
            "myTest")

    fun testShouldNotCompleteUseVariableInsideDeclaringAttribute() = checkDoesNotContainAnyOf(
            """<div data-sly-use.myUse="$DOLLAR{<caret>}"></div>""",
            "myUse")

    fun testShouldNotCompleteTestVariableInsideDeclaringAttribute() = checkDoesNotContainAnyOf(
            """<div data-sly-test.myTest="$DOLLAR{<caret>}"></div>""",
            "myTest")

    fun testShouldNotCompleteListVariableInsideDeclaringAttribute() = checkDoesNotContainAnyOf(
            """<div data-sly-list.myListItem="$DOLLAR{<caret>}"></div>""",
            "myListItem")

    fun testShouldNotCompleteRepeatVariableInsideDeclaringAttribute() = checkDoesNotContainAnyOf(
            """<div data-sly-repeat.myRepeatItem="$DOLLAR{<caret>}"></div>""",
            "myRepeatItem")

    fun testShouldNotCompleteUseVariableInsideOfTemplate() = checkDoesNotContainAnyOf("""
            <div data-sly-use.myUse="anything"></div>
            <template data-sly-template.myTemplate>$DOLLAR{<caret>}</template>""",
            "myUse")

    fun testShouldCompleteUseVariableInsideOfTemplateWhenItIsDeclaredInTemplate() = checkContainsAll("""
            <template data-sly-template.myTemplate>
                <div data-sly-use.myUse="anything"></div>
                $DOLLAR{<caret>}
            </template>""",
            "myUse")

    fun testShouldCompleteUseVariableAfterTemplate() = checkContainsAll("""
            <div data-sly-use.myUse="anything"></div>
            <template data-sly-template.myTemplate></template>
            $DOLLAR{<caret>}""",
            "myUse")

    fun testShouldNotCompleteTestVariableInsideOfTemplate() = checkDoesNotContainAnyOf("""
            <div data-sly-test.myTest="anything"></div>
            <template data-sly-template.myTemplate>$DOLLAR{<caret>}</template>""",
            "myTest")

    fun testShouldCompleteTestVariableInsideOfTemplateWhenItIsDeclaredInTemplate() = checkContainsAll("""
            <template data-sly-template.myTemplate>
                <div data-sly-test.myTest="anything"></div>
                $DOLLAR{<caret>}
            </template>""",
            "myTest")

    fun testShouldCompleteTestVariableAfterTemplate() = checkContainsAll("""
            <div data-sly-test.myTest="anything"></div>
            <template data-sly-template.myTemplate></template>
            $DOLLAR{<caret>}""",
            "myTest")

    fun testShouldNotCompleteListVariableInsideOfTemplate() = checkDoesNotContainAnyOf("""
            <div data-sly-list.myListItem="anything">
                <template data-sly-template.myTemplate>$DOLLAR{<caret>}</template>
            </div>""",
            "myListItem")

    fun testShouldCompleteListVariableInsideOfTemplateWhenItIsDeclaredInTemplate() = checkContainsAll("""
            <template data-sly-template.myTemplate>
                <div data-sly-list.myListItem="anything">
                    $DOLLAR{<caret>}
                </div>
            </template>""",
            "myListItem")

    fun testShouldCompleteListVariableAfterTemplate() = checkContainsAll("""
            <div data-sly-list.myListItem="anything">
                <template data-sly-template.myTemplate></template>
                $DOLLAR{<caret>}
            </div>""",
            "myListItem")

    fun testShouldNotCompleteRepeatVariableInsideOfTemplate() = checkDoesNotContainAnyOf("""
            <div data-sly-repeat.myRepeatItem="anything"></div>
            <template data-sly-template.myTemplate>$DOLLAR{<caret>}</template>""",
            "myRepeatItem")

    fun testShouldCompleteRepeatVariableInsideOfTemplateWhenItIsDeclaredInTemplate() = checkContainsAll("""
            <template data-sly-template.myTemplate>
                <div data-sly-repeat.myRepeatItem="anything">
                    $DOLLAR{<caret>}
                </div>
            </template>""",
            "myRepeatItem")

    fun testShouldCompleteRepeatVariableAfterTemplate() = checkContainsAll("""
            <div data-sly-repeat.myRepeatItem="anything">
                <template data-sly-template.myTemplate></template>
                $DOLLAR{<caret>}
            </div>""",
            "myRepeatItem")

}
