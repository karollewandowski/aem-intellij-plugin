package co.nums.intellij.aem.htl.inspections;

import co.nums.intellij.aem.htl.psi.HtlStringLiteral;
import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil;
import com.intellij.lang.annotation.AnnotationHolder;
import com.intellij.lang.annotation.Annotator;
import com.intellij.psi.PsiElement;
import org.jetbrains.annotations.NotNull;

/**
 * Annotates HTL string literals with the same quotes as enclosing HTML attribute.
 */
public class HtlWrongStringQuotesAnnotator implements Annotator {

	@Override
	public void annotate(@NotNull PsiElement element, @NotNull AnnotationHolder holder) {
		if (element instanceof HtlStringLiteral) {
			Character enclosingHtmlAttributeQuote = HtlPsiUtil.getEnclosingHtmlAttributeQuote(element);
			if (enclosingHtmlAttributeQuote != null) {
				char stringLiteralQuote = element.getText().charAt(0);
				if (stringLiteralQuote == enclosingHtmlAttributeQuote) {
					holder.createErrorAnnotation(element.getTextRange(), "Quotes must differ from enclosing attribute's quotes")
							.registerFix(new HtlStringQuotesFix(stringLiteralQuote));
				}
			}
		}
	}

}
