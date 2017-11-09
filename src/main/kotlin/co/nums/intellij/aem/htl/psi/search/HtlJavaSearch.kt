package co.nums.intellij.aem.htl.psi.search

import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.psi.search.searches.*

object HtlJavaSearch {

    private val USE_INTERFACES = arrayOf("io.sightly.java.api.Use", "org.apache.sling.scripting.sightly.pojo.Use")
    private val SLING_MODEL_ANNOTATION = "org.apache.sling.models.annotations.Model"

    fun useApiClasses(project: Project) = useApiImplementers(project) + slingModels(project)

    private fun useApiImplementers(project: Project): Collection<PsiClass> =
            USE_INTERFACES
                    .mapNotNull { it.toPsiClass(project) }
                    .flatMap { project.findImplementers(it) }
                    .filterNot { it.hasModifierProperty(PsiModifier.ABSTRACT) }

    private fun slingModels(project: Project): Collection<PsiClass> =
            SLING_MODEL_ANNOTATION
                    .toPsiClass(project)
                    ?.let { project.findAnnotatedClasses(it) }
                    .orEmpty()

    private fun String.toPsiClass(project: Project) =
            JavaPsiFacade.getInstance(project).findClass(this, GlobalSearchScope.allScope(project))

    private fun Project.findImplementers(implementedInterface: PsiClass): Collection<PsiClass> =
            ClassInheritorsSearch.search(implementedInterface, GlobalSearchScope.allScope(this), true).findAll()

    private fun Project.findAnnotatedClasses(annotation: PsiClass) =
            AnnotatedElementsSearch.searchPsiClasses(annotation, GlobalSearchScope.allScope(this)).findAll()

}
