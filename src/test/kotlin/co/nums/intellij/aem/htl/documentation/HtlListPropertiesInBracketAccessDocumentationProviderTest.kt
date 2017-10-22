package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlListPropertiesInBracketAccessDocumentationProviderTest : HtlDocumentationProviderTest() {

    fun testIndexPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties['index']}
                                  ^
            """,
            "<code>Number</code><p>zero-based counter (<code>0..length-1</code>)</p>"
    )

    fun testCountPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties['count']}
                                  ^
            """,
            "<code>Number</code><p>one-based counter (<code>1..length</code>)</p>"
    )

    fun testFirstPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties['first']}
                                  ^
            """,
            "<code>Boolean</code><p><code>true</code> for the first element being iterated</p>"
    )

    fun testMiddlePredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties['middle']}
                                  ^
            """,
            "<code>Boolean</code><p><code>true</code> if element being iterated is neither the first nor the last</p>"
    )

    fun testLastPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties['last']}
                                 ^
            """,
            "<code>Boolean</code><p><code>true</code> for the last element being iterated</p>"
    )

    fun testOddPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties['odd']}
                                 ^
            """,
            "<code>Boolean</code><p><code>true</code> if index is odd</p>"
    )

    fun testEvenPredefinedPropertyDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties['even']}
                                 ^
            """,
            "<code>Boolean</code><p><code>true</code> if index is even</p>"
    )

}
