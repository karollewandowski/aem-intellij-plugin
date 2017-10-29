package co.nums.intellij.aem.htl.documentation

import co.nums.intellij.aem.htl.extensions.isHtlBlock
import co.nums.intellij.aem.htl.psi.patterns.HtlPatterns
import com.intellij.lang.documentation.AbstractDocumentationProvider
import com.intellij.openapi.editor.Editor
import com.intellij.psi.*
import com.intellij.psi.xml.XmlToken

class HtlDocumentationProvider : AbstractDocumentationProvider() {

    override fun getCustomDocumentationElement(editor: Editor, file: PsiFile, contextElement: PsiElement?): PsiElement? {
        return when {
            contextElement is XmlToken && contextElement.isHtlBlock() -> contextElement
            HtlPatterns.variable.accepts(contextElement) -> contextElement
            HtlPatterns.propertyIdentifier.accepts(contextElement) -> contextElement
            HtlPatterns.optionIdentifier.accepts(contextElement) -> contextElement
            HtlPatterns.displayContextOptionValue.accepts(contextElement) -> contextElement
            else -> super.getCustomDocumentationElement(editor, file, contextElement)
        }
    }

    override fun generateDoc(element: PsiElement?, originalElement: PsiElement?): String? {
        element ?: return null
        return when {
            element is XmlToken && element.isHtlBlock() -> HtlBlockDocGenerator.generateDoc(element)
            HtlPatterns.variable.accepts(element) -> HtlVariableDocGenerator.generateDoc(element)
            HtlPatterns.propertyIdentifier.accepts(element) -> HtlPredefinedPropertyDocGenerator.generateDoc(element)
            HtlPatterns.optionIdentifier.accepts(element) -> HtlExpressionOptionDocGenerator.generateDoc(element)
            HtlPatterns.displayContextOptionValue.accepts(element) -> HtlDisplayContextDocGenerator.generateDoc(element)
            else -> null
        }
    }

}
