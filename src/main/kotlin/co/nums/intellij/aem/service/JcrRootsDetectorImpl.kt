package co.nums.intellij.aem.service

import co.nums.intellij.aem.constants.*
import co.nums.intellij.aem.extensions.containsFile
import com.intellij.openapi.vfs.*
import java.util.*

interface JcrRootsDetector {
    fun detectJcrRoots(root: VirtualFile, basePath: String? = ""): Set<String>
}

/**
 * List is limited to directories that are likely to be
 * used by developers to store files, excluding content
 * (it's special case as source dirs are sometimes named
 * as content.
 */
private val JCR_SPECIFIC_DIRECTORIES = hashSetOf(
        "apps",
        "conf",
        "etc",
        "home",
        "libs")

class JcrRootsDetectorImpl : JcrRootsDetector {

    override fun detectJcrRoots(root: VirtualFile, basePath: String?) =
            findPotentialRoots(root)
                    .filter { it.name == JCR_ROOT_DIRECTORY_NAME || it.containsFile { file -> file.name == ".content.xml" } }
                    .map { it.path.removePrefix(basePath ?: "") }
                    .toHashSet()

    private fun findPotentialRoots(root: VirtualFile): Iterable<VirtualFile> {
        val potentialRoots = HashSet<VirtualFile>()
        VfsUtilCore.visitChildrenRecursively(root, JcrRootsCollector(potentialRoots))
        return potentialRoots.asIterable()
    }

}

private class JcrRootsCollector(private val potentialRoots: MutableSet<VirtualFile>) : VirtualFileVisitor<VirtualFile>() {

    override fun visitFileEx(file: VirtualFile): Result {
        if (file.isDirectory) {
            when {
                file.name == JCR_ROOT_DIRECTORY_NAME || file.isJcrRootNamedAsContent() -> {
                    potentialRoots.add(file)
                    return SKIP_CHILDREN
                }
                file.name in JCR_SPECIFIC_DIRECTORIES -> {
                    potentialRoots.add(file.parent)
                    return SKIP_CHILDREN
                }
            }
        }
        return CONTINUE
    }

    // special case for JCR roots named as "content"
    private fun VirtualFile.isJcrRootNamedAsContent() = this.name == CONTENT_DIR_NAME && this.containsJcrSpecificDirs()

    private fun VirtualFile.containsJcrSpecificDirs() = children.any { it.name == CONTENT_DIR_NAME || it.name in JCR_SPECIFIC_DIRECTORIES }

}
