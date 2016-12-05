package co.nums.intellij.aem.htl.inspections;

import co.nums.intellij.aem.htl.psi.HtlTokenTypes;
import com.intellij.codeInsight.FileModificationService;
import com.intellij.codeInsight.intention.IntentionAction;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.project.Project;
import com.intellij.openapi.util.TextRange;
import com.intellij.psi.PsiDocumentManager;
import com.intellij.psi.PsiElement;
import com.intellij.psi.PsiFile;
import com.intellij.psi.impl.source.tree.LeafPsiElement;
import org.jetbrains.annotations.Nls;
import org.jetbrains.annotations.NotNull;

class HtlStringQuotesFix implements IntentionAction {

	private static final char DOUBLE_QUOTE = '"';
	private static final char SINGLE_QUOTE = '\'';
	private static final String NAME = "Fix quotes";

	private final char currentQuote;
	private final char quoteToInsert;

	HtlStringQuotesFix(char currentQuote) {
		this.currentQuote = currentQuote;
		this.quoteToInsert = currentQuote == DOUBLE_QUOTE ? SINGLE_QUOTE : DOUBLE_QUOTE;
	}

	@Nls
	@NotNull
	@Override
	public String getText() {
		return NAME;
	}

	@Nls
	@NotNull
	@Override
	public String getFamilyName() {
		return NAME;
	}

	@Override
	public boolean isAvailable(@NotNull Project project, Editor editor, PsiFile file) {
		return true;
	}

	@Override
	public boolean startInWriteAction() {
		return true;
	}

	@Override
	public void invoke(@NotNull Project project, Editor editor, PsiFile file) {
		PsiElement element = getCurrentElement(editor, file);
		if (isHtlString(element) && editionAllowed(element)) {
			fixQuotes(project, file, element);
		}
	}

	private static PsiElement getCurrentElement(Editor editor, PsiFile file) {
		int offset = editor.getCaretModel().getOffset();
		return file.findElementAt(offset);
	}

	private static boolean isHtlString(PsiElement element) {
		if (element instanceof LeafPsiElement) {
			LeafPsiElement leafElement = (LeafPsiElement) element;
			return leafElement.getElementType() == HtlTokenTypes.SINGLE_QUOTED_STRING
					|| leafElement.getElementType() == HtlTokenTypes.DOUBLE_QUOTED_STRING;
		}
		return false;
	}

	private static boolean editionAllowed(PsiElement element) {
		return element != null && element.isValid()
				&& FileModificationService.getInstance().prepareFileForWrite(element.getContainingFile());
	}

	private void fixQuotes(@NotNull Project project, PsiFile file, PsiElement element) {
		Document document = PsiDocumentManager.getInstance(project).getDocument(file);
		if (document != null) {
			String currentText = element.getText();
			String replacement = quoteToInsert + currentText.substring(1);
			if (endsWithUnescapedQuote(currentText, replacement)) {
				replacement = replacement.substring(0, replacement.length() - 1) + quoteToInsert;
			}
			TextRange textRange = element.getTextRange();
			document.replaceString(textRange.getStartOffset(), textRange.getEndOffset(), replacement);
		}
	}

	private boolean endsWithUnescapedQuote(String currentText, String replacement) {
		return currentText.length() > 1
				&& currentText.endsWith(Character.toString(currentQuote)) && !replacement.endsWith("\\" + currentQuote);
	}

}
