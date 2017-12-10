package co.nums.intellij.aem.htl.highlighting

import co.nums.intellij.aem.htl.DOLLAR

class HtlExpressionsHighlightingTest : HtlHighlightingTestBase() {

    fun testPropertiesAccess() {
        testHighlighting(
                """$DOLLAR{obj.title || properties.jcr:title}""",

                "obj".withType(HtlHighlighterColors.VARIABLE),
                "title".withType(HtlHighlighterColors.PROPERTY_ACCESS),
                "properties".withType(HtlHighlighterColors.GLOBAL_OBJECT),
                "jcr:title".withType(HtlHighlighterColors.PROPERTY_ACCESS)
        )
    }

    fun testNestedPropertyAccess() {
        testHighlighting(
                """$DOLLAR{obj1[obj2.propName].property}""",

                "obj1".withType(HtlHighlighterColors.VARIABLE),
                "obj2".withType(HtlHighlighterColors.VARIABLE),
                "propName".withType(HtlHighlighterColors.PROPERTY_ACCESS),
                "property".withType(HtlHighlighterColors.PROPERTY_ACCESS)
        )
    }

    fun testExpressionOptions() {
        testHighlighting(
                """$DOLLAR{ @ option1, option2=true, option3=obj.property}""",

                "option1".withType(HtlHighlighterColors.OPTION_NAME),
                "option2=".withType(HtlHighlighterColors.OPTION_NAME),
                "option3=".withType(HtlHighlighterColors.OPTION_NAME),
                "obj".withType(HtlHighlighterColors.VARIABLE),
                "property".withType(HtlHighlighterColors.PROPERTY_ACCESS)
        )
    }

}
