package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.data.blocks.Block
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlAttribute

object HtlBlockDocGenerator {

    private val blocksDocs = HtlDefinitions.blocks.associate { Pair(it.type, it.getDocString()) }

    private fun Block.getDocString() = """
                   <code>${this.type}</code>
                   <p>${this.doc.description}</p>
                   <ul>
                     ${blockDetail("Element", this.doc.element)}
                     ${blockDetail("Content of element", this.doc.elementContent)}
                     ${blockDetail("Attribute value", this.doc.attributeValue)}
                     ${blockDetail("Attribute identifier", this.doc.attributeIdentifier)}
                   </ul>
                   """

    private fun blockDetail(name: String, value: String?) =
            if (value != null && value.isNotBlank()) "<li><strong>$name:</strong> $value</li>"
            else ""

    fun generateDoc(element: PsiElement?): String? {
        val htlBlockElement = element?.context as XmlAttribute
        val blockType = htlBlockElement.nameElement.text.substringBefore('.')
        return blocksDocs[blockType]
    }

}
