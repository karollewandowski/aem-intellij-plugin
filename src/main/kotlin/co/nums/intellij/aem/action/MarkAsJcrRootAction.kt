package co.nums.intellij.aem.action

import co.nums.intellij.aem.extensions.*
import co.nums.intellij.aem.htl.index.HtlTemplatesIndex
import co.nums.intellij.aem.icons.AemIcons
import co.nums.intellij.aem.service.*
import com.intellij.openapi.actionSystem.AnActionEvent
import com.intellij.openapi.actionSystem.CommonDataKeys.VIRTUAL_FILE_ARRAY
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.intellij.util.FileContentUtil
import java.util.*

class MarkAsJcrRootAction : DumbAwareAction() {

    override fun update(event: AnActionEvent) {
        val dirs = event.getData(VIRTUAL_FILE_ARRAY)
        val project = event.project
        if (dirs?.isNotEmpty() == true && project != null && dirs.all { it.isDirectory }) {
        val jcrRoots = project.jcrRoots ?: return
            when {
                dirs.allCanBeMarkedAsJcrRoots(jcrRoots) -> event.presentation.icon = AemIcons.JCR_ROOT_DIR
                dirs.allCanBeUnmarkedAsJcrRoots(jcrRoots) -> event.presentation.text = "Unmark as JCR Root"
                else -> event.presentation.isEnabledAndVisible = false
            }
        } else {
            event.presentation.isEnabledAndVisible = false
        }
    }

    private fun Array<out VirtualFile>.allCanBeMarkedAsJcrRoots(jcrRoots: JcrRoots) = this.all { !jcrRoots.contains(it) }

    private fun Array<out VirtualFile>.allCanBeUnmarkedAsJcrRoots(jcrRoots: JcrRoots) = this.all { jcrRoots.isJcrContentRoot(it) }

    override fun actionPerformed(event: AnActionEvent) {
        if (!event.presentation.isEnabledAndVisible) return
        val project = event.project ?: return
        val dirs = event.getData(VIRTUAL_FILE_ARRAY) ?: return
        if (dirs.isEmpty() || !dirs.all { it.isDirectory }) return
        val jcrRoots = project.jcrRoots ?: return
        when {
            dirs.allCanBeMarkedAsJcrRoots(jcrRoots) -> doAction(project, dirs, jcrRoots::markAsJcrRoot)
            dirs.allCanBeUnmarkedAsJcrRoots(jcrRoots) -> doAction(project, dirs, jcrRoots::unmarkAsJcrRoot)
        }
    }

    private fun doAction(project: Project, dirs: Array<out VirtualFile>, action: (file: VirtualFile) -> Unit) {
        val filesToReparse: MutableList<VirtualFile> = LinkedList()
        dirs.forEach {
            action(it)
            it.refresh(true, true)
            it.collectHtmlFilesToReparse(filesToReparse)
        }
        refresh(project, filesToReparse)
    }

    private fun VirtualFile.collectHtmlFilesToReparse(filesToReparse: MutableList<VirtualFile>) {
        VfsUtilCore.visitChildrenRecursively(this, object : VirtualFileVisitor<VirtualFile>() {
            override fun visitFileEx(file: VirtualFile): Result {
                if (file.extension?.toLowerCase() == "html") {
                    filesToReparse.add(file)
                }
                return CONTINUE
            }
        })
    }

    private fun refresh(project: Project, filesToReparse: MutableList<VirtualFile>) {
        project.projectView?.apply {
            refresh()
            currentProjectViewPane.updateFromRoot(false)
        }
        project.psiManager.apply {
            FileContentUtil.reparseFiles(filesToReparse)
        }
        HtlTemplatesIndex.rebuild()
    }

}
