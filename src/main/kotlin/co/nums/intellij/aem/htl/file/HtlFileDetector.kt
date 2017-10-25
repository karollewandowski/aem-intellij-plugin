package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.constants.JcrConstants
import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.openapi.vfs.VirtualFile

object HtlFileDetector {

    fun isHtlFile(file: VirtualFile) = file.isHtml() && file.isInJcrRootDirectory()

    private fun VirtualFile.isHtml() = fileType === HtmlFileType.INSTANCE

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
