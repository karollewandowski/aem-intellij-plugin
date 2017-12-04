package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.extensions.*
import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.highlighting.HtlTemplateHighlighter
import co.nums.intellij.aem.icons.HtlIcons
import co.nums.intellij.aem.settings.aemSettings
import com.intellij.openapi.fileTypes.*
import com.intellij.openapi.fileTypes.ex.FileTypeIdentifiableByVirtualFile
import com.intellij.openapi.vfs.VirtualFile

object HtlFileType : LanguageFileType(HtlLanguage), TemplateLanguageFileType, FileTypeIdentifiableByVirtualFile {

    init {
        FileTypeEditorHighlighterProviders.INSTANCE.addExplicitExtension(this) { project, _, virtualFile, editorColorsScheme ->
            HtlTemplateHighlighter(project, virtualFile, editorColorsScheme)
        }
    }

    override fun getName() = "HTL"

    override fun getDescription() = "HTML Template Language file"

    override fun getDefaultExtension() = "htl" // must be other than html, to be properly substituted

    override fun getIcon() = HtlIcons.HTL_FILE

    override fun isMyFileType(file: VirtualFile): Boolean {
        if (file.isDirectory) return false
        val project = file.guessProject() ?: return false
        return project.aemSettings.aemSupportEnabled && file.isHtlFile(project)
    }

}
