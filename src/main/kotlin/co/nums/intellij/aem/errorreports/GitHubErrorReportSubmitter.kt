package co.nums.intellij.aem.errorreports

import co.nums.intellij.aem.extensions.getRequiredService
import co.nums.intellij.aem.service.PluginInfoProvider
import com.intellij.diagnostic.ReportMessages
import com.intellij.ide.DataManager
import com.intellij.idea.IdeaLogger
import com.intellij.notification.*
import com.intellij.openapi.actionSystem.CommonDataKeys
import com.intellij.openapi.application.*
import com.intellij.openapi.application.ex.ApplicationInfoEx
import com.intellij.openapi.diagnostic.*
import com.intellij.openapi.diagnostic.SubmittedReportInfo.SubmissionStatus
import com.intellij.openapi.progress.*
import com.intellij.openapi.project.Project
import com.intellij.openapi.util.SystemInfo.JAVA_VERSION
import com.intellij.openapi.util.SystemInfo.OS_NAME
import com.intellij.util.*
import com.intellij.util.SystemProperties.getJavaVmVendor
import java.awt.Component

class GitHubErrorReportSubmitter : ErrorReportSubmitter() {

    override fun getReportActionText() = ErrorReportingBundle.message("error.report.to.plugin.vendor")

    override fun submit(events: Array<IdeaLoggingEvent>, additionalInfo: String?, parentComponent: Component, consumer: Consumer<SubmittedReportInfo>): Boolean {
        val dataContext = DataManager.getInstance().getDataContext(parentComponent)
        val project = CommonDataKeys.PROJECT.getData(dataContext)
        val task = SubmitFeedbackTask(
                project,
                ErrorReportingBundle.message("error.report.progress.dialog.text"),
                createErrorBean(events[0], additionalInfo),
                CallbackWithNotification(consumer, project)
        )
        if (project == null) {
            task.run(EmptyProgressIndicator())
        } else {
            ProgressManager.getInstance().run(task)
        }
        return true
    }

    private fun createErrorBean(lastEvent: IdeaLoggingEvent, additionalInfo: String?): GitHubErrorBean {
        val errorMessage = lastEvent.throwable.message
        val stackTrace = if (lastEvent.throwable != null) ExceptionUtil.getThrowableText(lastEvent.throwable) else null
        return GitHubErrorBean(
                errorMessage,
                stackTrace,
                additionalInfo,
                IdeaLogger.ourLastActionId,
                getRequiredService<PluginInfoProvider>().getPluginVersion(),
                SystemInfo(OS_NAME, JAVA_VERSION, getJavaVmVendor()),
                ApplicationNamesInfo.getInstance(),
                ApplicationInfo.getInstance() as ApplicationInfoEx
        )
    }

    internal class CallbackWithNotification(
            private val originalConsumer: Consumer<SubmittedReportInfo>,
            private val project: Project?)
        : Consumer<SubmittedReportInfo> {

        override fun consume(reportInfo: SubmittedReportInfo) {
            originalConsumer.consume(reportInfo)
            ReportMessages.GROUP.createNotification(
                    ReportMessages.ERROR_REPORT,
                    reportInfo.linkText,
                    if (reportInfo.isNotFail()) NotificationType.INFORMATION else NotificationType.ERROR,
                    if (reportInfo.isNotFail()) NotificationListener.URL_OPENING_LISTENER else null)
                    .notify(project)
        }

        private fun SubmittedReportInfo.isNotFail() = status != SubmissionStatus.FAILED

    }

}

data class SystemInfo(
        val osName: String,
        val javaVersion: String,
        val javaVmVendor: String
)
