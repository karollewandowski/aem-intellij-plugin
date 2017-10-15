package co.nums.intellij.aem.htl.completion.provider.insertHandler

import co.nums.intellij.aem.extensions.hasText
import co.nums.intellij.aem.extensions.moveCaret
import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.editor.Document

object HtlExprOptionBracketsInsertHandler : InsertHandler<LookupElement> {

    private const val INTO_BRACKETS_OFFSET = 2

    override fun handleInsert(context: InsertionContext, item: LookupElement) {
        val document = context.editor.document
        val offset = context.editor.caretModel.offset
        if (!document.hasBracketsAt(offset)) {
            document.insertBrackets(offset, context)
        }
    }

    private fun Document.hasBracketsAt(offset: Int) = this.hasText(offset, "=[")

    private fun Document.insertBrackets(offset: Int, context: InsertionContext) {
        this.insertString(offset, "=[]")
        if (context.completionChar == '=') {
            context.setAddCompletionChar(false) // IDEA-19449
        }
        context.editor.moveCaret(INTO_BRACKETS_OFFSET)
    }

}
