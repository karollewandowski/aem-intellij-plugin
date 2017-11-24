package co.nums.intellij.aem.htl.index

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.data.blocks.*
import co.nums.intellij.aem.htl.definitions.HtlBlock
import co.nums.intellij.aem.htl.extensions.asHtmlFile
import co.nums.intellij.aem.htl.file.HtlFileType
import co.nums.intellij.aem.htl.psi.*
import com.intellij.psi.*
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.util.indexing.*
import com.intellij.util.io.*
import java.io.*

/**
 * HTL templates index where key is file path and value is
 * the list of HTL templates in this file.
 */
class HtlTemplatesIndex : FileBasedIndexExtension<String, List<HtlTemplate>>() {

    companion object {
        val NAME: ID<String, List<HtlTemplate>> = ID.create<String, List<HtlTemplate>>("HtlTemplateIndex")
        fun rebuild() = FileBasedIndex.getInstance()?.requestRebuild(NAME)
    }

    override fun getName() = NAME

    override fun getIndexer(): DataIndexer<String, List<HtlTemplate>, FileContent> = HtlTemplatesIndexer

    override fun getInputFilter() = FileBasedIndex.InputFilter { it.fileType == HtlFileType }

    override fun getKeyDescriptor(): KeyDescriptor<String> = EnumeratorStringDescriptor.INSTANCE

    override fun getValueExternalizer(): DataExternalizer<List<HtlTemplate>> = HtlTemplateDataExternalizer

    override fun dependsOnFileContent() = true

    override fun getVersion() = 0

    private object HtlTemplatesIndexer : DataIndexer<String, List<HtlTemplate>, FileContent> {

        override fun map(inputData: FileContent): Map<String, List<HtlTemplate>> {
            val htmlFile = inputData.psiFile.asHtmlFile() ?: return emptyMap()
            val filePath = inputData.file.path
            val htlTemplates = htmlFile.getTemplates(filePath)
            return if (htlTemplates.isNotEmpty()) mapOf(filePath to htlTemplates) else emptyMap()
        }

        private fun PsiFile.getTemplates(filePath: String): List<HtlTemplate> {
            return PsiTreeUtil.findChildrenOfType(this, XmlAttribute::class.java)
                    .filter { it.name.startsWith("${HtlBlock.TEMPLATE.type}.") }
                    .mapNotNull { it.toHtlTemplate(filePath) }
        }

        private fun XmlAttribute.toHtlTemplate(filePath: String): HtlTemplate? {
            if (!name.contains('.')) return null
            val templateName = name.substringAfter('.')
            if (templateName.isBlank()) return null
            val parameters = if (valueElement != null) {
                val optionsOffset = valueElement?.textOffset ?: return null
                val optionsExpression = containingFile.viewProvider.findElementAt(optionsOffset, HtlLanguage) ?: return null
                PsiTreeUtil.findChildrenOfType(optionsExpression, HtlOption::class.java).map { it.toHtlTemplateParameter() }
            } else {
                emptyList()
            }
            return HtlTemplate(filePath, templateName, parameters)
        }

        private fun HtlOption.toHtlTemplateParameter() = HtlTemplateParameter(optionName.text, optionValue?.getLiteralTextOrNull())

        private fun HtlOptionValue.getLiteralTextOrNull(): String? {
            var currentChild: PsiElement?
            while (true) {
                if (children.size != 1) return null
                currentChild = firstChild ?: return null
                if (currentChild is HtlLiteral) {
                    return firstChild.text
                }
            }
        }

    }

    private object HtlTemplateDataExternalizer : DataExternalizer<List<HtlTemplate>> {

        override fun save(output: DataOutput, htlTemplates: List<HtlTemplate>?) {
            if (htlTemplates != null) {
                output.writeInt(htlTemplates.size)
                for (htlTemplate in htlTemplates) {
                    output.writeTemplate(htlTemplate)
                }
            }
        }

        private fun DataOutput.writeTemplate(htlTemplate: HtlTemplate) {
            IOUtil.writeUTF(this, htlTemplate.filePath)
            IOUtil.writeUTF(this, htlTemplate.name)
            val parameters = htlTemplate.parameters
            this.writeInt(parameters.size)
            parameters.forEach { this.writeTemplateParameter(it) }
        }

        private fun DataOutput.writeTemplateParameter(parameter: HtlTemplateParameter) {
            IOUtil.writeUTF(this, parameter.name)
            if (parameter.defaultValue != null) {
                this.writeBoolean(true)
                IOUtil.writeUTF(this, parameter.defaultValue)
            } else {
                this.writeBoolean(false)
            }
        }

        override fun read(input: DataInput): List<HtlTemplate> {
            val templatesNumber = input.readInt()
            return (0 until templatesNumber).map { input.readTemplate() }
        }

        private fun DataInput.readTemplate(): HtlTemplate {
            val templateFilePath = IOUtil.readUTF(this)
            val templateName = IOUtil.readUTF(this)
            val templateParametersNumber = this.readInt()
            val templateParameters = (0 until templateParametersNumber).map { this.readTemplateParameter() }
            return HtlTemplate(templateFilePath, templateName, templateParameters)
        }

        private fun DataInput.readTemplateParameter(): HtlTemplateParameter {
            val name = IOUtil.readUTF(this)
            val defaultValue = if (this.readBoolean()) IOUtil.readUTF(this) else null
            return HtlTemplateParameter(name, defaultValue)
        }

    }

}
