package co.nums.intellij.aem.extensions

import com.intellij.ide.projectView.ProjectView
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiManager

val Project.projectView: ProjectView?
    get() = ProjectView.getInstance(this)

val Project.psiManager: PsiManager
    get() = PsiManager.getInstance(this)
