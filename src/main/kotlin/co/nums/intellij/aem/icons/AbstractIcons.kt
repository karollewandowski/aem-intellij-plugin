package co.nums.intellij.aem.icons

import com.intellij.openapi.util.IconLoader

open class AbstractIcons(private val iconsDirectory: String) {

    protected fun loadIcon(iconName: String) =
            IconLoader.getIcon("/icons/$iconsDirectory/$iconName.png", AbstractIcons::class.java)

}
