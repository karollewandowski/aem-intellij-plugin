package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.psi.extensions.XmlElementVisitorExtension
import co.nums.intellij.aem.htl.psi.extensions.isHtlVariableBlock
import com.intellij.codeInspection.InspectionProfileEntry
import com.intellij.codeInspection.ProblemsHolder
import com.intellij.codeInspection.XmlSuppressableInspectionTool
import com.intellij.psi.PsiElementVisitor
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlFile
import com.intellij.psi.xml.XmlTag
import org.jetbrains.annotations.NotNull

class HtmlMiddleTemplateBlocksVariablesAnnotator : XmlSuppressableInspectionTool() {

    private val MESSAGE = "Use data-sly-use variable statements only on root elements"

    override fun getDisplayName(): String {
        return "Middle template blocks variables annotator"
    }

    override fun getShortName(): String {
        return "HtmlMiddleTemplateBlocksVariablesAnnotator"
    }

    override fun getGroupDisplayName(): String {
        return "Htl inspections"
    }

    override fun getDescriptionContextClass(): Class<out InspectionProfileEntry> {
        return super.getDescriptionContextClass()
    }

    override fun getStaticDescription(): String? {
        return "Select non root data-sly-use statements variables"
    }


    override fun isEnabledByDefault(): Boolean {
        return true
    }

    fun checkAttribute(attribute: XmlAttribute, holder: ProblemsHolder, firstElement: XmlTag?) {
        if (attribute.isHtlVariableBlock() && attribute.name.startsWith("data-sly-use") && (firstElement != null && firstElement != attribute.parent)) {
            holder.registerProblem(attribute, MESSAGE, HtmlMiddleTemplateBlocksVariablesFix(attribute, firstElement))
        }
    }

    @NotNull
    override fun buildVisitor(holder: ProblemsHolder, isOnTheFly: Boolean): PsiElementVisitor {
        return object : XmlElementVisitorExtension() {
            override fun visitXmlFile(file: XmlFile?) {
                super.visitXmlFile(file)
            }

            override fun visitXmlAttribute(attribute: XmlAttribute) {
                checkAttribute(attribute, holder, getFirstElement())
            }
        }
    }
}