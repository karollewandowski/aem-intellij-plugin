package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.psi.HtlTokenTypes
import com.intellij.openapi.editor.colors.EditorColorsScheme
import com.intellij.openapi.editor.ex.util.LayerDescriptor
import com.intellij.openapi.editor.ex.util.LayeredLexerEditorHighlighter
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.fileTypes.StdFileTypes
import com.intellij.openapi.fileTypes.SyntaxHighlighter
import com.intellij.openapi.fileTypes.SyntaxHighlighterFactory
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings

class HtlTemplateHighlighter(project: Project?, virtualFile: VirtualFile?, colors: EditorColorsScheme)
    : LayeredLexerEditorHighlighter(HtlSyntaxHighlighter, colors) {

    init {
        registerOuterLanguageHighlighter(project, virtualFile)
    }

    private fun registerOuterLanguageHighlighter(project: Project?, virtualFile: VirtualFile?) {
        val outerLanguageHighlighter = getOuterSyntaxHighlighter(project, virtualFile)
        registerLayer(HtlTokenTypes.HTML_FRAGMENT, LayerDescriptor(outerLanguageHighlighter, ""))
    }

    private fun getOuterSyntaxHighlighter(project: Project?, virtualFile: VirtualFile?): SyntaxHighlighter? {
        var fileType: FileType = StdFileTypes.PLAIN_TEXT
        if (project != null && virtualFile != null) {
            val language = TemplateDataLanguageMappings.getInstance(project).getMapping(virtualFile)
            fileType = language?.associatedFileType ?: HtlLanguage.getTemplateLanguageFileType()
        }
        return SyntaxHighlighterFactory.getSyntaxHighlighter(fileType, project, virtualFile)
    }

}
