package co.nums.intellij.aem.htl.completion.provider.inserthandlers

import co.nums.intellij.aem.extensions.hasText
import co.nums.intellij.aem.extensions.moveCaret
import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.editor.Document

/**
 * Base for HTL blocks that will likely define value.
 */
abstract class HtlValueBlockInsertHandler
/**
 * @param insertionString text to insert after HTL block name, eg. `="${}"` in `data-sly-test="${}"` or `=""` in `data-sly-use=""`
 * @param insertionOffset offset that caret should be moved to after string insertion
 */
internal constructor(private val insertionString: String, private val insertionOffset: Int) : InsertHandler<LookupElement> {

    override fun handleInsert(context: InsertionContext, item: LookupElement) {
        val editor = context.editor
        val document = editor.document
        val offset = editor.caretModel.offset
        if (!hasBlockValue(document, offset)) {
            insertBlockValue(context, document, offset)
            editor.moveCaret(insertionOffset)
        }
    }

    private fun hasBlockValue(document: Document, offset: Int) =
            document.hasText(offset, "=\"") || document.hasText(offset, "='")

    private fun insertBlockValue(context: InsertionContext, document: Document, offset: Int) {
        val toInsert = if (glitchDetected(document, offset)) (insertionString + " ") else insertionString
        document.insertString(offset, toInsert)
        if (context.completionChar == '=') {
            context.setAddCompletionChar(false) // IDEA-19449
        }
    }

    private fun glitchDetected(document: Document, offset: Int) =
            offset >= document.textLength
                    || setOf('/', '>', '\n', '\r', '\t').contains(document.charsSequence[offset])

}
