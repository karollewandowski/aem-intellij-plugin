package co.nums.intellij.aem.settings

import co.nums.intellij.aem.extensions.getService
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
    get() = getService(this)
