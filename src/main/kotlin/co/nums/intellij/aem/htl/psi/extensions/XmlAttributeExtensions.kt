package co.nums.intellij.aem.htl.psi.extensions

import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlToken

private val htlBlocksNames = HtlDefinitions.blocks.map { it.name }

fun XmlAttribute.isHtlBlock() = (this.firstChild as? XmlToken)?.isHtlBlock() ?: false

fun XmlToken.isHtlBlock() = htlBlocksNames.contains(this.text.substringBefore(".").toLowerCase())
