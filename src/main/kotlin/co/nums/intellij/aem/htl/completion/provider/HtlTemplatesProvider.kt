package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.index.HtlTemplatesIndex
import co.nums.intellij.aem.icons.HtlIcons
import co.nums.intellij.aem.service.jcrRoots
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.util.ProcessingContext
import com.intellij.util.indexing.FileBasedIndex

object HtlTemplatesProvider : CompletionProvider<CompletionParameters>() {

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        result.addAllElements(htlTemplatesFiles(parameters))
    }

    private fun htlTemplatesFiles(parameters: CompletionParameters): List<LookupElement> {
        val basePath = parameters.position.project.basePath ?: ""
        val jcrRoots = parameters.position.project.jcrRoots.getAll()
        return FileBasedIndex.getInstance().getAllKeys(HtlTemplatesIndex.NAME, parameters.position.project)
                .map { it.normalizePath(basePath, jcrRoots) }
                .map { it.toLookupElement() }
    }

    private fun String.normalizePath(basePath: String, jcrRoots: Set<String>): String {
        var jcrRootRelativePath = removePrefix(basePath)
        for (jcrRoot in jcrRoots) {
            jcrRootRelativePath = jcrRootRelativePath.removePrefix(jcrRoot)
        }
        return jcrRootRelativePath
    }

    private fun String.toLookupElement(): LookupElement {
        val templatesFileDir = this.substringBeforeLast('/')
        val templatesFileName = this.substringAfterLast('/')
        return LookupElementBuilder.create(templatesFileName)
                .withIcon(HtlIcons.HTL_TEMPLATE)
                .withTailText(if (templatesFileDir.isNotBlank()) " ($templatesFileDir)" else " (/)", true)
    }

}
