package co.nums.intellij.aem.htl.psi.extensions

import co.nums.intellij.aem.htl.HtlBlocks
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlToken

private val htlBlocksNames = HtlDefinitions.blocks.map { it.type }
private val htlVariableBlocksNames = HtlDefinitions.blocks.filter { HtlBlocks.VARIABLE_BLOCKS.contains(it.type) }.map { it.type }

fun XmlAttribute.isHtlBlock() = (this.firstChild as? XmlToken)?.isHtlBlock() ?: false

fun XmlAttribute.isHtlVariableBlock(): Boolean {
    val blockType = (this.firstChild as? XmlToken)?.text?.substringBefore(".")?.toLowerCase()
    return htlVariableBlocksNames.contains(blockType)
}

fun XmlToken.isHtlBlock() = htlBlocksNames.contains(this.text.substringBefore(".").toLowerCase())
