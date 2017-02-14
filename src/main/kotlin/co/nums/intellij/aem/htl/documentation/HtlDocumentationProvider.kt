package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.psi.extensions.isHtlBlock
import co.nums.intellij.aem.htl.psi.patterns.HtlPatterns
import co.nums.intellij.aem.htl.service.HtlDefinitions
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.openapi.editor.Editor
import com.intellij.psi.PsiElement
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.xml.XmlAttribute
import com.intellij.psi.xml.XmlToken

class HtlDocumentationProvider : AbstractDocumentationProvider() {

    // TODO: move generation methods to separate classes
    // TODO: cache generated values

    override fun getCustomDocumentationElement(editor: Editor, file: PsiFile, contextElement: PsiElement?): PsiElement? {
        return when {
            contextElement is XmlToken && contextElement.isHtlBlock() -> contextElement
            HtlPatterns.variable.accepts(contextElement) -> contextElement
            HtlPatterns.predefinedPropertyIdentifier.accepts(contextElement) -> contextElement
            HtlPatterns.optionIdentifier.accepts(contextElement) -> contextElement
            HtlPatterns.displayContextOptionValue.accepts(contextElement) -> contextElement
            else -> super.getCustomDocumentationElement(editor, file, contextElement)
        }
    }

    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
        element ?: return null
        return when {
            element is XmlToken && element.isHtlBlock() -> generateBlockDoc(element)
            HtlPatterns.variable.accepts(element) -> generateGlobalObjectDoc(element)
            HtlPatterns.predefinedPropertyIdentifier.accepts(element) -> generatePredefinedPropertyDoc(element)
            HtlPatterns.optionIdentifier.accepts(element) -> generateOptionDoc(element)
            HtlPatterns.displayContextOptionValue.accepts(element) -> generateDisplayContextDoc(element)
            else -> null
        }
    }

    private fun generateBlockDoc(element: PsiElement?): String? {
        val htlBlockElement = element?.context as XmlAttribute
        val blockDoc = HtlDefinitions.blocks.find { htlBlockElement.text.startsWith(it.name) }?.doc
        if (blockDoc != null) {
            return """
                   <p>${blockDoc.description}</p>
                   <ul>
                     ${blockDetail("Element", blockDoc.element)}
                     ${blockDetail("Content of element", blockDoc.elementContent)}
                     ${blockDetail("Attribute value", blockDoc.attributeValue)}
                     ${blockDetail("Attribute identifier", blockDoc.attributeIdentifier)}
                   </ul>
                   """
        }
        return null
    }

    private fun blockDetail(name: String, value: String?) =
            if (value != null && value.isNotBlank()) "<li><strong>$name:</strong> $value</li>"
            else ""

    private fun generateGlobalObjectDoc(element: PsiElement): String? {
        val globalObjectName = element.text
        val globalObject = HtlDefinitions.globalObjects.find { it.name == globalObjectName }
        if (globalObject != null) {
            var globalObjectDoc = "<code>${globalObject.type}</code>"
            if (globalObject.description != null) {
                globalObjectDoc += "<p>${globalObject.description}</p>"
            }
            return globalObjectDoc
        }
        return null
    }

    private fun generatePredefinedPropertyDoc(element: PsiElement): String? {
        val propertyName = element.text
        val propertyType = HtlDefinitions.predefinedProperties.find { it.name == propertyName }?.type
        if (propertyType != null) {
            return "<code>$propertyType</code>"
        }
        return null
    }

    private fun generateOptionDoc(element: PsiElement): String? {
        val optionName = element.text
        return HtlDefinitions.expressionOptions.find { it.name == optionName }?.description
    }

    private fun generateDisplayContextDoc(element: PsiElement): String? {
        val displayContextName = element.text.removeSurrounding("'").removeSurrounding("\"")
        return HtlDefinitions.displayContexts.find { it.name == displayContextName }?.description
    }

    override fun getDocumentationElementForLookupItem(psiManager: PsiManager?, `object`: Any?, element: PsiElement?): PsiElement? {
        return super.getDocumentationElementForLookupItem(psiManager, `object`, element)
    }

}
