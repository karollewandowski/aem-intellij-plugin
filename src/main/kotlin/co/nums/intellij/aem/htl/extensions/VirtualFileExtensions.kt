package co.nums.intellij.aem.htl.extensions

import co.nums.intellij.aem.service.jcrRoots
import com.intellij.ide.highlighter.HtmlFileType
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

fun VirtualFile.isHtlFile(project: Project) = this.isHtml() && this.isInJcrRootDirectory(project)

private fun VirtualFile.isHtml() = fileType === HtmlFileType.INSTANCE

private fun VirtualFile.isInJcrRootDirectory(project: Project) = project.jcrRoots.contains(this)
