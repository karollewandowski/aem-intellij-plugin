package co.nums.intellij.aem.settings

import co.nums.intellij.aem.version.AemVersion
import com.intellij.openapi.components.*
import com.intellij.openapi.project.Project

@State(name = "AemSettings")
class AemSettings : PersistentStateComponent<AemSettings.State> {

    data class State(
            var aemVersion: String = AemVersion.ALL.last().aem,
            var htlVersion: String = AemVersion.ALL.last().htl,
            var manualVersionsEnabled: Boolean = false
    )

    private var myState = State()

    var aemVersion: String
        get() = myState.aemVersion
        set(value) {
            myState.aemVersion = value
        }

    var htlVersion: String
        get() = myState.htlVersion
        set(value) {
            myState.htlVersion = value
        }

    var manualVersionsEnabled: Boolean
        get() = myState.manualVersionsEnabled
        set(value) {
            myState.manualVersionsEnabled = value
        }

    override fun getState() = myState

    override fun loadState(state: State) {
        myState.aemVersion = state.aemVersion
        myState.htlVersion = state.htlVersion
    }

}

val Project.aemSettings: AemSettings
    get() = ServiceManager.getService(this, AemSettings::class.java)
            ?: error("Failed to get ${AemSettings::class.java.name} for $this")
