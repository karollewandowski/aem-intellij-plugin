package co.nums.intellij.aem.extensions

import co.nums.intellij.aem.service.jcrRoots
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.VirtualFile

fun VirtualFile.getProjectRelativePath(project: Project?) = path.removePrefix(project?.basePath ?: "")

fun VirtualFile.guessProject(): Project? = ProjectLocator.getInstance().guessProjectForFile(this)

fun VirtualFile.isHtlFile(project: Project) = this.isHtml() && project.jcrRoots.contains(this)

fun VirtualFile.isHtml() = extension?.toLowerCase() == "html"
