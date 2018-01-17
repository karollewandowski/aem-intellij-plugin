package co.nums.intellij.aem.htl.completion.provider

import co.nums.intellij.aem.htl.index.HtlTemplatesIndex
import co.nums.intellij.aem.icons.HtlIcons
import co.nums.intellij.aem.service.jcrRoots
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.*
import com.intellij.openapi.util.Key
import com.intellij.psi.search.GlobalSearchScope
import com.intellij.util.ProcessingContext
import com.intellij.util.indexing.FileBasedIndex

private val insertionStringKey: Key<String> = Key.create("HtlTemplatesProvider.insertion.string")

object HtlTemplatesFilesProvider : CompletionProvider<CompletionParameters>() {

    override fun addCompletions(parameters: CompletionParameters, context: ProcessingContext?, result: CompletionResultSet) {
        result.addAllElements(htlTemplatesFiles(parameters))
    }

    private fun htlTemplatesFiles(parameters: CompletionParameters): List<LookupElement> {
        val currentFilePath = parameters.originalFile.virtualFile.path
        val project = parameters.position.project
        val basePath = project.basePath ?: ""
        val jcrRoots = project.jcrRoots.getAll()
        val currentFileDirPath = currentFilePath.substringBeforeLast('/').normalizePath(basePath, jcrRoots)
        return FileBasedIndex.getInstance().let { index ->
            val projectScope = GlobalSearchScope.allScope(project)
            index.getAllKeys(HtlTemplatesIndex.NAME, project)
                    .filter { it != currentFilePath }
                    .flatMap { index.getValues(HtlTemplatesIndex.NAME, it, projectScope) }
                    .flatMap { it }
                    .map { it.filePath }
                    .map { it.normalizePath(basePath, jcrRoots) }
                    .map { it.toLookupElement(currentFileDirPath) }
        }
    }

    private fun String.normalizePath(basePath: String, jcrRoots: Set<String>): String {
        var jcrRootRelativePath = removePrefix(basePath)
        for (jcrRoot in jcrRoots) {
            if (jcrRootRelativePath.startsWith(jcrRoot)) {
                jcrRootRelativePath = jcrRootRelativePath.removePrefix(jcrRoot)
                break
            }
        }
        return jcrRootRelativePath
    }

    private fun String.toLookupElement(currentFileDirPath: String): LookupElement {
        val templatesFileDir = this.substringBeforeLast('/')
        val templatesFileName = this.substringAfterLast('/')
        val tailText = if (templatesFileDir.isNotBlank()) " ($templatesFileDir)" else " (/)"
        val lookupElement = LookupElementBuilder.create(templatesFileName)
                .withIcon(HtlIcons.HTL_TEMPLATE)
                .withTailText(tailText, true)
                .withInsertHandler(HtlTemplateFileInsertHandler)
        val insertionString = getTemplatePathToInsert(templatesFileDir, currentFileDirPath)
        lookupElement.putUserData(insertionStringKey, insertionString)
        return lookupElement
    }

    private fun getTemplatePathToInsert(templatesFileDir: String, currentFileDirPath: String) =
            if (templatesFileDir == currentFileDirPath) ""
            else templatesFileDir.removePrefix("/apps/").removePrefix("/libs/") + "/"

    private object HtlTemplateFileInsertHandler : InsertHandler<LookupElement> {

        override fun handleInsert(context: InsertionContext, lookupElement: LookupElement) {
            val insertionString = lookupElement.getUserData(insertionStringKey) ?: return
            val document = context.editor.document
            val offset = context.editor.caretModel.offset - lookupElement.lookupString.length
            document.insertString(offset, insertionString)
        }

    }

}
