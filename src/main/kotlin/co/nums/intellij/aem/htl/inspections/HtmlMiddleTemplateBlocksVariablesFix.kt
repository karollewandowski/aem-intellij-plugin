package co.nums.intellij.aem.htl.inspections

import com.intellij.codeInspection.LocalQuickFix
import com.intellij.codeInspection.ProblemDescriptor
import com.intellij.openapi.project.Project
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlTag

/**
 * Created by Paweł Tarkowski on 18.02.2017.
 */

private const val NAME = "Fix not root variables"

class HtmlMiddleTemplateBlocksVariablesFix(private val attributeToMove: XmlAttribute, private val firstElement: XmlTag?) : LocalQuickFix {

    override fun getName() = NAME

    override fun getFamilyName() = NAME

    override fun applyFix(project: Project, descriptor: ProblemDescriptor) {
        if (firstElement != null) {
            firstElement.setAttribute(attributeToMove.name, attributeToMove.value)
            attributeToMove.delete()
        }
    }
}