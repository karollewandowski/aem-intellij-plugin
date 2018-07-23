package co.nums.intellij.aem.settings

import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project
import com.intellij.util.xmlb.XmlSerializerUtil

@State(name = "AemSettings")
class AemSettings : PersistentStateComponent<AemSettings> {

    var aemSupportEnabled: Boolean = true
    var aemVersion: String = AemVersion.values().last().aem
    var htlVersion: String = AemVersion.values().last().htl
    var manualVersionsEnabled: Boolean = false

    override fun getState() = this

    override fun loadState(state: AemSettings) = XmlSerializerUtil.copyBean(state, this)

}

val Project.aemSettings: AemSettings?
    get() {
        if (this.isDisposed) return null
        return ServiceManager.getService(this, AemSettings::class.java)
                ?: error("Failed to get ${AemSettings::class.java.name} for $this")
    }
