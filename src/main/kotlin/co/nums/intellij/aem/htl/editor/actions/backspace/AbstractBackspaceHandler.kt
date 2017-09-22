package co.nums.intellij.aem.htl.editor.actions.backspace

import co.nums.intellij.aem.extensions.removeText
import co.nums.intellij.aem.htl.psi.extensions.isHtl
import com.intellij.codeInsight.editorActions.BackspaceHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiFile

abstract class AbstractBackspaceHandler : BackspaceHandlerDelegate() {

    abstract val expectedDeletedChar: Char
    abstract val expectedNextChar: Char

    override fun beforeCharDeleted(deletedChar: Char, file: PsiFile, editor: Editor) {
        // do nothing
    }

    override fun charDeleted(deletedChar: Char, file: PsiFile, editor: Editor): Boolean {
        if (deletedChar == expectedDeletedChar && file.isHtl()) {
            val offset = editor.caretModel.offset
            if (offset < 1 || offset >= editor.document.textLength) {
                return false
            }
            val document = editor.document
            val nextChar = document.charsSequence[offset]
            if (nextChar == expectedNextChar && shouldBeDeleted(file, offset)) {
                document.removeText(offset, offset + 1)
                return true
            }
        }
        return false
    }

    abstract fun shouldBeDeleted(file: PsiFile, offset: Int): Boolean

}
