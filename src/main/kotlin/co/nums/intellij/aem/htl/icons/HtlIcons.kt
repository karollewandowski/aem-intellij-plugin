package co.nums.intellij.aem.htl.icons

import com.intellij.openapi.util.IconLoader

object HtlIcons {

    val HTL_FILE = loadIcon("htl-icon.png")
    val HTL_BLOCK = loadIcon("htl-block.png")
    val HTL_EXPRESSION_OPTION = loadIcon("htl-expression-option.png")
    val HTL_DISPLAY_CONTEXT = loadIcon("htl-display-context.png")
    val HTL_GLOBAL_OBJECT = loadIcon("htl-global-object.png")
    val HTL_PREDEFINED_PROPERTY = loadIcon("htl-predefined-property.png")

    private fun loadIcon(iconName: String) = IconLoader.getIcon("/icons/$iconName", HtlIcons::class.java)

}
