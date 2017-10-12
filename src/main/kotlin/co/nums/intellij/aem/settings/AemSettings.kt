package co.nums.intellij.aem.settings

import co.nums.intellij.aem.version.AemVersion
import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "AemSettings")
class AemSettings : PersistentStateComponent<AemSettings> {

    var aemVersion: String = AemVersion.ALL.last().aem
    var htlVersion: String = AemVersion.ALL.last().htl
    var manualVersionsEnabled: Boolean = false

    override fun getState() = this

    override fun loadState(state: AemSettings) = XmlSerializerUtil.copyBean(state, this)

}

val Project.aemSettings: AemSettings
    get() = ServiceManager.getService(this, AemSettings::class.java)
            ?: error("Failed to get ${AemSettings::class.java.name} for $this")
