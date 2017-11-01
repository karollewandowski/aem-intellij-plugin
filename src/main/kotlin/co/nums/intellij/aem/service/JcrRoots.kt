package co.nums.intellij.aem.service

import co.nums.intellij.aem.constants.JCR_ROOT_DIRECTORY_NAME
import co.nums.intellij.aem.extensions.getProjectRelativePath
import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.*
import com.intellij.openapi.vfs.VfsUtilCore.visitChildrenRecursively
import com.intellij.util.xmlb.XmlSerializerUtil
import java.util.*

@State(name = "JcrRoots")
class JcrRoots(private val project: Project) : PersistentStateComponent<JcrRoots.State> {

    private val myState = State()

    class State {
        var markedJcrContentRoots: MutableSet<String> = HashSet()
        var unmarkedJcrContentRoots: MutableSet<String> = HashSet()
    }

    private val jcrDetectableDirectories = setOf(
            "apps",
            "conf",
            "content",
            "etc",
            "home",
            "libs"
    )

    private val detectedJcrContentRoots = findPotentialRoots(project)
            .filter { it.name == JCR_ROOT_DIRECTORY_NAME || it.hasContentXmlFile() }
            .map { it.getProjectRelativePath(project) }
            .toHashSet()

    private fun findPotentialRoots(project: Project): MutableSet<VirtualFile> {
        val potentialRoots: MutableSet<VirtualFile> = HashSet()
        visitChildrenRecursively(project.baseDir, object : VirtualFileVisitor<VirtualFile>() {
            override fun visitFileEx(file: VirtualFile): Result {
                if (file.isDirectory) {
                    if (file.name == JCR_ROOT_DIRECTORY_NAME) {
                        potentialRoots.add(file)
                        return SKIP_CHILDREN
                    } else if (jcrDetectableDirectories.contains(file.name)) {
                        return addNonStandardJcrRoot(file)
                    }
                }
                return CONTINUE
            }

            private fun addNonStandardJcrRoot(file: VirtualFile): Result {
                if (file.isJcrRootDirectoryNamedAsContent()) {
                    potentialRoots.add(file)
                } else {
                    potentialRoots.add(file.parent)
                }
                return SKIP_CHILDREN
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

    fun isJcrContentRoot(file: VirtualFile) = effectiveJcrRoots().contains(file.getProjectRelativePath(project))

    fun isNotEmpty() = effectiveJcrRoots().isNotEmpty()

    fun contains(file: VirtualFile) = effectiveJcrRoots().any { file.getProjectRelativePath(project).startsWith(it) }

    private fun effectiveJcrRoots() = detectedJcrContentRoots
            .plus(myState.markedJcrContentRoots)
            .minus(myState.unmarkedJcrContentRoots)

    override fun getState() = myState

    override fun loadState(state: State) = XmlSerializerUtil.copyBean(state, myState)

    fun markAsJcrRoot(file: VirtualFile) {
        val newJcrRootPath = file.getProjectRelativePath(project)
        myState.markedJcrContentRoots.add(newJcrRootPath)
        myState.unmarkedJcrContentRoots.remove(newJcrRootPath)
    }

    fun unmarkAsJcrRoot(file: VirtualFile) {
        val newJcrRootPath = file.getProjectRelativePath(project)
        myState.unmarkedJcrContentRoots.add(newJcrRootPath)
        myState.markedJcrContentRoots.remove(newJcrRootPath)
    }

}

val Project.jcrRoots: JcrRoots
    get() = ServiceManager.getService(this, JcrRoots::class.java)
            ?: error("Failed to get ${JcrRoots::class.java.name} for $this")
