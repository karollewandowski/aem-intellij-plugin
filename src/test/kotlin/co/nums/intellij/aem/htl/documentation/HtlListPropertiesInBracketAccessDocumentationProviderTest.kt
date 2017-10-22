package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlListPropertiesInBracketAccessDocumentationProviderTest : HtlDocumentationProviderTest() {

    fun testIndexPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['index']}
                                  ^
            """,
            "<code>Number</code><p>zero-based counter (<code>0..length-1</code>)</p>"
    )

    fun testCountPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['count']}
                                  ^
            """,
            "<code>Number</code><p>one-based counter (<code>1..length</code>)</p>"
    )

    fun testFirstPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['first']}
                                  ^
            """,
            "<code>Boolean</code><p><code>true</code> for the first element being iterated</p>"
    )

    fun testMiddlePredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['middle']}
                                  ^
            """,
            "<code>Boolean</code><p><code>true</code> if element being iterated is neither the first nor the last</p>"
    )

    fun testLastPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['last']}
                                 ^
            """,
            "<code>Boolean</code><p><code>true</code> for the last element being iterated</p>"
    )

    fun testOddPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['odd']}
                                 ^
            """,
            "<code>Boolean</code><p><code>true</code> if index is odd</p>"
    )

    fun testEvenPredefinedPropertyDoc() = doTest(
            """
            $DOLLAR{properties['even']}
                                 ^
            """,
            "<code>Boolean</code><p><code>true</code> if index is even</p>"
    )

}
