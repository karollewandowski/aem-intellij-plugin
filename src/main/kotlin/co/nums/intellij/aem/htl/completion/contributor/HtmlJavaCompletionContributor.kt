package co.nums.intellij.aem.htl.completion.contributor

import co.nums.intellij.aem.htl.completion.provider.*
import co.nums.intellij.aem.htl.psi.patterns.HtlPatterns
import com.intellij.codeInsight.completion.*

class HtmlJavaCompletionContributor : CompletionContributor() {

    init {
        extend(
                CompletionType.BASIC,
                HtlPatterns.simpleUseObjectDeclaration,
                HtlJavaUseApiClassesProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.simpleUseObjectDeclaration,
                HtlTemplatesProvider
        )
    }

}
