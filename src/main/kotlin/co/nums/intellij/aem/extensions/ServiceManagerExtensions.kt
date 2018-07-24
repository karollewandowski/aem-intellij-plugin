package co.nums.intellij.aem.extensions

import com.intellij.openapi.components.ServiceManager
import com.intellij.openapi.project.Project

inline fun <reified T : Any> getRequiredService(): T =
        ServiceManager.getService(T::class.java) ?: throw IllegalStateException("Could not get ${T::class.java.name}")

inline fun <reified T : Any> getService(project: Project): T? {
    if (project.isDisposed) return null
    return ServiceManager.getService(project, T::class.java)
            ?: error("Failed to get ${T::class.java.name} for $project")
}
