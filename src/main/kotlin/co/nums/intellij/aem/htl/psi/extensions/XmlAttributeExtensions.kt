package co.nums.intellij.aem.htl.psi.extensions

import co.nums.intellij.aem.htl.definitions.HtlBlock
import com.intellij.psi.xml.*

private val htlBlockTypes = HtlBlock.values().map { it.type }
private val htlVariableBlockTypes = HtlBlock.values().filter { it.identifierType.isVariable() }.map { it.type }
private val htlImplicitVariableBlockTypes = HtlBlock.values().filter { it.iterable }.map { it.type }

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
