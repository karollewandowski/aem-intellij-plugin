package co.nums.intellij.aem.htl.completion.provider.inserthandlers

import co.nums.intellij.aem.extensions.hasText
import co.nums.intellij.aem.extensions.moveCaret
import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil
import com.intellij.codeInsight.AutoPopupController
import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.Editor

object HtlExprOptionQuotesInsertHandler : InsertHandler<LookupElement> {

    private const val INTO_QUOTES_OFFSET = 2

    override fun handleInsert(context: InsertionContext, item: LookupElement) {
        val editor = context.editor
        val document = editor.document
        val offset = editor.caretModel.offset
        if (!hasQuotes(document, offset)) {
            handleQuotes(context, editor, document, offset)
        }
    }

    private fun hasQuotes(document: Document, offset: Int) =
            document.hasText(offset, "=\"") || document.hasText(offset, "='")

    private fun handleQuotes(context: InsertionContext, editor: Editor, document: Document, offset: Int) {
        insertQuotes(context, document, offset)
        editor.moveCaret(INTO_QUOTES_OFFSET)
        AutoPopupController.getInstance(context.project).scheduleAutoPopup(editor)
    }

    private fun insertQuotes(context: InsertionContext, document: Document, offset: Int) {
        val quote = getQuoteToInsert(context, offset)
        document.insertString(offset, "=$quote$quote")
    }

    private fun getQuoteToInsert(context: InsertionContext, offset: Int): Char {
        val viewProvider = context.file.viewProvider
        val currentElement = viewProvider.findElementAt(offset) ?: return '\''
        val outerQuote = HtlPsiUtil.getOuterHtmlAttributeQuote(currentElement)
        return when (outerQuote) {
            '\'' -> '"'
            else -> '\''
        }
    }

}
