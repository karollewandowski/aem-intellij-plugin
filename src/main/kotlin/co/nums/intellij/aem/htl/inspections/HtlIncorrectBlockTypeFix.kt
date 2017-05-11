package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.extensions.canBeEdited
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiDocumentManager
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.xml.XmlAttribute

open class HtlIncorrectBlockTypeFix(private val blockType: String) : IntentionAction {

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val currentElement = file.findElementAt(editor.caretModel.offset) ?: return
        val blockName = (currentElement.parent as? XmlAttribute)?.nameElement ?: return
        if (currentElement.canBeEdited()) {
            fixHtlBlockType(project, file, blockName)
        }
    }

    private fun fixHtlBlockType(project: Project, file: PsiFile, blockName: PsiElement) {
        val document = PsiDocumentManager.getInstance(project).getDocument(file)
        if (document != null) {
            val currentBlockType = blockName.text.substringBefore('.')
            val startOffset = blockName.textRange.startOffset
            val endOffset = startOffset + currentBlockType.length
            document.replaceString(startOffset, endOffset, blockType)
        }
    }

    override fun getFamilyName() = "Fix incorrect HTL block type"

    override fun getText() = "Change to '$blockType'"

    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile) = true

}
