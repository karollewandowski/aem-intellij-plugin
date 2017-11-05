package co.nums.intellij.aem.settings;

import co.nums.intellij.aem.messages.AemPluginBundle;
import co.nums.intellij.aem.service.ApplicationRestarter;
import com.intellij.openapi.options.Configurable;
import com.intellij.openapi.options.ConfigurationException;
import com.intellij.openapi.project.Project;

import javax.swing.JCheckBox;
import javax.swing.JComponent;
import javax.swing.JPanel;

public class AemProjectConfigurable implements Configurable {

    private JPanel mainPanel;
    private JCheckBox enableAemSupportCheckBox;
    private AemVersionsPanel aemVersionsPanel;

    private final AemSettings aemSettings;

    public AemProjectConfigurable(Project project) {
        aemSettings = AemSettingsKt.getAemSettings(project);
    }

    @Override
    public JComponent createComponent() {
        boolean aemSupportEnabled = aemSettings.getAemSupportEnabled();
        enableAemSupportCheckBox.setSelected(aemSupportEnabled);
        aemVersionsPanel.initComponent(aemSettings);
        aemVersionsPanel.updateAemSupportDependentFields(aemSupportEnabled);
        enableAemSupportCheckBox.addItemListener(
                event -> aemVersionsPanel.updateAemSupportDependentFields(enableAemSupportCheckBox.isSelected()));
        return mainPanel;
    }

    @Override
    public boolean isModified() {
        return aemSettings.getAemSupportEnabled() != enableAemSupportCheckBox.isSelected()
                || aemVersionsPanel.isModified(aemSettings);
    }

    @Override
    public void apply() throws ConfigurationException {
        boolean restartRequired = isRestartRequired();
        aemSettings.setAemSupportEnabled(enableAemSupportCheckBox.isSelected());
        aemVersionsPanel.apply(aemSettings);
        if (restartRequired) {
            ApplicationRestarter.INSTANCE.askForApplicationRestart();
        }
    }

    private boolean isRestartRequired() {
        return aemSettings.getAemSupportEnabled() != enableAemSupportCheckBox.isSelected()
                || aemVersionsPanel.fieldsRequiringRestartChanged(aemSettings);
    }

    @Override
    public void reset() {
        enableAemSupportCheckBox.setSelected(aemSettings.getAemSupportEnabled());
        aemVersionsPanel.reset(aemSettings);
    }

    @Override
    public String getDisplayName() {
        return AemPluginBundle.message("plugin.name");
    }

    @Override
    public String getHelpTopic() {
        return null;
    }

    @Override
    public void disposeUIResources() {
        // nothing to dispose
    }

}
