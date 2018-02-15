package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.extensions.isHtlFile
import com.intellij.openapi.util.Condition
import com.intellij.openapi.vfs.VirtualFile

class HtlProblemFileHighlightFilter : Condition<VirtualFile> {

    override fun value(virtualFile: VirtualFile?) = virtualFile?.isHtlFile() == true

}
