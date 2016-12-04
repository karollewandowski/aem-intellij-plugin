package co.nums.intellij.aem.htl.completion.provider;

import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprOptionBracketsInsertHandler;
import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprOptionQuotesInsertHandler;
import co.nums.intellij.aem.htl.icons.HtlIcons;
import co.nums.intellij.aem.htl.psi.HtlTokenTypes;
import com.google.common.collect.Sets;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.tree.IElementType;

import java.util.Set;

/**
 * Provides HTL built-in expression options.
 */
public class HtlExprOptionsCompletionProvider extends UniqueIdentifiersProviderBase {

	private static final HtlExprOptionQuotesInsertHandler QUOTES_HANDLER = new HtlExprOptionQuotesInsertHandler();
	private static final HtlExprOptionQuotesInsertHandler AUTO_POPUP_QUOTES_HANDLER =
			new HtlExprOptionQuotesInsertHandler(true);
	private static final HtlExprOptionBracketsInsertHandler BRACKETS_HANDLER = new HtlExprOptionBracketsInsertHandler();

	private static final Set<LookupElement> EXPRESSION_OPTIONS_ELEMENTS = Sets.newHashSet(
			expressionOption("i18n"),
			expressionOption("format").withInsertHandler(BRACKETS_HANDLER),
			expressionOption("scheme").withInsertHandler(QUOTES_HANDLER),
			expressionOption("domain").withInsertHandler(QUOTES_HANDLER),
			expressionOption("locale").withInsertHandler(QUOTES_HANDLER),
			expressionOption("context").withInsertHandler(AUTO_POPUP_QUOTES_HANDLER),
			expressionOption("hint").withInsertHandler(QUOTES_HANDLER),
			expressionOption("join").withInsertHandler(QUOTES_HANDLER),
			expressionOption("path").withInsertHandler(QUOTES_HANDLER),
			expressionOption("prependPath").withInsertHandler(QUOTES_HANDLER),
			expressionOption("appendPath").withInsertHandler(QUOTES_HANDLER),
			expressionOption("selectors").withInsertHandler(QUOTES_HANDLER),
			expressionOption("addSelectors").withInsertHandler(QUOTES_HANDLER),
			expressionOption("removeSelectors").withInsertHandler(QUOTES_HANDLER),
			expressionOption("extension").withInsertHandler(QUOTES_HANDLER),
			expressionOption("suffix").withInsertHandler(QUOTES_HANDLER),
			expressionOption("prependSuffix").withInsertHandler(QUOTES_HANDLER),
			expressionOption("appendSuffix").withInsertHandler(QUOTES_HANDLER),
			expressionOption("query").withInsertHandler(QUOTES_HANDLER),
			expressionOption("addQuery").withInsertHandler(QUOTES_HANDLER),
			expressionOption("removeQuery").withInsertHandler(QUOTES_HANDLER),
			expressionOption("fragment").withInsertHandler(QUOTES_HANDLER)
	);

	private static LookupElementBuilder expressionOption(String name) {
		return LookupElementBuilder.create(name)
				.withIcon(HtlIcons.HTL_EXPRESSION_OPTION)
				.withTypeText("HTL expression option", true);
	}

	@Override
	Set<LookupElement> getCandidateLookupElements() {
		return EXPRESSION_OPTIONS_ELEMENTS;
	}

	@Override
	IElementType getIdentifiersContainerElementType() {
		return HtlTokenTypes.OPTION_LIST;
	}

	@Override
	IElementType getIdentifiedElementType() {
		return HtlTokenTypes.OPTION;
	}

}
