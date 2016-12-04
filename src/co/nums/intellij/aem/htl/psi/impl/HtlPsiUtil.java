package co.nums.intellij.aem.htl.psi.impl;

import co.nums.intellij.aem.htl.psi.HtlExpression;
import com.intellij.lang.html.HTMLLanguage;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.psi.util.PsiTreeUtil;
import com.intellij.psi.xml.XmlAttributeValue;
import org.jetbrains.annotations.Nullable;

public final class HtlPsiUtil {

	private HtlPsiUtil() {
		// no instances
	}

	@Nullable
	public static String getEnclosingHtmlAttributeQuote(PsiElement element) {
		HtlExpression expression = PsiTreeUtil.getParentOfType(element, HtlExpression.class);
		if (expression != null) {
			XmlAttributeValue xmlAttributeValue = getEnclosingXmlAttributeValue(expression);
			if (xmlAttributeValue != null) {
				return Character.toString(xmlAttributeValue.getText().charAt(0));
			}
		}
		return null;
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
