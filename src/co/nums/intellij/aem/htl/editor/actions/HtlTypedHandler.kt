package co.nums.intellij.aem.htl.editor.actions

import co.nums.intellij.aem.extensions.isHtl
import co.nums.intellij.aem.extensions.moveCaret
import com.intellij.codeInsight.editorActions.TypedHandlerDelegate
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.fileTypes.FileType
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiFile

class HtlTypedHandler : TypedHandlerDelegate() {

    override fun beforeCharTyped(charTyped: Char, project: Project?, editor: Editor, file: PsiFile, fileType: FileType?): TypedHandlerDelegate.Result {
        if (charTyped == '}' && file.isHtl()) {
            val offset = editor.caretModel.offset
            if (offset < 3 || offset >= editor.document.textLength) {
                return TypedHandlerDelegate.Result.CONTINUE
            }
            val nextChar = editor.document.charsSequence[offset]
            if (nextChar == '}') {
                editor.moveCaret(1)
                return TypedHandlerDelegate.Result.STOP
            }
        }
        return TypedHandlerDelegate.Result.CONTINUE
    }

    override fun charTyped(charTyped: Char, project: Project?, editor: Editor, file: PsiFile): TypedHandlerDelegate.Result {
        if (charTyped == '{' && file.isHtl()) {
            val offset = editor.caretModel.offset
            if (offset < 2 || offset > editor.document.textLength) {
                return TypedHandlerDelegate.Result.CONTINUE
            }
            val previousChar = editor.document.charsSequence[offset - 2]
            if (previousChar == '$') {
                editor.document.insertString(offset, "}")
            }
        }
        return TypedHandlerDelegate.Result.CONTINUE
    }

}
