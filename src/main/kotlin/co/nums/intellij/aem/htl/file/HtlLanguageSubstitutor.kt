package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.extensions.isHtlFile
import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.settings.aemSettings
import com.intellij.lang.Language
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.LanguageSubstitutor

class HtlLanguageSubstitutor : LanguageSubstitutor() {

    override fun getLanguage(file: VirtualFile, project: Project): Language? =
            if (project.aemSettings.aemSupportEnabled && file.isHtlFile(project)) HtlLanguage
            else null

}
