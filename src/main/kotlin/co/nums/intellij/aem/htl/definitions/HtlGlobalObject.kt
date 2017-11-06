package co.nums.intellij.aem.htl.definitions

enum class HtlGlobalObject(
        val identifier: String,
        val type: String,
        val description: String? = null,
        val predefinedPropertiesHolder: Boolean = false
) {

    PROPERTIES(
            identifier = "properties",
            type = "org.apache.sling.api.resource.ValueMap",
            description = "List of properties of the current resource.",
            predefinedPropertiesHolder = true
    ),
    PAGE_PROPERTIES(
            identifier = "pageProperties",
            type = "org.apache.sling.api.resource.ValueMap",
            description = "List of page properties of the current page.",
            predefinedPropertiesHolder = true
    ),
    INHERITED_PAGE_PROPERTIES(
            identifier = "inheritedPageProperties",
            type = "org.apache.sling.api.resource.ValueMap",
            description = "List of inherited page properties of the current page.",
            predefinedPropertiesHolder = true
    ),
    COMPONENT(
            identifier = "component",
            type = "com.day.cq.wcm.api.components.Component",
            description = "The current AEM component object of the current resource."
    ),
    COMPONENT_CONTEXT(
            identifier = "componentContext",
            type = "com.day.cq.wcm.api.components.ComponentContext",
            description = "The current component context object of the request."
    ),
    CURRENT_DESIGN(
            identifier = "currentDesign",
            type = "com.day.cq.wcm.api.designer.Design",
            description = "The current design object of the current page."
    ),
    CURRENT_NODE(
            identifier = "currentNode",
            type = "javax.jcr.Node",
            description = "The current JCR node object."
    ),
    CURRENT_PAGE(
            identifier = "currentPage",
            type = "com.day.cq.wcm.api.Page",
            description = "The current AEM WCM page object."
    ),
    CURRENT_SESSION(
            identifier = "currentSession",
            type = "javax.servlet.http.HttpSession",
            description = "The current HTTP session object."
    ),
    CURRENT_STYLE(
            identifier = "currentStyle",
            type = "com.day.cq.wcm.api.designer.Style",
            description = "The current style object of the current cell."
    ),
    DESIGNER(
            identifier = "designer",
            type = "com.day.cq.wcm.api.designer.Designer",
            description = "The designer object used to access design information."
    ),
    EDIT_CONTEXT(
            identifier = "editContext",
            type = "com.day.cq.wcm.api.components.EditContext",
            description = "The edit context object of the AEM component."
    ),
    LOG(
            identifier = "log",
            type = "org.slf4j.Logger"
    ),
    OUT(
            identifier = "out",
            type = "java.io.PrintWriter"
    ),
    PAGE_MANAGER(
            identifier = "pageManager",
            type = "com.day.cq.wcm.api.PageManager",
            description = "The page manager object for page level operations."
    ),
    READER(
            identifier = "reader",
            type = "java.io.BufferedReader"
    ),
    REQUEST(
            identifier = "request",
            type = "org.apache.sling.api.SlingHttpServletRequest",
            description = "The current Sling HTTP request object."
    ),
    RESOLVER(
            identifier = "resolver",
            type = "org.apache.sling.api.resource.ResourceResolver",
            description = "The Sling resource resolver object."
    ),
    RESOURCE(
            identifier = "resource",
            type = "org.apache.sling.api.resource.Resource",
            description = "The current Sling resource object."
    ),
    RESOURCE_DESIGN(
            identifier = "resourceDesign",
            type = "com.day.cq.wcm.api.designer.Design",
            description = "The design object of the resource page."
    ),
    RESOURCE_PAGE(
            identifier = "resourcePage",
            type = "com.day.cq.wcm.api.Page",
            description = "The resource page object."
    ),
    RESPONSE(
            identifier = "response",
            type = "org.apache.sling.api.SlingHttpServletResponse",
            description = "The current Sling HTTP response object."
    ),
    SLING(
            identifier = "sling",
            type = "org.apache.sling.api.scripting.SlingScriptHelper",
            description = "The Sling script helper object."
    ),
    SLY_WCM_HELPER(
            identifier = "slyWcmHelper",
            type = "com.adobe.cq.sightly.WCMScriptHelper"
    ),
    WCM_MODE(
            identifier = "wcmmode",
            type = "com.adobe.cq.sightly.SightlyWCMMode",
            description = "Object containing information about current WCM mode."
    ),
    XSS_API(
            identifier = "xssAPI",
            type = "com.adobe.granite.xss.XSSAPI"
    );

    companion object {

        val allIdentifiers = HtlGlobalObject.values()
                .map { it.identifier }

        val predefinedPropertiesHoldersIdentifiers = HtlGlobalObject.values()
                .filter { it.predefinedPropertiesHolder }
                .map { it.identifier }

    }

}
