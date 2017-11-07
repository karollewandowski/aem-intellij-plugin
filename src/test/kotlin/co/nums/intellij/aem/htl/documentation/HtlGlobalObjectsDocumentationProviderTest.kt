package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlGlobalObjectsDocumentationProviderTest : HtlDocumentationProviderTestBase() {

    fun testPropertiesGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{properties}
                        ^
            """,
            "<code>org.apache.sling.api.resource.ValueMap</code><p>List of properties of the current resource.</p>"
    )

    fun testPagePropertiesGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{pageProperties}
                        ^
            """,
            "<code>org.apache.sling.api.resource.ValueMap</code><p>List of page properties of the current page.</p>"
    )

    fun testInheritedPagePropertiesGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{inheritedPageProperties}
                        ^
            """,
            "<code>org.apache.sling.api.resource.ValueMap</code><p>List of inherited page properties of the current page.</p>"
    )

    fun testComponentGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{component}
                        ^
            """,
            "<code>com.day.cq.wcm.api.components.Component</code><p>The current AEM component object of the current resource.</p>"
    )

    fun testComponentContextGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{componentContext}
                        ^
            """,
            "<code>com.day.cq.wcm.api.components.ComponentContext</code><p>The current component context object of the request.</p>"
    )

    fun testCurrentDesignGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{currentDesign}
                        ^
            """,
            "<code>com.day.cq.wcm.api.designer.Design</code><p>The current design object of the current page.</p>"
    )

    fun testCurrentNodeGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{currentNode}
                        ^
            """,
            "<code>javax.jcr.Node</code><p>The current JCR node object.</p>"
    )

    fun testCurrentPageGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{currentPage}
                        ^
            """,
            "<code>com.day.cq.wcm.api.Page</code><p>The current AEM WCM page object.</p>"
    )

    fun testCurrentSessionGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{currentSession}
                        ^
            """,
            "<code>javax.servlet.http.HttpSession</code><p>The current HTTP session object.</p>"
    )

    fun testCurrentStyleGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{currentStyle}
                        ^
            """,
            "<code>com.day.cq.wcm.api.designer.Style</code><p>The current style object of the current cell.</p>"
    )

    fun testDesignerGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{designer}
                        ^
            """,
            "<code>com.day.cq.wcm.api.designer.Designer</code><p>The designer object used to access design information.</p>"
    )

    fun testEditContextGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{editContext}
                        ^
            """,
            "<code>com.day.cq.wcm.api.components.EditContext</code><p>The edit context object of the AEM component.</p>"
    )

    fun testLogGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{log}
                     ^
            """,
            "<code>org.slf4j.Logger</code>"
    )

    fun testOutGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{out}
                     ^
            """,
            "<code>java.io.PrintWriter</code>"
    )

    fun testPageManagerGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{pageManager}
                        ^
            """,
            "<code>com.day.cq.wcm.api.PageManager</code><p>The page manager object for page level operations.</p>"
    )

    fun testReaderGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{reader}
                        ^
            """,
            "<code>java.io.BufferedReader</code>"
    )

    fun testRequestGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{request}
                        ^
            """,
            "<code>org.apache.sling.api.SlingHttpServletRequest</code><p>The current Sling HTTP request object.</p>"
    )

    fun testResolverGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{resolver}
                        ^
            """,
            "<code>org.apache.sling.api.resource.ResourceResolver</code><p>The Sling resource resolver object.</p>"
    )

    fun testResourceGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{resource}
                        ^
            """,
            "<code>org.apache.sling.api.resource.Resource</code><p>The current Sling resource object.</p>"
    )

    fun testResourceDesignGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{resourceDesign}
                        ^
            """,
            "<code>com.day.cq.wcm.api.designer.Design</code><p>The design object of the resource page.</p>"
    )

    fun testResourcePageGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{resourcePage}
                        ^
            """,
            "<code>com.day.cq.wcm.api.Page</code><p>The resource page object.</p>"
    )

    fun testResponseGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{response}
                        ^
            """,
            "<code>org.apache.sling.api.SlingHttpServletResponse</code><p>The current Sling HTTP response object.</p>"
    )

    fun testSlingGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{sling}
                      ^
            """,
            "<code>org.apache.sling.api.scripting.SlingScriptHelper</code><p>The Sling script helper object.</p>"
    )

    fun testSlyWcmHelperGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{slyWcmHelper}
                        ^
            """,
            "<code>com.adobe.cq.sightly.WCMScriptHelper</code>"
    )

    fun testWcmmodeGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{wcmmode}
                        ^
            """,
            "<code>com.adobe.cq.sightly.SightlyWCMMode</code><p>Object containing information about current WCM mode.</p>"
    )

    fun testXssAPIGlobalObjectDoc() = doTestWithDollarConstant(
            """
            $DOLLAR{xssAPI}
                      ^
            """,
            "<code>com.adobe.granite.xss.XSSAPI</code>"
    )

}
