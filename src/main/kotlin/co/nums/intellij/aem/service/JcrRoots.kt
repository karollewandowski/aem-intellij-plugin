package co.nums.intellij.aem.service

import co.nums.intellij.aem.extensions.getProjectRelativePath
import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.xmlb.XmlSerializerUtil
import java.util.*

@State(name = "JcrRoots")
class JcrRoots : PersistentStateComponent<JcrRoots.State> {

    private val project: Project
    private val jcrRootsDetector: JcrRootsDetector

    private val myState = State()

    class State {
        var markedJcrContentRoots: MutableSet<String> = HashSet()
        var unmarkedJcrContentRoots: MutableSet<String> = HashSet()
    }

    private val detectedJcrContentRoots: Set<String>

    @SuppressWarnings("unused") // used implicitly by platform
    constructor(project: Project) : this(project, JcrRootsDetectorImpl())

    // only for tests
    internal constructor(project: Project, jcrRootsDetector: JcrRootsDetector) {
        this.project = project
        this.jcrRootsDetector = jcrRootsDetector
        this.detectedJcrContentRoots = jcrRootsDetector.detectJcrRoots(project.baseDir, project.basePath)
    }

    fun isJcrContentRoot(file: VirtualFile) = effectiveJcrRoots().contains(file.getProjectRelativePath(project))

    fun isEmpty() = effectiveJcrRoots().isEmpty()

    fun contains(file: VirtualFile) = effectiveJcrRoots().any { file.getProjectRelativePath(project).startsWith(it) }

    private fun effectiveJcrRoots() = detectedJcrContentRoots
            .plus(myState.markedJcrContentRoots)
            .minus(myState.unmarkedJcrContentRoots)

    override fun getState() = myState

    override fun loadState(state: State) = XmlSerializerUtil.copyBean(state, myState)

    fun markAsJcrRoot(dir: VirtualFile) {
        if (!dir.isDirectory) throw IllegalArgumentException("File ${dir.path} is not directory")
        if (this.contains(dir)) throw IllegalArgumentException("Directory ${dir.path} or its parent is already marked as JCR Root")
        dir.move(from = myState.unmarkedJcrContentRoots, to = myState.markedJcrContentRoots)
    }

    fun unmarkAsJcrRoot(dir: VirtualFile) {
        if (!this.isJcrContentRoot(dir)) throw IllegalArgumentException("${dir.path} is not JCR Root")
        dir.move(from = myState.markedJcrContentRoots, to = myState.unmarkedJcrContentRoots)
    }

    private fun VirtualFile.move(from: MutableSet<String>, to: MutableSet<String>) {
        val newJcrRootPath = this.getProjectRelativePath(project)
        to.add(newJcrRootPath)
        from.remove(newJcrRootPath)
    }

    fun getAll() = effectiveJcrRoots()

}

val Project.jcrRoots: JcrRoots
    get() = ServiceManager.getService(this, JcrRoots::class.java)
            ?: error("Failed to get ${JcrRoots::class.java.name} for $this")
