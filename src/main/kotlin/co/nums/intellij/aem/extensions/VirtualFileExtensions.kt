package co.nums.intellij.aem.extensions

import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile

fun VirtualFile.getProjectRelativePath(project: Project?) = this.path.removePrefix(project?.basePath ?: "")
