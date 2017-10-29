package co.nums.intellij.aem.htl.editor.actions.backspace

import co.nums.intellij.aem.htl.extensions.isHtlExpressionToken
import com.intellij.psi.PsiFile

class HtlExpressionBackspaceHandler : AbstractBackspaceHandler() {

    override val expectedDeletedChar = '{'
    override val expectedNextChar = '}'

    override fun shouldBeDeleted(file: PsiFile, offset: Int) = file.isAtHtlExpressionToken(offset)

    private fun PsiFile.isAtHtlExpressionToken(offset: Int): Boolean {
        val currentElement = this.findElementAt(offset) ?: return false
        return currentElement.isHtlExpressionToken()
    }

}
