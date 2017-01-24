package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.htl.icons.HtlIcons
import co.nums.intellij.aem.htl.psi.HtlFile
import com.intellij.ide.IconProvider
import com.intellij.openapi.util.Iconable
import com.intellij.psi.PsiElement

class HtlIconProvider : IconProvider() {

    override fun getIcon(element: PsiElement, @Iconable.IconFlags flags: Int) = when (element) {
        is HtlFile -> HtlIcons.HTL_FILE
        else -> null
    }

}
