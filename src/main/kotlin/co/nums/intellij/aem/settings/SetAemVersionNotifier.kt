package co.nums.intellij.aem.settings

import co.nums.intellij.aem.icons.AemIcons
import co.nums.intellij.aem.messages.AemPluginBundle
import co.nums.intellij.aem.service.jcrRoots
import com.intellij.ide.util.PropertiesComponent
import com.intellij.notification.*
import com.intellij.openapi.actionSystem.*
import com.intellij.openapi.application.ApplicationManager
import com.intellij.openapi.options.ShowSettingsUtil
import com.intellij.openapi.project.Project
import com.intellij.openapi.startup.StartupActivity

private const val VERSION_NOTIFICATION_DISABLED_PROPERTY_NAME = "aem-intellij-plugin.notifications.version.disabled"

class SetAemVersionNotifier : StartupActivity {

    private val aemPluginNotifications = NotificationGroup("AEM IntelliJ Plugin", NotificationDisplayType.STICKY_BALLOON, false)

    override fun runActivity(project: Project) {
        if (project.jcrRoots?.isEmpty() != true && notificationNotShownYet(project)) {
            showVersionNotification(project)
        }
    }

    private fun notificationNotShownYet(project: Project) =
            !PropertiesComponent.getInstance(project).getBoolean(VERSION_NOTIFICATION_DISABLED_PROPERTY_NAME)

    private fun showVersionNotification(project: Project) {
        val notification = aemPluginNotifications.createNotification(
                AemPluginBundle.message("plugin.name"),
                AemPluginBundle.message("notification.aem.version.text", project.aemSettings?.aemVersion),
                NotificationType.INFORMATION,
                null)
        notification
                .setIcon(AemIcons.AEM_LOGO)
                .setImportant(true)
                .addAction(OpenSettings(project, notification))
                .addAction(SuppressNotification(project, notification))
                .notify(project)
    }

}

private class OpenSettings(private val project: Project, private val notification: Notification) : AnAction(AemPluginBundle.message("notification.aem.version.action.change")) {

    override fun actionPerformed(event: AnActionEvent?) {
        project.disableVersionNotification()
        notification.expire()
        ApplicationManager.getApplication().invokeLater {
            ShowSettingsUtil.getInstance().showSettingsDialog(project, AemPluginBundle.message("plugin.name"))
        }
    }

}

private class SuppressNotification(private val project: Project, private val notification: Notification) : AnAction(AemPluginBundle.message("notification.action.do.not.show.again")) {

    override fun actionPerformed(event: AnActionEvent?) {
        project.disableVersionNotification()
        notification.expire()
    }

}

private fun Project.disableVersionNotification()
        = PropertiesComponent.getInstance(this).setValue(VERSION_NOTIFICATION_DISABLED_PROPERTY_NAME, true)
