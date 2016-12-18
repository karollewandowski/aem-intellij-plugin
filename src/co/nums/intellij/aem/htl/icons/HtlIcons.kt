package co.nums.intellij.aem.htl.icons

import com.intellij.openapi.util.IconLoader

import javax.swing.Icon

object HtlIcons {

    val HTL_FILE = loadIcon("htl-icon.png")
    val HTL_BLOCK = loadIcon("htl-block.png")
    val HTL_EXPRESSION_OPTION = loadIcon("htl-expression-option.png")
    val HTL_DISPLAY_CONTEXT = loadIcon("htl-display-context.png")

    private fun loadIcon(iconName: String): Icon {
        return IconLoader.getIcon("/icons/" + iconName, HtlIcons::class.java)
    }

}
