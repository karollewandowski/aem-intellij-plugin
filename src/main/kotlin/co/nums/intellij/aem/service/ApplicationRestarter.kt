package co.nums.intellij.aem.service

import co.nums.intellij.aem.messages.AemPluginBundle
import com.intellij.ide.IdeBundle
import com.intellij.openapi.application.ApplicationNamesInfo
import com.intellij.openapi.application.ex.ApplicationManagerEx
import com.intellij.openapi.ui.Messages

object ApplicationRestarter {

    fun askForApplicationRestart() {
        if (showRestartDialog() == Messages.YES) {
            ApplicationManagerEx.getApplicationEx().restart(true)
        }
    }

    @Messages.YesNoResult
    private fun showRestartDialog(): Int {
        val action = if (ApplicationManagerEx.getApplicationEx().isRestartCapable) "restart" else "shutdown"
        return Messages.showYesNoDialog(
                AemPluginBundle.message("ide.restart.required.message", ApplicationNamesInfo.getInstance().fullProductName),
                AemPluginBundle.messageOrDefault("ide.restart.required.title", "AEM IntelliJ Plugin Update"),
                IdeBundle.message("ide.$action.action"),
                IdeBundle.message("ide.postpone.action"),
                Messages.getQuestionIcon()
        )
    }

}
