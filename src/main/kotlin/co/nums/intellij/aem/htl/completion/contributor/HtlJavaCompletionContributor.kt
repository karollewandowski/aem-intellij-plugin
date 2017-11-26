package co.nums.intellij.aem.htl.completion.contributor

import co.nums.intellij.aem.htl.completion.provider.HtlJavaUseApiClassesProvider
import co.nums.intellij.aem.htl.psi.patterns.HtlPatterns
import com.intellij.codeInsight.completion.*

class HtlJavaCompletionContributor : CompletionContributor() {

    init {
        extend(
                CompletionType.BASIC,
                HtlPatterns.expressionUseObjectDeclaration,
                HtlJavaUseApiClassesProvider)
    }

}
