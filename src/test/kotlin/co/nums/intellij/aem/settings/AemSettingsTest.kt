package co.nums.intellij.aem.settings

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.*

class AemSettingsTest {

    private lateinit var cut: AemSettings

    @BeforeEach
    fun setUp() {
        cut = AemSettings()
    }

    @Test
    fun aemSupportShouldBeEnabledByDefault() {
        assertThat(cut.aemSupportEnabled).isTrue()
    }

    @Test
    fun latestAemVersionShouldBeSetByDefault() {
        assertThat(cut.aemVersion).isEqualTo(AemVersion.values().last().aem)
    }

    @Test
    fun latestHtlVersionShouldBeSetByDefault() {
        assertThat(cut.htlVersion).isEqualTo(AemVersion.values().last().htl)
    }

    @Test
    fun settingVersionsManuallyShouldBeDisabledByDefault() {
        assertThat(cut.manualVersionsEnabled).isFalse()
    }

    @Test
    fun shouldCopyStateFromGivenStateWhenLoading() {
        val stateToLoad = AemSettings()
        stateToLoad.aemSupportEnabled = false
        stateToLoad.aemVersion = "test-aem-version"
        stateToLoad.htlVersion = "test-htl-version"
        stateToLoad.manualVersionsEnabled = true

        cut.loadState(stateToLoad)

        assertThat(cut.aemSupportEnabled).isFalse()
        assertThat(cut.aemVersion).isEqualTo("test-aem-version")
        assertThat(cut.htlVersion).isEqualTo("test-htl-version")
        assertThat(cut.manualVersionsEnabled).isTrue()
    }

}
