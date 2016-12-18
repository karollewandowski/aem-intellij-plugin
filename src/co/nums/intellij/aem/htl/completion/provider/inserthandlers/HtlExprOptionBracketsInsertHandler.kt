package co.nums.intellij.aem.htl.completion.provider.inserthandlers

import co.nums.intellij.aem.extensions.hasText
import co.nums.intellij.aem.extensions.moveCaret
import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.editor.Document

object HtlExprOptionBracketsInsertHandler : InsertHandler<LookupElement> {

    private const val INTO_BRACKETS_OFFSET = 2

    override fun handleInsert(context: InsertionContext, item: LookupElement) {
        val editor = context.editor
        val document = editor.document
        val offset = editor.caretModel.offset
        if (!hasBrackets(document, offset)) {
            insertBrackets(context, document, offset)
            editor.moveCaret(INTO_BRACKETS_OFFSET)
        }
    }

    private fun hasBrackets(document: Document, offset: Int) = document.hasText(offset, "=[")

    private fun insertBrackets(context: InsertionContext, document: Document, offset: Int) {
        document.insertString(offset, "=[]")
        if (context.completionChar == '=') {
            context.setAddCompletionChar(false) // IDEA-19449
        }
    }

}
