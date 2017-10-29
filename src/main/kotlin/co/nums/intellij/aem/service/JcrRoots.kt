package co.nums.intellij.aem.service

import co.nums.intellij.aem.constants.JCR_ROOT_DIRECTORY_NAME
import co.nums.intellij.aem.extensions.getProjectRelativePath
import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.*
import com.intellij.openapi.vfs.VfsUtilCore.visitChildrenRecursively
import java.util.*

interface JcrRoots {
    fun isJcrContentRoot(path: String): Boolean
    fun isNotEmpty(): Boolean
    fun contains(file: VirtualFile): Boolean
}


class JcrRootsImpl(private val project: Project) : JcrRoots {

    private val jcrDetectableDirectories = setOf(
            "apps",
            "conf",
            "content",
            "etc",
            "home",
            "libs"
    )

    private val detectedJcrContentRoots = findPotentialRoots(project)
            .filter { it.hasContentXmlFile() }
            .map { it.getProjectRelativePath(project) }
            .toHashSet()

    private val markedJcrContentRoots: MutableSet<String> = HashSet()
    private val unmarkedJcrContentRoots: MutableSet<String> = HashSet()

    private fun findPotentialRoots(project: Project): MutableSet<VirtualFile> {
        val potentialRoots: MutableSet<VirtualFile> = HashSet()
        visitChildrenRecursively(project.baseDir, object : VirtualFileVisitor<VirtualFile>() {
            override fun visitFileEx(file: VirtualFile): Result {
                if (file.isDirectory) {
                    if (file.name == JCR_ROOT_DIRECTORY_NAME) {
                        potentialRoots.add(file)
                        return SKIP_CHILDREN
                    } else if (jcrDetectableDirectories.contains(file.name)) {
                        if (file.isJcrRootDirectoryNamedAsContent()) {
                            potentialRoots.add(file)
                        } else {
                            potentialRoots.add(file.parent)
                        }
                        return SKIP_CHILDREN
                    }
                }
                return CONTINUE
            }
        })
        return potentialRoots
    }

    private fun VirtualFile.isJcrRootDirectoryNamedAsContent() =
            this.name == "content" && this.children
                    .map(VirtualFile::getName)
                    .any { jcrDetectableDirectories.contains(it) }

    private fun VirtualFile.hasContentXmlFile(): Boolean {
        var contentXmlFound = false
        visitChildrenRecursively(this, object : VirtualFileVisitor<VirtualFile>() {
            override fun visitFileEx(file: VirtualFile): Result {
                if (!contentXmlFound && file.name == ".content.xml") {
                    contentXmlFound = true
                    return SKIP_CHILDREN
                }
                return CONTINUE
            }
        })
        return contentXmlFound
    }

    override fun isJcrContentRoot(path: String) = effectiveJcrRoots().contains(path)

    override fun isNotEmpty() = effectiveJcrRoots().isNotEmpty()

    override fun contains(file: VirtualFile) = effectiveJcrRoots().any { file.getProjectRelativePath(project).startsWith(it) }

    private fun effectiveJcrRoots() = detectedJcrContentRoots.minus(unmarkedJcrContentRoots).plus(markedJcrContentRoots)

}

val Project.jcrRoots: JcrRoots
    get() = ServiceManager.getService(this, JcrRoots::class.java)
            ?: error("Failed to get ${JcrRoots::class.java.name} for $this")
