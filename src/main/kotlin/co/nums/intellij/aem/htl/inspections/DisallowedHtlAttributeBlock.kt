package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.extensions.canBeEdited
import co.nums.intellij.aem.extensions.removeText
import co.nums.intellij.aem.extensions.replaceString
import co.nums.intellij.aem.htl.definitions.HtlBlock
import co.nums.intellij.aem.htl.psi.extensions.isHtl
import co.nums.intellij.aem.htl.psi.extensions.isHtlBlock
import com.intellij.codeInsight.intention.IntentionAction
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.editor.Editor
import com.intellij.openapi.project.Project
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlToken

private val htlAttributeBlockType: String = HtlBlock.ATTRIBUTE.type

class DisallowedHtlAttributeBlockAnnotator : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.containingFile.isHtl() && element is XmlAttribute && element.isHtlBlock()) {
            val disallowedName = element.getDisallowedName()
            if (disallowedName != null) {
                val annotation = holder.createErrorAnnotation(element.nameElement.textRange,
                        "The '$disallowedName' attribute cannot be generated with 'data-sly-attribute' due to XSS vulnerability.")
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

    override fun getText() = "Change 'data-sly-attribute.$disallowedAttributeName' to '$disallowedAttributeName'"

    override fun getFamilyName() = "HTL inspections" // TODO: add to messages bundle and use in others

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

    override fun getText() = "Remove 'data-sly-attribute.$disallowedAttributeName' attribute"

    override fun getFamilyName() = "HTL inspections" // TODO: add to messages bundle and use in others

    override fun startInWriteAction() = true

    override fun isAvailable(project: Project, editor: Editor, file: PsiFile) = true

}
