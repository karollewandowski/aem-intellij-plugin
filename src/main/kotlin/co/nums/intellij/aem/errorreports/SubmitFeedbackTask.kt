package co.nums.intellij.aem.errorreports

import co.nums.intellij.aem.extensions.getRequiredService
import co.nums.intellij.aem.service.PluginInfoProvider
import com.intellij.openapi.diagnostic.SubmittedReportInfo
import com.intellij.openapi.progress.ProgressIndicator
import com.intellij.openapi.progress.Task.Backgroundable
import com.intellij.openapi.project.Project
import com.intellij.util.Consumer

class SubmitFeedbackTask internal constructor(project: Project?,
                                              title: String,
                                              private val error: GitHubErrorBean,
                                              private val callback: Consumer<SubmittedReportInfo>) : Backgroundable(project, title, true) {

    override fun run(indicator: ProgressIndicator) {
        indicator.isIndeterminate = true
        val issueService: GitHubIssueService = getRequiredService()
        val pluginInfoProvider: PluginInfoProvider = getRequiredService()
        callback.consume(FeedbackSubmitter.submitFeedback(pluginInfoProvider, issueService, error))
    }

}
