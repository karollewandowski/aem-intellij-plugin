package co.nums.intellij.aem.extensions

import co.nums.intellij.aem.service.jcrRoots
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*

fun VirtualFile.getProjectRelativePath(project: Project?) = path.removePrefix(project?.basePath ?: "")

fun VirtualFile.guessProject(): Project? = ApplicationManager.getApplication().runReadAction<Project?, Throwable> { ProjectLocator.getInstance().guessProjectForFile(this) }

fun VirtualFile.isHtlFile(project: Project) = this.isHtml() && project.jcrRoots?.contains(this) ?: false

fun VirtualFile.isHtlFile() = this.isHtml() && this.guessProject()?.jcrRoots?.contains(this) ?: false

fun VirtualFile.isHtml() = extension?.toLowerCase() == "html"

fun VirtualFile.containsFile(predicate: (VirtualFile) -> (Boolean)): Boolean {
    var found = false
    VfsUtilCore.visitChildrenRecursively(this, object : VirtualFileVisitor<VirtualFile>() {
        override fun visitFileEx(file: VirtualFile): Result {
            if (!found && predicate.invoke(file)) {
                found = true
                return SKIP_CHILDREN
            }
            return CONTINUE
        }
    })
    return found
}
