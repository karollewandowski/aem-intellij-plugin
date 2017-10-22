package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlPredefinedPropertiesInBracketAccessDocumentationProviderTest : HtlDocumentationProviderTest() {

    fun testJcrTitlePredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['jcr:title']}
                                   ^
            """,
            "<code>String</code>"
    )

    fun testJcrDescriptionPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['jcr:description']}
                                     ^
            """,
            "<code>String</code>"
    )

    fun testJcrLanguagePredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['jcr:language']}
                                    ^
            """,
            "<code>String</code>"
    )

    fun testJcrCreatedPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['jcr:created']}
                                    ^
            """,
            "<code>Date</code>"
    )

    fun testJcrCreatedByPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['jcr:createdBy']}
                                     ^
            """,
            "<code>String</code>"
    )

    fun testJcrLastModifiedPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['jcr:lastModified']}
                                      ^
            """,
            "<code>Date</code>"
    )

    fun testJcrLastModifiedByPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['jcr:lastModifiedBy']}
                                        ^
            """,
            "<code>String</code>"
    )

    fun testJcrPrimaryTypePredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['jcr:primaryType']}
                                     ^
            """,
            "<code>String</code>"
    )

    fun testSlingKeyPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['sling:key']}
                                  ^
            """,
            "<code>String</code>"
    )

    fun testSlingMessagePredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['sling:message']}
                                     ^
            """,
            "<code>String</code>"
    )

    fun testSlingResourceTypePredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['sling:resourceType']}
                                    ^
            """,
            "<code>String</code>"
    )

    fun testSlingResourceSuperTypePredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['sling:resourceSuperType']}
                                        ^
            """,
            "<code>String</code>"
    )

    fun testCqLastModifiedPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['cq:lastModified']}
                                     ^
            """,
            "<code>Date</code>"
    )

    fun testCqLastModifiedByPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['cq:lastModifiedBy']}
                                       ^
            """,
            "<code>String</code>"
    )

    fun testCqTemplatePredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['cq:template']}
                                      ^
            """,
            "<code>String</code>"
    )

}
