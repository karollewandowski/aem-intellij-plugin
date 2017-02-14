package co.nums.intellij.aem.htl.psi.search

import co.nums.intellij.aem.htl.HtlBlocks
import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.data.blocks.Block
import co.nums.intellij.aem.htl.data.blocks.HtlBlockVariable
import co.nums.intellij.aem.htl.psi.extensions.isHtlBlock
import co.nums.intellij.aem.htl.psi.extensions.isHtlExpressionToken
import co.nums.intellij.aem.htl.psi.extensions.isHtlString
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.PsiFile
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlToken

object HtlSearch {

    private const val DEFAULT_USE_OBJECT_TYPE = "Use object"

    private val htlVariableBlocks = HtlDefinitions.blocks.filter { it.identifierType.isVariable() }
    private val htlVariableBlockNames = htlVariableBlocks.map { it.name }

    fun blockVariables(htmlFile: PsiFile): Collection<HtlBlockVariable> {
        return PsiTreeUtil.findChildrenOfType(htmlFile, XmlAttribute::class.java)
                .flatMap { it.toHtlVariables() }
                .filterNotNull()
    }

    private fun XmlAttribute.toHtlVariables(): List<HtlBlockVariable> {
        if (this.isHtlBlock()) {
            val blockName = this.text.substringBefore(".").toLowerCase()
            if (htlVariableBlockNames.contains(blockName)) {
                val blockDefinition = htlVariableBlocks.find { it.name == blockName } ?: return emptyList()
                return this.createHtlVariables(blockDefinition)
            }
        }
        return emptyList()
    }

    private fun XmlAttribute.createHtlVariables(blockDefinition: Block): List<HtlBlockVariable> {
        val identifier = this.getIdentifier(blockDefinition.isIterable()) ?: return emptyList()
        val dataType = this.getDataType(blockDefinition)
        val variable = HtlBlockVariable(identifier, blockDefinition.identifierType, dataType, this)
        if (blockDefinition.isIterable()) {
            val listVariable = createImplicitListVariable(identifier, blockDefinition)
            return listOf(variable, listVariable)
        }
        return listOf(variable)
    }

    private fun XmlAttribute.createImplicitListVariable(identifier: String, blockDefinition: Block): HtlBlockVariable {
        val listIdentifier = identifier + "List"
        val listDataType = this.getDataType(blockDefinition, true)
        return HtlBlockVariable(listIdentifier, blockDefinition.identifierType, listDataType, this)
    }

    private fun XmlAttribute.getIdentifier(iterable: Boolean): String? {
        val blockText = (this.firstChild as XmlToken).text
        if (blockText.contains('.')) {
            val identifier = blockText.substringAfter('.')
            return if (identifier.isNotBlank()) identifier else null
        } else if (iterable) {
            return "item"
        }
        return null
    }

    private fun XmlAttribute.getDataType(block: Block, implicitList: Boolean = false): String {
        return when {
            HtlBlocks.USE == block.name -> this.getUseObjectType()
            HtlBlocks.TEST == block.name -> "Test result"
            block.isIterable() -> if (implicitList) "Iterable" else "List element" // TODO: resolve Java type
            else -> ""
        }
    }

    private fun XmlAttribute.getUseObjectType(): String {
        var useObjectType: String
        val blockValue = this.valueElement ?: return DEFAULT_USE_OBJECT_TYPE
        val htlFile = blockValue.containingFile.viewProvider.getPsi(HtlLanguage) ?: return DEFAULT_USE_OBJECT_TYPE
        val htlExpressionStart = htlFile.findElementAt(blockValue.textOffset) ?: return DEFAULT_USE_OBJECT_TYPE
        if (htlExpressionStart.isHtlExpressionToken()) {
            val nextToken = PsiTreeUtil.nextVisibleLeaf(htlExpressionStart) ?: return DEFAULT_USE_OBJECT_TYPE
            useObjectType = if (nextToken.isHtlString()) nextToken.text else ""
        } else {
            useObjectType = blockValue.text
        }
        useObjectType = useObjectType.trim('"', '\'', ' ')
        if (useObjectType.isBlank()) {
            return DEFAULT_USE_OBJECT_TYPE
        }
        return useObjectType
    }

}
