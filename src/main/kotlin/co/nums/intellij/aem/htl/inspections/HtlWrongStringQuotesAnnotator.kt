package co.nums.intellij.aem.htl.inspections

import co.nums.intellij.aem.htl.psi.HtlStringLiteral
import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement

/**
 * Annotates HTL string literals with the same quotes as outer HTML attribute.
 */
class HtlWrongStringQuotesAnnotator : Annotator {

    private val MESSAGE = "Quotes must differ from outer attribute's quotes"

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is HtlStringLiteral && element.hasWrongQuotes()) {
            holder.createErrorAnnotation(element.getTextRange(), MESSAGE)
                    .registerFix(stringQuotesFixFor(element))
        }
    }

    private fun PsiElement.hasWrongQuotes(): Boolean {
        val htlStringQuote = this.text[0]
        val outerHtmlAttributeQuote = HtlPsiUtil.getOuterHtmlAttributeQuote(this)
        return htlStringQuote == outerHtmlAttributeQuote
    }

    private fun stringQuotesFixFor(htlStringLiteral: HtlStringLiteral): HtlStringQuotesFix {
        val htlStringQuote = htlStringLiteral.text[0]
        return when (htlStringQuote) {
            '\'' -> HtlStringSingleQuotesFix
            '"' -> HtlStringDoubleQuotesFix
            else -> throw IllegalStateException("Found invalid string quote character: $htlStringQuote")
        }
    }

}
