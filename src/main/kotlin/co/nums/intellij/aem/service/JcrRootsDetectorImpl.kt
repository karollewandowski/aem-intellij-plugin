package co.nums.intellij.aem.service

import co.nums.intellij.aem.constants.*
import com.intellij.openapi.vfs.*

interface JcrRootsDetector {
    fun detectJcrRoots(root: VirtualFile, basePath: String? = ""): Set<String>
}

class JcrRootsDetectorImpl : JcrRootsDetector {

    override fun detectJcrRoots(root: VirtualFile, basePath: String?) =
            findPotentialRoots(root)
                    .filter { it.name == JCR_ROOT_DIRECTORY_NAME || it.hasContentXmlFile() }
                    .map { it.path.removePrefix(basePath ?: "") }
                    .toHashSet()

    private fun findPotentialRoots(root: VirtualFile): MutableSet<VirtualFile> {
        val potentialRoots: MutableSet<VirtualFile> = HashSet()
        VfsUtilCore.visitChildrenRecursively(root, JcrRootsCollector(potentialRoots))
        return potentialRoots
    }

    private fun VirtualFile.hasContentXmlFile(): Boolean {
        var contentXmlFound = false
        VfsUtilCore.visitChildrenRecursively(this, object : VirtualFileVisitor<VirtualFile>() {
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

}

private class JcrRootsCollector(private val potentialRoots: MutableSet<VirtualFile>) : VirtualFileVisitor<VirtualFile>() {

    override fun visitFileEx(file: VirtualFile): Result {
        if (file.isDirectory) {
            if (file.name == JCR_ROOT_DIRECTORY_NAME) {
                potentialRoots.add(file)
                return SKIP_CHILDREN
            } else if (file.name in JCR_SPECIFIC_DIRECTORIES) {
                return addNonStandardJcrRoot(file)
            }
        }
        return CONTINUE
    }

    private fun addNonStandardJcrRoot(file: VirtualFile): Result {
        if (file.isJcrRootDirectoryNamedAsStandardDir()) {
            potentialRoots.add(file)
        } else {
            potentialRoots.add(file.parent)
        }
        return SKIP_CHILDREN
    }

    private fun VirtualFile.isJcrRootDirectoryNamedAsStandardDir() =
            name in JCR_SPECIFIC_DIRECTORIES && children.any { it.name in JCR_SPECIFIC_DIRECTORIES }

}
