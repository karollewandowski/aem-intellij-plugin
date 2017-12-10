package co.nums.intellij.aem.htl.highlighting

import co.nums.intellij.aem.htl.DOLLAR

class HtlBlocksHighlightingTest : HtlHighlightingTestBase() {

    fun testAttributeBlock() {
        testHighlighting(
                """<div data-sly-attribute.class="any"></div>""",

                "data-sly-attribute".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testElementBlock() {
        testHighlighting(
                """<ul data-sly-element="any"></ul>""",

                "data-sly-element".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testIncludeBlock() {
        testHighlighting(
                """<ul data-sly-include="any"></ul>""",

                "data-sly-include".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testListBlock() {
        testHighlighting(
                """<div data-sly-list.element="any"></div>""",

                "data-sly-list".withType(HtlHighlighterColors.BLOCK_TYPE),
                "element".withType(HtlHighlighterColors.VARIABLE)
        )
    }

    fun testListBlockWithoutIdentifier() {
        testHighlighting(
                """<div data-sly-list="any"></div>""",

                "data-sly-list".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testRepeatBlock() {
        testHighlighting(
                """<div data-sly-repeat.element="any"></div>""",

                "data-sly-repeat".withType(HtlHighlighterColors.BLOCK_TYPE),
                "element".withType(HtlHighlighterColors.VARIABLE)
        )
    }

    fun testRepeatBlockWithoutIdentifier() {
        testHighlighting(
                """<div data-sly-repeat="any"></div>""",

                "data-sly-repeat".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testResourceBlock() {
        testHighlighting(
                """<div data-sly-resource="any"></div>""",

                "data-sly-resource".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testTestBlock() {
        testHighlighting(
                """<div data-sly-test="any"></div>""",

                "data-sly-test".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testTestBlockWithIdentifier() {
        testHighlighting(
                """<div data-sly-test.result="any"></div>""",

                "data-sly-test".withType(HtlHighlighterColors.BLOCK_TYPE),
                "result".withType(HtlHighlighterColors.VARIABLE)
        )
    }

    fun testTextBlock() {
        testHighlighting(
                """<div data-sly-text="any"></div>""",

                "data-sly-text".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testUnwrapBlock() {
        testHighlighting(
                """<div data-sly-unwrap></div>""",

                "data-sly-unwrap".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

    fun testSimpleUseBlock() {
        testHighlighting(
                """<div data-sly-use.testUse="com.example.TestUse"></div>""",

                "data-sly-use".withType(HtlHighlighterColors.BLOCK_TYPE),
                "testUse".withType(HtlHighlighterColors.VARIABLE),
                "com.example.TestUse".withType(HtlHighlighterColors.USE_DECLARATION)
        )
    }

    fun testExpressionUseBlock() {
        testHighlighting(
                """<div data-sly-use.testUse="$DOLLAR{'com.example.TestUse' @ param1=value, param2}"></div>""",

                "data-sly-use".withType(HtlHighlighterColors.BLOCK_TYPE),
                "testUse".withType(HtlHighlighterColors.VARIABLE),
                "com.example.TestUse".withType(HtlHighlighterColors.USE_DECLARATION),
                "param1=".withType(HtlHighlighterColors.OPTION_NAME),
                "value".withType(HtlHighlighterColors.VARIABLE),
                "param2".withType(HtlHighlighterColors.OPTION_NAME)
        )
    }

    fun testTemplateBlock() {
        testHighlighting(
                """
                <template data-sly-template.testTemplate="$DOLLAR{ @ param1, param2='Default value'}">
                    $DOLLAR{param1}
                    $DOLLAR{param2}
                </template>
                """,

                "data-sly-template".withType(HtlHighlighterColors.BLOCK_TYPE),
                "testTemplate".withType(HtlHighlighterColors.TEMPLATE_IDENTIFIER),
                "param1".withType(HtlHighlighterColors.VARIABLE),
                "param2".withType(HtlHighlighterColors.VARIABLE),
                "param1".withType(HtlHighlighterColors.VARIABLE),
                "param2".withType(HtlHighlighterColors.VARIABLE)
        )
    }

    fun testCallBlock() {
        testHighlighting(
                """<sly data-sly-call="$DOLLAR{template @ param1, param2='Test string'}"></sly>""",

                "data-sly-call".withType(HtlHighlighterColors.BLOCK_TYPE),
                "template".withType(HtlHighlighterColors.TEMPLATE_IDENTIFIER),
                "param1".withType(HtlHighlighterColors.OPTION_NAME),
                "param2=".withType(HtlHighlighterColors.OPTION_NAME)
        )
    }

    fun testCallTemplateReferencedByDotPropertyAccessBlock() {
        testHighlighting(
                """<sly data-sly-call="$DOLLAR{lib.template @ param1, param2='Test string'}"></sly>""",

                "data-sly-call".withType(HtlHighlighterColors.BLOCK_TYPE),
                "lib".withType(HtlHighlighterColors.VARIABLE),
                "template".withType(HtlHighlighterColors.TEMPLATE_IDENTIFIER),
                "param1".withType(HtlHighlighterColors.OPTION_NAME),
                "param2=".withType(HtlHighlighterColors.OPTION_NAME)
        )
    }

    fun testCallTemplateReferencedWithComplexExpressionByVariableBlock() {
        testHighlighting(
                """<sly data-sly-call="$DOLLAR{lib[testObj.templateName] @ param1, param2='Test string'}"></sly>""",

                "data-sly-call".withType(HtlHighlighterColors.BLOCK_TYPE),
                "lib".withType(HtlHighlighterColors.VARIABLE),
                "testObj".withType(HtlHighlighterColors.VARIABLE),
                "templateName".withType(HtlHighlighterColors.PROPERTY_ACCESS),
                "param1".withType(HtlHighlighterColors.OPTION_NAME),
                "param2=".withType(HtlHighlighterColors.OPTION_NAME)
        )
    }

    fun testMixedCasesBlock() {
        testHighlighting(
                """<div dATa-sLY-TexT="any"></div>""",

                "dATa-sLY-TexT".withType(HtlHighlighterColors.BLOCK_TYPE)
        )
    }

}
