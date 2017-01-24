package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.constants.JcrConstants
import co.nums.intellij.aem.htl.HtlLanguage
import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.LanguageSubstitutor

class HtlLanguageSubstitutor : LanguageSubstitutor() {

    override fun getLanguage(file: VirtualFile, project: Project) =
            if (file.isHtml() && file.isInJcrRootDirectory()) HtlLanguage
            else null

    private fun VirtualFile.isHtml() = this.fileType === HtmlFileType.INSTANCE

    private fun VirtualFile.isInJcrRootDirectory(): Boolean {
        var parent: VirtualFile? = this.parent
        while (parent != null) {
            if (parent.name == JcrConstants.JCR_ROOT_DIRECTORY_NAME) {
                return true
            }
            parent = parent.parent
        }
        return false
    }

}
