package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlPredefinedPropertiesInDotAccessDocumentationProviderTest : HtlDocumentationProviderTest() {

    fun testJcrTitlePredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.jcr:title}
                                  ^
            """,
            "<code>String</code>"
    )

    fun testJcrDescriptionPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.jcr:description}
                                    ^
            """,
            "<code>String</code>"
    )

    fun testJcrLanguagePredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.jcr:language}
                                   ^
            """,
            "<code>String</code>"
    )

    fun testJcrCreatedPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.jcr:created}
                                   ^
            """,
            "<code>Date</code>"
    )

    fun testJcrCreatedByPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.jcr:createdBy}
                                    ^
            """,
            "<code>String</code>"
    )

    fun testJcrLastModifiedPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.jcr:lastModified}
                                     ^
            """,
            "<code>Date</code>"
    )

    fun testJcrLastModifiedByPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.jcr:lastModifiedBy}
                                       ^
            """,
            "<code>String</code>"
    )

    fun testJcrPrimaryTypePredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.jcr:primaryType}
                                    ^
            """,
            "<code>String</code>"
    )

    fun testSlingKeyPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.sling:key}
                                 ^
            """,
            "<code>String</code>"
    )

    fun testSlingMessagePredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.sling:message}
                                    ^
            """,
            "<code>String</code>"
    )

    fun testSlingResourceTypePredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.sling:resourceType}
                                   ^
            """,
            "<code>String</code>"
    )

    fun testSlingResourceSuperTypePredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.sling:resourceSuperType}
                                       ^
            """,
            "<code>String</code>"
    )

    fun testCqLastModifiedPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.cq:lastModified}
                                    ^
            """,
            "<code>Date</code>"
    )

    fun testCqLastModifiedByPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.cq:lastModifiedBy}
                                      ^
            """,
            "<code>String</code>"
    )

    fun testCqTemplatePredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties.cq:template}
                                     ^
            """,
            "<code>String</code>"
    )

}
