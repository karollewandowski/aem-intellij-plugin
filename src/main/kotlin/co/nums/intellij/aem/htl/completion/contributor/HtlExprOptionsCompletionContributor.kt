package co.nums.intellij.aem.htl.completion.contributor

import co.nums.intellij.aem.htl.completion.provider.HtlDisplayContextsProvider
import co.nums.intellij.aem.htl.completion.provider.HtlExprOptionsCompletionProvider
import co.nums.intellij.aem.htl.psi.patterns.HtlPatterns
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType

class HtlExprOptionsCompletionContributor : CompletionContributor() {

    init {
        extend(
                CompletionType.BASIC,
                HtlPatterns.optionIdentifier(),
                HtlExprOptionsCompletionProvider
        )
        extend(
                CompletionType.BASIC,
                HtlPatterns.displayContextOptionValue(),
                HtlDisplayContextsProvider
        )
    }

}
