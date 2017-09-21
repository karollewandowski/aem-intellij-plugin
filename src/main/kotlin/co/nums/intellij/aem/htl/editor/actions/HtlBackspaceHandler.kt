package co.nums.intellij.aem.htl.editor.actions

import co.nums.intellij.aem.extensions.removeText
import co.nums.intellij.aem.htl.psi.extensions.*
import com.intellij.codeInsight.editorActions.BackspaceHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

/**
 * Deletes right brace when left brace is deleted in HTL expression.
 * // TODO: abstract it and use for quotes
 */
class HtlBackspaceHandler : BackspaceHandlerDelegate() {

    override fun beforeCharDeleted(deletedChar: Char, file: PsiFile, editor: Editor) {
        // do nothing
    }

    override fun charDeleted(deletedChar: Char, file: PsiFile, editor: Editor): Boolean {
        if (deletedChar == '{' && file.isHtl()) {
            val offset = editor.caretModel.offset
            if (offset < 1 || offset >= editor.document.textLength) {
                return false
            }
            val document = editor.document
            val nextChar = document.charsSequence[offset]
            if (nextChar == '}' && file.isAtHtlExpressionToken(offset)) {
                document.removeText(offset, offset + 1)
                return true
            }
        }
        return false
    }

    private fun PsiFile.isAtHtlExpressionToken(offset: Int): Boolean {
        val currentElement = this.findElementAt(offset) ?: return false
        return currentElement.isHtlExpressionToken()
    }

}
