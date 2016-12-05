package co.nums.intellij.aem.htl.psi.impl;

import co.nums.intellij.aem.htl.psi.HtlExpression;
import com.intellij.lang.html.HTMLLanguage;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttributeValue;
import org.jetbrains.annotations.Nullable;

import java.util.Optional;

public final class HtlPsiUtil {

	private HtlPsiUtil() {
		// no instances
	}

	/**
	 * Returns enclosing HTML attribute quote if HTL expression is enclosed by
	 * attribute.
	 *
	 * @param element
	 * 		element from expression to check
	 * @return enclosing HTML attribute quote or {@code null} if element is not
	 * enclosed by attribute
	 */
	@Nullable
	public static Character getEnclosingHtmlAttributeQuote(PsiElement element) {
		return Optional.ofNullable(element)
				.map(el -> PsiTreeUtil.getParentOfType(el, HtlExpression.class))
				.map(HtlPsiUtil::getEnclosingXmlAttributeValue)
				.map(XmlAttributeValue::getText)
				.filter(text -> text.length() > 0)
				.map(xmlAttributeText -> xmlAttributeText.charAt(0))
				.orElse(null);
	}

	@Nullable
	private static XmlAttributeValue getEnclosingXmlAttributeValue(PsiElement element) {
		int offset = element.getTextOffset();
		if (offset > 0) {
			FileViewProvider viewProvider = element.getContainingFile().getViewProvider();
			PsiElement previousElement = viewProvider.getPsi(HTMLLanguage.INSTANCE).findElementAt(offset - 1);
			if (previousElement != null) {
				return PsiTreeUtil.getParentOfType(previousElement, XmlAttributeValue.class);
			}
		}
		return null;
	}

}
