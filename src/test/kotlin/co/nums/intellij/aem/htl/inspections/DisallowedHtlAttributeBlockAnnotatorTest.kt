package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.HtlAnnotatorTestBase

class DisallowedHtlAttributeBlockAnnotatorTest : HtlAnnotatorTestBase() {

    fun testStyleAttribute() = checkAnnotations(
            """<div <error descr="The 'style' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.">data-sly-attribute.style</error>="any"></div>""")

    fun testStyleUpperCaseAttribute() = checkAnnotations(
            """<div <error descr="The 'style' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.">data-sly-attribute.STYLE</error>="any"></div>""")

    fun testOnAttribute() = checkAnnotations(
            """<div <error descr="The 'on' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.">data-sly-attribute.on</error>="any"></div>""")

    fun testOnUpperCaseAttribute() = checkAnnotations(
            """<div <error descr="The 'on' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.">data-sly-attribute.ON</error>="any"></div>""")

    fun testOnClickAttribute() = checkAnnotations(
            """<div <error descr="The 'onclick' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.">data-sly-attribute.onclick</error>="any"></div>""")

    fun testOnClickUpperCaseAttribute() = checkAnnotations(
            """<div <error descr="The 'onclick' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.">data-sly-attribute.ONCLICK</error>="any"></div>""")

    fun testOnClickWithDashAttribute() = checkAnnotations(
            """<div <error descr="The 'on-click' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.">data-sly-attribute.on-click</error>="any"></div>""")

    fun testOnClickWithDashUpperCaseAttribute() = checkAnnotations(
            """<div <error descr="The 'on-click' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.">data-sly-attribute.ON-CLICK</error>="any"></div>""")

    fun testPrefixedStyleAttribute() = checkAnnotations(
            """<div data-sly-attribute.prefixedstyle="any"></div>""")

    fun testSuffixedStyleAttribute() = checkAnnotations(
            """<div data-sly-attribute.stylesuffixed="any"></div>""")

}
