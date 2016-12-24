package co.nums.intellij.aem.htl.completion.provider.inserthandlers

import co.nums.intellij.aem.extensions.hasQuotesAt
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
 * @param insertionString text to insert after HTL block type, eg. `="${}"` in `data-sly-test="${}"` or `=""` in `data-sly-use=""`
 * @param insertionOffset offset that caret should be moved to after string insertion
 */
internal constructor(private val insertionString: String, private val insertionOffset: Int) : InsertHandler<LookupElement> {

    override fun handleInsert(context: InsertionContext, item: LookupElement) {
        val document = context.editor.document
        val offset = context.editor.caretModel.offset
        if (!document.hasBlockValueAt(offset)) {
            document.insertBlockValue(offset, context)
        }
    }

    private fun Document.hasBlockValueAt(offset: Int) = this.hasQuotesAt(offset)

    private fun Document.insertBlockValue(offset: Int, context: InsertionContext) {
        this.insertString(offset, insertionString)
        if (context.completionChar == '=') {
            context.setAddCompletionChar(false) // IDEA-19449
        }
        context.editor.moveCaret(insertionOffset)
    }

}
