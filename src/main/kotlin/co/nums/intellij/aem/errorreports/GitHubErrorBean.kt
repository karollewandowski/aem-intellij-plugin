package co.nums.intellij.aem.errorreports

import com.intellij.openapi.application.ApplicationNamesInfo
import com.intellij.openapi.application.ex.ApplicationInfoEx
import org.apache.commons.codec.digest.DigestUtils.md5Hex

class GitHubErrorBean(
        private val errorMessage: String?,
        private val stackTrace: String?,
        private val description: String?,
        private val lastAction: String?,
        private val pluginVersion: String?,
        private val systemInfo: SystemInfo,
        private val appNamesInfo: ApplicationNamesInfo,
        private val appInfo: ApplicationInfoEx
) {

    /**
     * Includes last event message and exception hash (10 last characters
     * of MD5 hex representation of printed stack trace).
     */
    val issueTitle = "${errorMessage()} [${errorHash()}]"

    private fun errorMessage() = if (!errorMessage.isNullOrBlank()) errorMessage else "Unspecified error"

    private fun errorHash() = if (stackTrace != null) md5Hex(stackTrace).takeLast(10) else "no-hash"

    val issueDetails = getCommentAsMarkdownText(includeException = true)

    val issueDetailsWithoutException = getCommentAsMarkdownText(includeException = false)

    private fun getCommentAsMarkdownText(includeException: Boolean) =
            description() + exception(includeException) + errorContext()

    private fun description() =
            """
            |## Description
            |${if (!description.isNullOrBlank()) description else "*No description*"}
            """.trimMargin()

    private fun exception(includeException: Boolean) = if (!includeException) "" else
            """
            |
            |## Exception
            |```
            |${stackTrace ?: "invalid stack trace"}
            |```
            """.trimMargin()

    private fun errorContext() =
            """
            |
            |## Error context
            |```
            |Last Action:       $lastAction
            |Plugin Version:    ${pluginVersion ?: "unknown"}
            |OS Name:           ${systemInfo.osName}
            |Java Version:      ${systemInfo.javaVersion}
            |Java VM Vendor:    ${systemInfo.javaVmVendor}
            |Product Name:      ${appNamesInfo.productName}
            |Full Product Name: ${appNamesInfo.fullProductName}
            |App Version Name:  ${appInfo.versionName}
            |Is EAP:            ${appInfo.isEAP}
            |App Build:         ${appInfo.build.asString()}
            |App Version:       ${appInfo.fullVersion}
            |```
            """.trimMargin()

}
