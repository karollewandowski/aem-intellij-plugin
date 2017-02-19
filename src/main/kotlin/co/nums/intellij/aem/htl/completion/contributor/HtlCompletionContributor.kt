package co.nums.intellij.aem.htl.completion.contributor

import co.nums.intellij.aem.htl.completion.provider.HtlBlockVariablesProvider
import co.nums.intellij.aem.htl.completion.provider.HtlDisplayContextsProvider
import co.nums.intellij.aem.htl.completion.provider.HtlExprOptionsCompletionProvider
import co.nums.intellij.aem.htl.completion.provider.HtlGlobalObjectsCompletionProvider
import co.nums.intellij.aem.htl.completion.provider.HtlListPropertiesProvider
import co.nums.intellij.aem.htl.completion.provider.HtlPredefinedPropertyCompletionProvider
import co.nums.intellij.aem.htl.psi.patterns.HtlPatterns
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType

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
                HtlPatterns.propertyIdentifier,
                HtlListPropertiesProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.optionIdentifier,
                HtlExprOptionsCompletionProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.displayContextOptionValue,
                HtlDisplayContextsProvider
        )
    }

}
