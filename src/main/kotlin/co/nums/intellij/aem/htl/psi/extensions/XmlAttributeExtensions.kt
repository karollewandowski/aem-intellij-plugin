package co.nums.intellij.aem.htl.psi.extensions

import co.nums.intellij.aem.htl.HtlBlocks
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlToken

private val htlBlockTypes = HtlDefinitions.blocks.map { it.type }
private val htlVariableBlockTypes = HtlDefinitions.blocks.filter { HtlBlocks.VARIABLE_BLOCKS.contains(it.type) }.map { it.type }
private val htlImplicitVariableBlockTypes = HtlDefinitions.blocks.filter { HtlBlocks.ITERABLE.contains(it.type) }.map { it.type }

fun XmlAttribute.isHtlBlock() = (firstChild as? XmlToken)?.isHtlBlock() ?: false

fun XmlAttribute.isHtlVariableBlock(): Boolean {
    val blockType = (firstChild as? XmlToken)?.text?.substringBefore(".")?.toLowerCase()
    return htlVariableBlockTypes.contains(blockType)
}

fun XmlAttribute.isHtlVariableDeclaration(): Boolean {
    val blockName = (firstChild as? XmlToken)?.text ?: return false
    val blockType = blockName.substringBefore(".").toLowerCase()
    return htlVariableBlockTypes.contains(blockType)
            && (htlImplicitVariableBlockTypes.contains(blockType) || blockHasIdentifier(blockName))
}

private fun blockHasIdentifier(blockName: String): Boolean {
    val blockIdentifier = blockName.substringAfter('.', missingDelimiterValue = "")
    return blockIdentifier.isNotEmpty()
}

fun XmlToken.isHtlBlock() = htlBlockTypes.contains(text.substringBefore(".").toLowerCase())
