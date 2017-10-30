package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class DisallowedHtlAttributeBlockAnnotatorNotHtlFileTest : HtlAnnotatorTestBase() {

    fun testStyleAttribute() = checkAnnotationsInHtmlFile(
            """<div data-sly-attribute.style="any"></div>""")

    fun testStyleUpperCaseAttribute() = checkAnnotationsInHtmlFile(
            """<div data-sly-attribute.STYLE="any"></div>""")

    fun testOnAttribute() = checkAnnotationsInHtmlFile(
            """<div data-sly-attribute.on="any"></div>""")

    fun testOnUpperCaseAttribute() = checkAnnotationsInHtmlFile(
            """<div data-sly-attribute.ON="any"></div>""")

    fun testOnClickAttribute() = checkAnnotationsInHtmlFile(
            """<div data-sly-attribute.onclick="any"></div>""")

    fun testOnClickUpperCaseAttribute() = checkAnnotationsInHtmlFile(
            """<div data-sly-attribute.ONCLICK="any"></div>""")

    fun testOnClickWithDashAttribute() = checkAnnotationsInHtmlFile(
            """<div data-sly-attribute.on-click="any"></div>""")

    fun testOnClickWithDashUpperCaseAttribute() = checkAnnotationsInHtmlFile(
            """<div data-sly-attribute.ON-CLICK="any"></div>""")

}
