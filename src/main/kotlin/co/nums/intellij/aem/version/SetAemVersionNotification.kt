package co.nums.intellij.aem.version

import co.nums.intellij.aem.settings.*
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity
import javax.swing.SwingUtilities

private const val VERSION_NOTIFICATION_DISABLED_PROPERTY_NAME = "aem-intellij-plugin.notifications.version.disabled1"

class SetAemVersionNotification : StartupActivity {

    private val aemPluginNotifications = NotificationGroup("AEM IntelliJ Plugin", NotificationDisplayType.STICKY_BALLOON, false)

    override fun runActivity(project: Project) {
        if (notificationShouldBeShown(project)) {
            showVersionNotification(project)
        }
    }

    private fun notificationShouldBeShown(project: Project) =
            !PropertiesComponent.getInstance(project).getBoolean(VERSION_NOTIFICATION_DISABLED_PROPERTY_NAME)

    private fun showVersionNotification(project: Project) {
        val notification = aemPluginNotifications.createNotification(
                "AEM IntelliJ Plugin",
                "AEM version is set to <b>${project.aemSettings.aemVersion}</b>. " +
                        "Set actually used version to make plugin fully compatible with your AEM.",
                NotificationType.INFORMATION,
                null)
        notification
                .setImportant(true)
                .addAction(OpenSettings(project, notification))
                .addAction(SuppressNotification(project, notification))
                .notify(project)
    }

}

private class OpenSettings(private val project: Project, private val notification: Notification) : AnAction("Change version") {

    override fun actionPerformed(event: AnActionEvent?) {
        project.disableVersionNotification()
        notification.expire()
        SwingUtilities.invokeLater {
            ShowSettingsUtil.getInstance().showSettingsDialog(project, AemProjectConfigurable.DISPLAY_NAME)
        }
    }

}

private class SuppressNotification(private val project: Project, private val notification: Notification) : AnAction("Don't show again") {

    override fun actionPerformed(event: AnActionEvent?) {
        project.disableVersionNotification()
        notification.expire()
    }

}

private fun Project.disableVersionNotification()
        = PropertiesComponent.getInstance(this).setValue(VERSION_NOTIFICATION_DISABLED_PROPERTY_NAME, true)
