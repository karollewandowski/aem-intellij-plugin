package co.nums.intellij.aem.test.util

import com.intellij.openapi.project.Project
import org.mockito.Mockito
import org.mockito.stubbing.OngoingStubbing

inline fun <reified T> whenGetService(project: Project): OngoingStubbing<T> {
    if (Mockito.mockingDetails(project).mockCreationSettings.defaultAnswer != Mockito.RETURNS_DEEP_STUBS) {
        throw IllegalArgumentException("Project mock must return deep stubs by default")
    }
    return Mockito.`when`(project.picoContainer.getComponentInstance(T::class.java.name) as? T)
}
