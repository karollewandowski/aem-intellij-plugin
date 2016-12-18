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
        if (element is HtlStringLiteral && quotesClashDetected(element)) {
            holder.createErrorAnnotation(element.getTextRange(), MESSAGE)
                    .registerFix(stringQuotesFixFor(element))
        }
    }

    private fun quotesClashDetected(element: PsiElement): Boolean {
        val outerHtmlAttributeQuote = HtlPsiUtil.getOuterHtmlAttributeQuote(element)
        if (outerHtmlAttributeQuote != null) {
            val htlStringQuote = element.text[0]
            return outerHtmlAttributeQuote == htlStringQuote
        }
        return false
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
