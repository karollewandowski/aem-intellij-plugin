package co.nums.intellij.aem.htl.completion.contributor

import co.nums.intellij.aem.htl.completion.provider.HtlBlocksCompletionProvider
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.XmlPatterns.xmlAttribute
import com.intellij.psi.xml.XmlTokenType

class HtmlCompletionContributor : CompletionContributor() {

    init {
        extend(
                CompletionType.BASIC,
                psiElement(XmlTokenType.XML_NAME).inside(xmlAttribute()),
                HtlBlocksCompletionProvider
        )
    }

}
