package co.nums.intellij.aem.action

import co.nums.intellij.aem.extensions.*
import co.nums.intellij.aem.icons.AemIcons
import co.nums.intellij.aem.service.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.project.*
import com.intellij.openapi.vfs.*
import com.intellij.util.FileContentUtil
import java.util.*

class MarkAsJcrRootAction : DumbAwareAction() {

    override fun update(event: AnActionEvent) {
        val dirs = event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY)
        val project = event.project
        if (dirs != null && project != null && dirs.all { it.isDirectory }) {
            val jcrRoots = project.jcrRoots
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
        val dirs = event.getData(CommonDataKeys.VIRTUAL_FILE_ARRAY) ?: return
        if (dirs.all { it.isDirectory }) {
            var actionPerformed = false
            val project = event.project ?: return
            val jcrRoots = project.jcrRoots
            val filesToReparse: MutableList<VirtualFile> = LinkedList()
            when {
                dirs.allCanBeMarkedAsJcrRoots(jcrRoots) -> dirs.forEach {
                    jcrRoots.markAsJcrRoot(it)
                    it.refresh(true, true)
                    it.collectHtmlFilesToReparse(filesToReparse)
                    actionPerformed = true
                }
                dirs.allCanBeUnmarkedAsJcrRoots(jcrRoots) -> dirs.forEach {
                    jcrRoots.unmarkAsJcrRoot(it)
                    it.refresh(true, true)
                    it.collectHtmlFilesToReparse(filesToReparse)
                    actionPerformed = true
                }
            }
            if (actionPerformed) {
                refresh(project, filesToReparse)
            }
        }
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
    }

}
