package co.nums.intellij.aem.htl.completion.contributor

import co.nums.intellij.aem.htl.completion.provider.HtlDisplayContextsProvider
import co.nums.intellij.aem.htl.completion.provider.HtlExprOptionsCompletionProvider
import co.nums.intellij.aem.htl.completion.provider.HtlGlobalObjectsCompletionProvider
import co.nums.intellij.aem.htl.completion.provider.HtlPredefinedPropertyCompletionProvider
import co.nums.intellij.aem.htl.psi.patterns.HtlPatterns
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType

class HtlCompletionContributor : CompletionContributor() {

    init {
        extend(
                CompletionType.BASIC,
                HtlPatterns.globalObjectIdentifier,
                HtlGlobalObjectsCompletionProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.predefinedPropertyIdentifier,
                HtlPredefinedPropertyCompletionProvider
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
