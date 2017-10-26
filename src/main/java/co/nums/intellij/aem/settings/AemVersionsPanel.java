package co.nums.intellij.aem.settings;

import com.intellij.openapi.options.ConfigurationException;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import java.util.Arrays;
import java.util.Optional;
import java.util.function.Function;

public class AemVersionsPanel {

    private JPanel mainPanel;
    private JPanel versionsPanel;
    private JLabel aemVersionLabel;
    private JComboBox<String> aemVersion;
    private JLabel htlVersionLabel;
    private JCheckBox enableManualVersionsCheckBox;
    private JComboBox<String> htlVersion;

    public void initComponent(AemSettings aemSettings) {
        initVersionsComboBox(aemVersion, AemVersion::getAem, aemSettings.getAemVersion());
        aemVersion.addItemListener(event -> {
            if (!enableManualVersionsCheckBox.isSelected()) {
                setHtlByAemVersion();
            }
        });
        initVersionsComboBox(htlVersion, AemVersion::getHtl, aemSettings.getHtlVersion());
        initManualVersionsCheckBox(aemSettings);
    }

    public void updateAemSupportDependentFields(boolean aemSupportEnabled) {
        versionsPanel.setEnabled(aemSupportEnabled);
        aemVersionLabel.setEnabled(aemSupportEnabled);
        aemVersion.setEnabled(aemSupportEnabled);
        enableManualVersionsCheckBox.setEnabled(aemSupportEnabled);
        boolean manualVersionsCheckBoxEnabled = enableManualVersionsCheckBox.isSelected();
        htlVersionLabel.setEnabled(aemSupportEnabled && manualVersionsCheckBoxEnabled);
        htlVersion.setEnabled(aemSupportEnabled && manualVersionsCheckBoxEnabled);
    }

    private void initVersionsComboBox(JComboBox<String> comboBox, Function<AemVersion, String> versionExtractor,
            String selectedVersion) {
        comboBox.setModel(new DefaultComboBoxModel<>(getVersions(versionExtractor)));
        comboBox.setSelectedItem(selectedVersion);
    }

    private String[] getVersions(Function<AemVersion, String> versionExtractor) {
        return Arrays.stream(AemVersion.values()).map(versionExtractor).toArray(String[]::new);
    }

    private void initManualVersionsCheckBox(AemSettings aemSettings) {
        boolean manualVersionsEnabled = aemSettings.getManualVersionsEnabled();
        enableManualVersionsCheckBox.setSelected(manualVersionsEnabled);
        htlVersionLabel.setEnabled(manualVersionsEnabled);
        htlVersion.setEnabled(manualVersionsEnabled);
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
        return Arrays.stream(AemVersion.values())
                .filter(version -> version.getAem().equals(selectedAemVersion))
                .map(AemVersion::getHtl)
                .findFirst()
                .orElseThrow(() -> new IllegalStateException(
                        "Could not find HTL version for AEM: " + selectedAemVersion));
    }

    public boolean isModified(AemSettings aemSettings) {
        return aemSettings.getManualVersionsEnabled() != enableManualVersionsCheckBox.isSelected()
                || !aemSettings.getAemVersion().equals(aemVersion.getSelectedItem())
                || !aemSettings.getHtlVersion().equals(htlVersion.getSelectedItem());
    }

    public void apply(AemSettings aemSettings) throws ConfigurationException {
        aemSettings.setManualVersionsEnabled(enableManualVersionsCheckBox.isSelected());
        aemSettings.setAemVersion(getVersion(aemVersion, "AEM version is null"));
        aemSettings.setHtlVersion(getVersion(htlVersion, "HTL version is null"));
    }

    private static String getVersion(JComboBox<String> version, String exceptionMessage) throws ConfigurationException {
        return Optional.ofNullable((String) version.getSelectedItem())
                .orElseThrow(() -> new ConfigurationException(exceptionMessage));
    }

    public void reset(AemSettings aemSettings) {
        enableManualVersionsCheckBox.setSelected(aemSettings.getManualVersionsEnabled());
        aemVersion.setSelectedItem(aemSettings.getAemVersion());
        htlVersion.setSelectedItem(aemSettings.getHtlVersion());
    }

    public boolean fieldsRequiringRestartChanged(AemSettings aemSettings) {
        return !aemSettings.getAemVersion().equals(aemVersion.getSelectedItem())
                || !aemSettings.getHtlVersion().equals(htlVersion.getSelectedItem());
    }

}
