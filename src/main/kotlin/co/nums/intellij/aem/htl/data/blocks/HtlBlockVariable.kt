package co.nums.intellij.aem.htl.data.blocks

import co.nums.intellij.aem.htl.definitions.BlockIdentifierType
import com.intellij.psi.xml.XmlAttribute

data class HtlBlockVariable(
        val identifier: String,
        val identifierType: BlockIdentifierType,
        val dataType: String,
        val definer: XmlAttribute
)
