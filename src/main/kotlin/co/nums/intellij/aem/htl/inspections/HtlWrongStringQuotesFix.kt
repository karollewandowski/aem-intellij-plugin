package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.extensions.canBeEdited
import co.nums.intellij.aem.htl.psi.extensions.isPartOfHtlString
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil

private const val NAME = "Fix quotes"
private const val SINGLE_QUOTE = '\''
private const val DOUBLE_QUOTE = '"'

object HtlStringSingleQuotesFix : HtlStringQuotesFix(SINGLE_QUOTE)
object HtlStringDoubleQuotesFix : HtlStringQuotesFix(DOUBLE_QUOTE)

open class HtlStringQuotesFix(private val quoteToFix: Char) : IntentionAction {

    private val quoteToInsert: Char
        get() = if (quoteToFix == DOUBLE_QUOTE) SINGLE_QUOTE else DOUBLE_QUOTE

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val currentElement = file.findElementAt(editor.caretModel.offset) ?: return
        val stringLiteralToFix = getStringLiteralToFix(currentElement)
        if (stringLiteralToFix != null && stringLiteralToFix.canBeEdited()) {
            fixQuotes(project, file, stringLiteralToFix)
        }
    }

    private fun getStringLiteralToFix(currentElement: PsiElement): PsiElement? {
        if (currentElement.isPartOfHtlString()) return currentElement.parent
        val prevVisibleLeaf = PsiTreeUtil.prevVisibleLeaf(currentElement) ?: return null
        if (prevVisibleLeaf.isPartOfHtlString()) return prevVisibleLeaf.parent
        return null
    }

    private fun fixQuotes(project: Project, file: PsiFile, htlStringLiteral: PsiElement) {
        val document = PsiDocumentManager.getInstance(project).getDocument(file)
        if (document != null) {
            val currentText = htlStringLiteral.text
            var replacement = quoteToInsert + currentText.substring(1)
            if (endsWithUnescapedQuote(currentText, replacement)) {
                replacement = replacement.substring(0, replacement.length - 1) + quoteToInsert
            }
            val textRange = htlStringLiteral.textRange
            document.replaceString(textRange.startOffset, textRange.endOffset, replacement)
        }
    }

    private fun endsWithUnescapedQuote(currentText: String, replacement: String) =
            currentText.length > 1
                    && currentText.endsWith(Character.toString(quoteToFix)) && !replacement.endsWith("\\" + quoteToFix)

    override fun getText() = NAME

    override fun getFamilyName() = NAME

    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile) = true

}
