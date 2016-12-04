package co.nums.intellij.aem.htl.completion.provider.inserthandlers;

import com.intellij.codeInsight.completion.InsertHandler;
import com.intellij.codeInsight.completion.InsertionContext;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.openapi.editor.Document;
import com.intellij.openapi.editor.Editor;
import com.intellij.openapi.editor.ScrollType;
import com.intellij.util.text.CharArrayUtil;

public class HtlExprOptionBracketsInsertHandler implements InsertHandler<LookupElement> {

	@Override
	public void handleInsert(InsertionContext context, LookupElement item) {
		Editor editor = context.getEditor();
		Document document = editor.getDocument();
		int caretOffset = editor.getCaretModel().getOffset();
		if (!hasBrackets(document, caretOffset)) {
			insertBrackets(context, document, caretOffset);
			moveCaretIntoBrackets(editor, caretOffset);
		}
	}

	private static boolean hasBrackets(Document document, int caretOffset) {
		CharSequence chars = document.getCharsSequence();
		return CharArrayUtil.regionMatches(chars, caretOffset, "=[");
	}

	private static void insertBrackets(InsertionContext context, Document document, int caretOffset) {
		document.insertString(caretOffset, "=[]");
		if (context.getCompletionChar() == '=') {
			context.setAddCompletionChar(false); // IDEA-19449
		}
	}

	private static void moveCaretIntoBrackets(Editor editor, int caretOffset) {
		editor.getCaretModel().moveToOffset(caretOffset + 2);
		editor.getScrollingModel().scrollToCaret(ScrollType.RELATIVE);
		editor.getSelectionModel().removeSelection();
	}

}
