package co.nums.intellij.aem.service

import co.nums.intellij.aem.htl.file.HtlFileType
import co.nums.intellij.aem.settings.aemSettings
import com.intellij.openapi.project.Project
import com.intellij.psi.search.*
import com.intellij.psi.xml.XmlFile

interface AemFilesDetector {
    fun hasAemFiles(project: Project): Boolean
}

class AemFilesDetectorImpl : AemFilesDetector {

    override fun hasAemFiles(project: Project) = project.aemSettings.aemSupportEnabled
            && (project.hasHtlFiles() || project.hasContentFiles())

    private fun Project.hasHtlFiles() = FileTypeIndex.containsFileOfType(HtlFileType, GlobalSearchScope.allScope(this))

    private fun Project.hasContentFiles() = FilenameIndex.getFilesByName(this, ".content.xml", GlobalSearchScope.allScope(this))
            .any { (it as? XmlFile)?.rootTag?.localName == "root" }

    // add more detecting methods here if needed

}
