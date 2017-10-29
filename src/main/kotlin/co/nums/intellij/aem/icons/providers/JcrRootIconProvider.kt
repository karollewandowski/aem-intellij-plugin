package co.nums.intellij.aem.icons.providers

import co.nums.intellij.aem.extensions.getProjectRelativePath
import co.nums.intellij.aem.icons.AemIcons
import co.nums.intellij.aem.service.jcrRoots
import com.intellij.ide.IconProvider
import com.intellij.openapi.project.DumbAware
import com.intellij.psi.*
import javax.swing.Icon

class JcrRootIconProvider : IconProvider(), DumbAware {

    override fun getIcon(element: PsiElement, flags: Int):Icon? {
        val project = element.project
        val psiDirectory = element as? PsiDirectory ?: return null
        if (project.jcrRoots.isJcrContentRoot(psiDirectory.virtualFile.getProjectRelativePath(project))) {
            return AemIcons.JCR_ROOT_DIR
        }
        return null
    }

}
