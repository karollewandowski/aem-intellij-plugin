package co.nums.intellij.aem.icons

import com.intellij.openapi.util.IconLoader

open class AbstractIcons(private val iconsFolderName: String) {

    protected fun loadIcon(iconName: String) =
            IconLoader.getIcon("/icons/$iconsFolderName/$iconName.png", AbstractIcons::class.java)

}
