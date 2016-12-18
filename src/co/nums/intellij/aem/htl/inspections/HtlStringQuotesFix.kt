package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.psi.HtlTokenTypes
import com.intellij.codeInsight.FileModificationService
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.impl.source.tree.LeafPsiElement

private const val NAME = "Fix quotes"
private const val SINGLE_QUOTE = '\''
private const val DOUBLE_QUOTE = '"'

object HtlStringSingleQuotesFix : HtlStringQuotesFix('\'')
object HtlStringDoubleQuotesFix : HtlStringQuotesFix('"')

open class HtlStringQuotesFix(private val quoteToFix: Char) : IntentionAction {

    private val quoteToInsert: Char
        get() = if (quoteToFix == DOUBLE_QUOTE) SINGLE_QUOTE else DOUBLE_QUOTE

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val currentElement = file.findElementAt(editor.caretModel.offset) ?: return
        if (isHtlString(currentElement) && editionAllowed(currentElement)) {
            fixQuotes(project, file, currentElement)
        }
    }

    private fun isHtlString(element: PsiElement) =
            element is LeafPsiElement
                    && (element.elementType === HtlTokenTypes.SINGLE_QUOTED_STRING
                    || element.elementType === HtlTokenTypes.DOUBLE_QUOTED_STRING)

    private fun editionAllowed(element: PsiElement) =
            element.isValid && FileModificationService.getInstance().prepareFileForWrite(element.containingFile)

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
