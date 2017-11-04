package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.service.jcrRoots
import co.nums.intellij.aem.settings.aemSettings
import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.LanguageSubstitutor

class HtlLanguageSubstitutor : LanguageSubstitutor() {

    override fun getLanguage(file: VirtualFile, project: Project) =
            if (project.aemSettings.aemSupportEnabled && file.isHtlFile(project)) HtlLanguage
            else null

    private fun VirtualFile.isHtlFile(project: Project) = this.isHtml() && this.isInJcrRootDirectory(project)

    private fun VirtualFile.isHtml() = fileType === HtmlFileType.INSTANCE

    private fun VirtualFile.isInJcrRootDirectory(project: Project) = project.jcrRoots.contains(this)


}
