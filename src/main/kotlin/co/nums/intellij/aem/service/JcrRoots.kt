package co.nums.intellij.aem.service

import co.nums.intellij.aem.extensions.getProjectRelativePath
import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.util.xmlb.XmlSerializerUtil
import java.util.*

@State(name = "JcrRoots")
class JcrRoots(private val project: Project) : PersistentStateComponent<JcrRoots.State> {

    private val myState = State()

    class State {
        var markedJcrContentRoots: MutableSet<String> = HashSet()
        var unmarkedJcrContentRoots: MutableSet<String> = HashSet()
    }

    private val detectedJcrContentRoots = JcrRootsDetector.detectJcrRoots(project.baseDir, project.basePath)

    fun isJcrContentRoot(file: VirtualFile) = effectiveJcrRoots().contains(file.getProjectRelativePath(project))

    fun isNotEmpty() = effectiveJcrRoots().isNotEmpty()

    fun contains(file: VirtualFile) = effectiveJcrRoots().any { file.getProjectRelativePath(project).startsWith(it) }

    private fun effectiveJcrRoots() = detectedJcrContentRoots
            .plus(myState.markedJcrContentRoots)
            .minus(myState.unmarkedJcrContentRoots)

    override fun getState() = myState

    override fun loadState(state: State) = XmlSerializerUtil.copyBean(state, myState)

    fun markAsJcrRoot(file: VirtualFile) = file.move(from = myState.unmarkedJcrContentRoots, to = myState.markedJcrContentRoots)

    fun unmarkAsJcrRoot(file: VirtualFile) = file.move(from = myState.markedJcrContentRoots, to = myState.unmarkedJcrContentRoots)

    private fun VirtualFile.move(from: MutableSet<String>, to: MutableSet<String>) {
        val newJcrRootPath = this.getProjectRelativePath(project)
        to.add(newJcrRootPath)
        from.remove(newJcrRootPath)
    }

}

val Project.jcrRoots: JcrRoots
    get() = ServiceManager.getService(this, JcrRoots::class.java)
            ?: error("Failed to get ${JcrRoots::class.java.name} for $this")
