package co.nums.intellij.aem.htl.completion.provider.inserthandlers;

import co.nums.intellij.aem.htl.psi.impl.HtlPsiUtil;
import com.intellij.codeInsight.AutoPopupController;
import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.psi.FileViewProvider;
import com.intellij.psi.PsiElement;
import com.intellij.util.text.CharArrayUtil;
import org.jetbrains.annotations.NotNull;

public class HtlExprOptionQuotesInsertHandler implements InsertHandler<LookupElement> {

	private final boolean showAutoPopup;

	public HtlExprOptionQuotesInsertHandler() {
		this(false);
	}

	public HtlExprOptionQuotesInsertHandler(boolean showAutoPopup) {
		this.showAutoPopup = showAutoPopup;
	}

	@Override
	public void handleInsert(InsertionContext context, LookupElement item) {
		Editor editor = context.getEditor();
		Document document = editor.getDocument();
		int caretOffset = editor.getCaretModel().getOffset();
		if (!hasQuotes(document, caretOffset)) {
			handleQuotes(context, editor, document, caretOffset);
		}
	}

	private static boolean hasQuotes(Document document, int caretOffset) {
		CharSequence chars = document.getCharsSequence();
		return CharArrayUtil.regionMatches(chars, caretOffset, "=\"")
				|| CharArrayUtil.regionMatches(chars, caretOffset, "='");
	}

	private void handleQuotes(InsertionContext context, Editor editor, Document document, int caretOffset) {
		insertQuotes(context, document, caretOffset);
		moveCaretIntoQuotes(editor, caretOffset);
		handleAutoPopup(context, editor);
	}

	private static void insertQuotes(InsertionContext context, Document document, int caretOffset) {
		String insertedQuote = getQuoteToInsert(context, caretOffset);
		document.insertString(caretOffset, "=" + insertedQuote + insertedQuote);
	}

	@NotNull
	private static String getQuoteToInsert(InsertionContext context, int caretOffset) {
		FileViewProvider viewProvider = context.getFile().getViewProvider();
		PsiElement currentElement = viewProvider.findElementAt(caretOffset);
		String enclosingQuote = HtlPsiUtil.getEnclosingHtmlAttributeQuote(currentElement);
		if ("'".equals(enclosingQuote)) {
			return  "\"";
		}
		return "'";
	}

	private static void moveCaretIntoQuotes(Editor editor, int caretOffset) {
		editor.getCaretModel().moveToOffset(caretOffset + 2);
		editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
		editor.getSelectionModel().removeSelection();
	}

	private void handleAutoPopup(InsertionContext context, Editor editor) {
		if (showAutoPopup) {
			AutoPopupController.getInstance(context.getProject()).scheduleAutoPopup(editor);
		}
	}

}
