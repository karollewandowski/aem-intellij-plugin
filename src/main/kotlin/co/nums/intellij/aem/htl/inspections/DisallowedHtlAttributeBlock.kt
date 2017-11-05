package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.extensions.*
import co.nums.intellij.aem.htl.definitions.HtlBlock
import co.nums.intellij.aem.htl.extensions.*
import co.nums.intellij.aem.messages.HtlInspectionsBundle.message
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.lang.annotation.*
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.*
import com.intellij.psi.xml.*

private val htlAttributeBlockType: String = HtlBlock.ATTRIBUTE.type

class DisallowedHtlAttributeBlockAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.containingFile.isHtl() && element is XmlAttribute && element.isHtlBlock()) {
            val disallowedName = element.getDisallowedName()
            if (disallowedName != null) {
                val annotation = holder.createErrorAnnotation(
                        element.nameElement.textRange,
                        message("inspection.htl.disallowed.attribute.block.annotation", disallowedName))
                annotation.registerFix(DisallowedHtlAttributeBlockToPlainAttributeFix(disallowedName))
                annotation.registerFix(RemoveDisallowedHtmlAttributeBlockFix(disallowedName))
            }
        }
    }

    private fun XmlAttribute.getDisallowedName(): String? {
        val blockName = this.name.toLowerCase()
        if (blockName == "$htlAttributeBlockType.style" || blockName.startsWith("$htlAttributeBlockType.on")) {
            return blockName.substringAfter('.')
        }
        return null
    }

}


class DisallowedHtlAttributeBlockToPlainAttributeFix(private val disallowedAttributeName: String) : IntentionAction {

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val currentElement = file.findElementAt(editor.caretModel.offset) as? XmlToken ?: return
        val xmlAttribute = currentElement.parent as? XmlAttribute ?: return
        val xmlAttributeName = xmlAttribute.nameElement
        if (xmlAttributeName.canBeEdited()) {
            editor.document.replaceString(xmlAttributeName.textRange, disallowedAttributeName)
        }
    }

    override fun getText() = message("inspection.htl.disallowed.attribute.block.fix.change.attribute", disallowedAttributeName)

    override fun getFamilyName() = message("inspection.htl.family")

    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile) = true

}


class RemoveDisallowedHtmlAttributeBlockFix(private val disallowedAttributeName: String?) : IntentionAction {

    override fun invoke(project: Project, editor: Editor, file: PsiFile) {
        val currentElement = file.findElementAt(editor.caretModel.offset) as? XmlToken ?: return
        val xmlAttribute = currentElement.parent as? XmlAttribute ?: return
        if (currentElement.canBeEdited()) {
            editor.document.removeText(xmlAttribute.textRange)
        }
    }

    override fun getText() = message("inspection.htl.disallowed.attribute.block.fix.remove.attribute", disallowedAttributeName)

    override fun getFamilyName() = message("inspection.htl.family")

    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile) = true

}
