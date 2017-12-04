package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.extensions.canBeEdited
import co.nums.intellij.aem.htl.definitions.*
import co.nums.intellij.aem.htl.extensions.*
import co.nums.intellij.aem.htl.highlighting.createReferenceErrorAnnotation
import co.nums.intellij.aem.messages.HtlInspectionsBundle.message
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.lang.annotation.*
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.xml.XmlAttribute
import com.intellij.util.text.EditDistance

class HtlIncorrectBlockTypeAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.containingFile.isHtl() && element is XmlAttribute && element.name.toLowerCase().startsWith(HTL_BLOCK_PREFIX)) {
            if (!element.isHtlBlock()) {
                highlightUnknownHtlBlockError(holder, element)
            }
        }
    }

    private fun highlightUnknownHtlBlockError(holder: AnnotationHolder, element: XmlAttribute) {
        val blockType = element.localName.substringBefore('.')
        val suggestedBlockType = blockStartingWith(blockType) ?: closestLevenshteinDistance(blockType)
        val errorMessage = message("inspection.htl.incorrect.block.type.annotation", blockType, suggestedBlockType)
        holder.createReferenceErrorAnnotation(element.nameElement.textRange, errorMessage)
                .registerFix(HtlIncorrectBlockTypeFix(suggestedBlockType))
    }

    private fun blockStartingWith(blockType: String) = HtlBlock.values()
            .map { it.type }
            .firstOrNull { it.startsWith(blockType) }

    private fun closestLevenshteinDistance(blockType: String) = HtlBlock.values()
            .sortedBy { EditDistance.levenshtein(it.type, blockType, false) }
            .map { it.type }
            .first()

}


class HtlIncorrectBlockTypeFix(private val blockType: String) : IntentionAction {

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

    override fun getFamilyName() = message("inspection.htl.family")

    override fun getText() = message("inspection.htl.incorrect.block.type.fix.change", blockType)

    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile) = true

}
