package co.nums.intellij.aem.settings;

import co.nums.intellij.aem.version.AemVersion;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class AemProjectConfigurable implements Configurable {

    public static final String DISPLAY_NAME = "AEM IntelliJ Plugin";

    private JPanel mainPanel;
    private JComboBox<String> aemVersion;
    private JCheckBox enableManualVersionsCheckBox;
    private JLabel htlVersionLabel;
    private JComboBox<String> htlVersion;

    private final AemSettings aemSettings;

    public AemProjectConfigurable(Project project) {
        this.aemSettings = AemSettingsKt.getAemSettings(project);
    }

    @Override
    public JComponent createComponent() {
        initVersionsComboBox(aemVersion, AemVersion::getAem, aemSettings.getAemVersion());
        aemVersion.addItemListener(event -> {
            if (!enableManualVersionsCheckBox.isSelected()) {
                setHtlByAemVersion();
            }
        });
        initVersionsComboBox(htlVersion, AemVersion::getHtl, aemSettings.getHtlVersion());
        initManualVersionsCheckBox();
        return mainPanel;
    }

    private void initVersionsComboBox(JComboBox<String> comboBox, Function<AemVersion, String> versionExtractor,
            String selectedVersion) {
        comboBox.setModel(new DefaultComboBoxModel<>(getVersions(versionExtractor)));
        comboBox.setSelectedItem(selectedVersion);
    }

    private String[] getVersions(Function<AemVersion, String> versionExtractor) {
        return Arrays.stream(AemVersion.Companion.getALL()).map(versionExtractor).toArray(String[]::new);
    }

    private void initManualVersionsCheckBox() {
        enableManualVersionsCheckBox.setSelected(aemSettings.getManualVersionsEnabled());
        htlVersionLabel.setEnabled(aemSettings.getManualVersionsEnabled());
        htlVersion.setEnabled(aemSettings.getManualVersionsEnabled());
        enableManualVersionsCheckBox.addItemListener(event -> {
            boolean enabled = enableManualVersionsCheckBox.isSelected();
            htlVersionLabel.setEnabled(enabled);
            htlVersion.setEnabled(enabled);
            if (!enabled) {
                setHtlByAemVersion();
            }
        });
    }

    private void setHtlByAemVersion() {
        String selectedAemVersion = (String) aemVersion.getSelectedItem();
        String htlVersionString = findHtlByAemVersion(selectedAemVersion);
        htlVersion.setSelectedItem(htlVersionString);
    }

    private static String findHtlByAemVersion(String selectedAemVersion) {
        return Arrays.stream(AemVersion.Companion.getALL())
                .filter(version -> version.getAem().equals(selectedAemVersion))
                .map(AemVersion::getHtl)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Could not find HTL version for AEM: " + selectedAemVersion));
    }

    @Override
    public boolean isModified() {
        return aemSettings.getManualVersionsEnabled() != enableManualVersionsCheckBox.isSelected()
                || !aemSettings.getAemVersion().equals(aemVersion.getSelectedItem())
                || !aemSettings.getHtlVersion().equals(htlVersion.getSelectedItem());
    }

    @Override
    public void apply() throws ConfigurationException {
        aemSettings.setManualVersionsEnabled(enableManualVersionsCheckBox.isSelected());
        aemSettings.setAemVersion(getVersion(aemVersion, "AEM version is null"));
        aemSettings.setHtlVersion(getVersion(htlVersion, "HTL version is null"));
    }

    private static String getVersion(JComboBox<String> version, String exceptionMessage) throws ConfigurationException {
        return Optional.ofNullable((String) version.getSelectedItem())
                .orElseThrow(() -> new ConfigurationException(exceptionMessage));
    }

    @Override
    public void reset() {
        enableManualVersionsCheckBox.setSelected(aemSettings.getManualVersionsEnabled());
        aemVersion.setSelectedItem(aemSettings.getAemVersion());
        htlVersion.setSelectedItem(aemSettings.getHtlVersion());
    }

    @Override
    public void disposeUIResources() {
        // nothing to dispose
    }

    @Override
    public String getDisplayName() {
        return DISPLAY_NAME;
    }

    @Override
    public String getHelpTopic() {
        return null;
    }

}
