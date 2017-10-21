package co.nums.intellij.aem.htl.completion.contributor

import co.nums.intellij.aem.htl.completion.provider.*
import co.nums.intellij.aem.htl.psi.patterns.HtlPatterns
import com.intellij.codeInsight.completion.*

class HtlCompletionContributor : CompletionContributor() {

    init {
        extend(
                CompletionType.BASIC,
                HtlPatterns.variable,
                HtlGlobalObjectsCompletionProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.variable,
                HtlBlockVariablesProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.propertyIdentifier,
                HtlPredefinedPropertyCompletionProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.optionIdentifier,
                HtlExpressionOptionsCompletionProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.displayContextOptionValue,
                HtlDisplayContextsProvider
        )
    }

}
