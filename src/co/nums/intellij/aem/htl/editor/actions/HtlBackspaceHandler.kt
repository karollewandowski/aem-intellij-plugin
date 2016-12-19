package co.nums.intellij.aem.htl.editor.actions

import co.nums.intellij.aem.extensions.isHtl
import co.nums.intellij.aem.extensions.isHtlExpressionToken
import co.nums.intellij.aem.extensions.removeText
import com.intellij.codeInsight.editorActions.BackspaceHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

/**
 * Deletes right brace when left brace is deleted in HTL expression.
 */
class HtlBackspaceHandler : BackspaceHandlerDelegate() {

    override fun beforeCharDeleted(c: Char, file: PsiFile, editor: Editor) {
        // do nothing
    }

    override fun charDeleted(deletedChar: Char, file: PsiFile, editor: Editor): Boolean {
        if (deletedChar == '{' && file.isHtl()) {
            val document = editor.document
            val offset = editor.caretModel.offset
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
