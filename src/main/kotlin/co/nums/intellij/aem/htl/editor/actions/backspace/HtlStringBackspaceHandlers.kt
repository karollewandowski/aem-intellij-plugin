package co.nums.intellij.aem.htl.editor.actions.backspace

import co.nums.intellij.aem.htl.extensions.isPartOfHtlString
import com.intellij.psi.PsiFile

class HtlSingleQuotedStringBackspaceHandler : HtlStringBackspaceHandler('\'')
class HtlDoubleQuotedStringBackspaceHandler : HtlStringBackspaceHandler('"')

open class HtlStringBackspaceHandler(quoteChar: Char) : AbstractBackspaceHandler() {

    override val expectedDeletedChar = quoteChar
    override val expectedNextChar = quoteChar

    override fun shouldBeDeleted(file: PsiFile, offset: Int) = file.isAtHtlStringToken(offset)

    private fun PsiFile.isAtHtlStringToken(offset: Int): Boolean {
        val currentElement = this.findElementAt(offset) ?: return false
        return currentElement.isPartOfHtlString()
    }
}
