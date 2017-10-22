package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.DOLLAR

class HtlGlobalObjectsDocumentationProviderTest : HtlDocumentationProviderTest() {

    fun testPropertiesGlobalObjectDoc() = doTest(
            """
            $DOLLAR{properties}
                        ^
            """,
            "<code>org.apache.sling.api.resource.ValueMap</code><p>List of properties of the current resource.</p>"
    )

    fun testPagePropertiesGlobalObjectDoc() = doTest(
            """
            $DOLLAR{pageProperties}
                        ^
            """,
            "<code>org.apache.sling.api.resource.ValueMap</code><p>List of page properties of the current page.</p>"
    )

    fun testInheritedPagePropertiesGlobalObjectDoc() = doTest(
            """
            $DOLLAR{inheritedPageProperties}
                        ^
            """,
            "<code>org.apache.sling.api.resource.ValueMap</code><p>List of inherited page properties of the current page.</p>"
    )

    fun testComponentGlobalObjectDoc() = doTest(
            """
            $DOLLAR{component}
                        ^
            """,
            "<code>com.day.cq.wcm.api.components.Component</code><p>The current AEM component object of the current resource.</p>"
    )

    fun testComponentContextGlobalObjectDoc() = doTest(
            """
            $DOLLAR{componentContext}
                        ^
            """,
            "<code>com.day.cq.wcm.api.components.ComponentContext</code><p>The current component context object of the request.</p>"
    )

    fun testCurrentDesignGlobalObjectDoc() = doTest(
            """
            $DOLLAR{currentDesign}
                        ^
            """,
            "<code>com.day.cq.wcm.api.designer.Design</code><p>The current design object of the current page.</p>"
    )

    fun testCurrentNodeGlobalObjectDoc() = doTest(
            """
            $DOLLAR{currentNode}
                        ^
            """,
            "<code>javax.jcr.Node</code><p>The current JCR node object.</p>"
    )

    fun testCurrentPageGlobalObjectDoc() = doTest(
            """
            $DOLLAR{currentPage}
                        ^
            """,
            "<code>com.day.cq.wcm.api.Page</code><p>The current AEM WCM page object.</p>"
    )

    fun testCurrentSessionGlobalObjectDoc() = doTest(
            """
            $DOLLAR{currentSession}
                        ^
            """,
            "<code>javax.servlet.http.HttpSession</code><p>The current HTTP session object.</p>"
    )

    fun testCurrentStyleGlobalObjectDoc() = doTest(
            """
            $DOLLAR{currentStyle}
                        ^
            """,
            "<code>com.day.cq.wcm.api.designer.Style</code><p>The current style object of the current cell.</p>"
    )

    fun testDesignerGlobalObjectDoc() = doTest(
            """
            $DOLLAR{designer}
                        ^
            """,
            "<code>com.day.cq.wcm.api.designer.Designer</code><p>The designer object used to access design information.</p>"
    )

    fun testEditContextGlobalObjectDoc() = doTest(
            """
            $DOLLAR{editContext}
                        ^
            """,
            "<code>com.day.cq.wcm.api.components.EditContext</code><p>The edit context object of the AEM component.</p>"
    )

    fun testLogGlobalObjectDoc() = doTest(
            """
            $DOLLAR{log}
                     ^
            """,
            "<code>org.slf4j.Logger</code>"
    )

    fun testOutGlobalObjectDoc() = doTest(
            """
            $DOLLAR{out}
                     ^
            """,
            "<code>java.io.PrintWriter</code>"
    )

    fun testPageManagerGlobalObjectDoc() = doTest(
            """
            $DOLLAR{pageManager}
                        ^
            """,
            "<code>com.day.cq.wcm.api.PageManager</code><p>The page manager object for page level operations.</p>"
    )

    fun testReaderGlobalObjectDoc() = doTest(
            """
            $DOLLAR{reader}
                        ^
            """,
            "<code>java.io.BufferedReader</code>"
    )

    fun testRequestGlobalObjectDoc() = doTest(
            """
            $DOLLAR{request}
                        ^
            """,
            "<code>org.apache.sling.api.SlingHttpServletRequest</code><p>The current Sling HTTP request object.</p>"
    )

    fun testResolverGlobalObjectDoc() = doTest(
            """
            $DOLLAR{resolver}
                        ^
            """,
            "<code>org.apache.sling.api.resource.ResourceResolver</code><p>The Sling resource resolver object.</p>"
    )

    fun testResourceGlobalObjectDoc() = doTest(
            """
            $DOLLAR{resource}
                        ^
            """,
            "<code>org.apache.sling.api.resource.Resource</code><p>The current Sling resource object.</p>"
    )

    fun testResourceDesignGlobalObjectDoc() = doTest(
            """
            $DOLLAR{resourceDesign}
                        ^
            """,
            "<code>com.day.cq.wcm.api.designer.Design</code><p>The design object of the resource page.</p>"
    )

    fun testResourcePageGlobalObjectDoc() = doTest(
            """
            $DOLLAR{resourcePage}
                        ^
            """,
            "<code>com.day.cq.wcm.api.Page</code><p>The resource page object.</p>"
    )

    fun testResponseGlobalObjectDoc() = doTest(
            """
            $DOLLAR{response}
                        ^
            """,
            "<code>org.apache.sling.api.SlingHttpServletResponse</code><p>The current Sling HTTP response object.</p>"
    )

    fun testSlingGlobalObjectDoc() = doTest(
            """
            $DOLLAR{sling}
                      ^
            """,
            "<code>org.apache.sling.api.scripting.SlingScriptHelper</code><p>The Sling script helper object.</p>"
    )

    fun testSlyWcmHelperGlobalObjectDoc() = doTest(
            """
            $DOLLAR{slyWcmHelper}
                        ^
            """,
            "<code>com.adobe.cq.sightly.WCMScriptHelper</code>"
    )

    fun testWcmmodeGlobalObjectDoc() = doTest(
            """
            $DOLLAR{wcmmode}
                        ^
            """,
            "<code>com.adobe.cq.sightly.SightlyWCMMode</code><p>Object containing information about current WCM mode.</p>"
    )

    fun testXssAPIGlobalObjectDoc() = doTest(
            """
            $DOLLAR{xssAPI}
                      ^
            """,
            "<code>com.adobe.granite.xss.XSSAPI</code>"
    )

}
