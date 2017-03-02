package co.nums.intellij.aem.htl.completion.provider.inserthandlers

import co.nums.intellij.aem.extensions.hasText
import co.nums.intellij.aem.extensions.moveCaret
import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil
import com.intellij.codeInsight.AutoPopupController
import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.openapi.editor.Document

object HtlExprOptionQuotesInsertHandler : InsertHandler<LookupElement> {

    private const val INTO_QUOTES_OFFSET = 2

    override fun handleInsert(context: InsertionContext, item: LookupElement) {
        val document = context.editor.document
        val offset = context.editor.caretModel.offset
        if (!document.hasQuotesAt(offset)) {
            handleQuotes(document, offset, context)
        }
    }

    private fun Document.hasQuotesAt(offset: Int) = this.hasText(offset, "=\"") || this.hasText(offset, "='")

    private fun handleQuotes(document: Document, offset: Int, context: InsertionContext) {
        document.insertQuotes(offset, context)
        val editor = context.editor
        editor.moveCaret(INTO_QUOTES_OFFSET)
        AutoPopupController.getInstance(context.project).scheduleAutoPopup(editor)
    }

    private fun Document.insertQuotes(offset: Int, context: InsertionContext) {
        val quote = getQuoteToInsert(context, offset)
        this.insertString(offset, "=$quote$quote")
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
