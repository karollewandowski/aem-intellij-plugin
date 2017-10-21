package co.nums.intellij.aem.htl.definitions

enum class HtlDisplayContext(
        val type: String,
        val description: String
) {

    ATTRIBUTE(
            type = "attribute",
            description = "Applies HTML attribute value escaping."
    ),
    ATTRIBUTE_NAME(
            type = "attributeName",
            description = "Outputs nothing if the value doesn't correspond to the HTML attribute name syntax. Doesn't allow <code>style</code> and <code>on*</code> attributes."
    ),
    COMMENT(
            type = "comment",
            description = "Applies HTML comment escaping."
    ),
    ELEMENT_NAME(
            type = "elementName",
            description = "Allows only element names that are white-listed, outputs <code>div</code> otherwise."
    ),
    HTML(
            type = "html",
            description = "Removes markup that may contain XSS risks."
    ),
    NUMBER(
            type = "number",
            description = "Outputs zero if the value is not a number."
    ),
    SCRIPT_COMMENT(
            type = "scriptComment",
            description = "Outputs nothing if value is trying to break out of the JavaScript comment context."
    ),
    SCRIPT_REG_EXP(
            type = "scriptRegExp",
            description = "Applies JavaScript regular expression escaping."
    ),
    SCRIPT_STRING(
            type = "scriptString",
            description = "Applies JavaScript string escaping."
    ),
    SCRIPT_TOKEN(
            type = "scriptToken",
            description = "Outputs nothing if the value doesn't correspond to the JavaScript token syntax."
    ),
    STYLE_COMMENT(
            type = "styleComment",
            description = "Outputs nothing if value is trying to break out of the CSS comment context."
    ),
    STYLE_STRING(
            type = "styleString",
            description = "Applies CSS string escaping."
    ),
    STYLE_TOKEN(
            type = "styleToken",
            description = "Outputs nothing if the value doesn't correspond to the CSS token syntax."
    ),
    TEXT(
            type = "text",
            description = "Escapes all HTML tokens."
    ),
    UNSAFE(
            type = "unsafe",
            description = "Disables XSS protection."
    ),
    URI(
            type = "uri",
            description = "Outputs nothing if the value contains XSS risks."
    )

}
