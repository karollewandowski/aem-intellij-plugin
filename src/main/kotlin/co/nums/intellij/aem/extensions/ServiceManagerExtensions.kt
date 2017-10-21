package co.nums.intellij.aem.extensions

import com.intellij.openapi.components.ServiceManager

inline fun <reified T : Any> getRequiredService(): T =
        ServiceManager.getService(T::class.java) ?: throw IllegalStateException("Could not get ${T::class.java.name}")

