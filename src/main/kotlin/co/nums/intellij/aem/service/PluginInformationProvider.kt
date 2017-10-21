package co.nums.intellij.aem.service

import com.intellij.ide.plugins.PluginManager
import com.intellij.openapi.extensions.PluginId

const val AEM_INTELLIJ_PLUGIN_ID = "co.nums.intellij.aem"

interface PluginInfoProvider {

    fun getPluginVersion(): String?

    /**
     * Checks whether running plugin version is older than given `version`.
     */
    fun runningVersionIsOlderThan(version: String): Boolean

}


class PluginInfoProviderImpl : PluginInfoProvider {

    override fun getPluginVersion() = PluginManager.getPlugin(PluginId.getId(AEM_INTELLIJ_PLUGIN_ID))?.version

    override fun runningVersionIsOlderThan(version: String) = (getPluginVersion() ?: "0.0.0") < version

}
