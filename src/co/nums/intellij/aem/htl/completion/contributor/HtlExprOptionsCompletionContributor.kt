package co.nums.intellij.aem.htl.completion.contributor

import co.nums.intellij.aem.htl.completion.provider.HtlDisplayContextsProvider
import co.nums.intellij.aem.htl.completion.provider.HtlExprOptionsCompletionProvider
import co.nums.intellij.aem.htl.psi.HtlTokenTypes
import com.intellij.codeInsight.completion.CompletionContributor
import com.intellij.codeInsight.completion.CompletionType
import com.intellij.patterns.PlatformPatterns.psiElement
import com.intellij.patterns.PsiElementPattern
import com.intellij.patterns.StandardPatterns.or
import com.intellij.psi.PsiElement
import com.intellij.psi.TokenType

class HtlExprOptionsCompletionContributor : CompletionContributor() {

    init {
        extend(
                CompletionType.BASIC,
                optionIdentifier(),
                HtlExprOptionsCompletionProvider
        )
        extend(
                CompletionType.BASIC,
                displayContextOptionValue(),
                HtlDisplayContextsProvider
        )
    }

    private fun optionIdentifier(): PsiElementPattern.Capture<PsiElement> {
        return psiElement(HtlTokenTypes.IDENTIFIER)
                .inside(psiElement(HtlTokenTypes.OPTION))
                .andNot(psiElement().afterLeaf(psiElement(HtlTokenTypes.ASSIGN)))
    }

    private fun displayContextOptionValue(): PsiElementPattern.Capture<PsiElement> {
        return psiElement()
                .inside(psiElement(HtlTokenTypes.OPTION))
                .afterLeafSkipping(
                        or(psiElement(HtlTokenTypes.ASSIGN), psiElement(TokenType.WHITE_SPACE)),
                        psiElement(HtlTokenTypes.IDENTIFIER).withText("context")
                )
    }

}
