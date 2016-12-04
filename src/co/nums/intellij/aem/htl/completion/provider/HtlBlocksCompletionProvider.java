package co.nums.intellij.aem.htl.completion.provider;

import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlExprBlockInsertHandler;
import co.nums.intellij.aem.htl.completion.provider.inserthandlers.HtlSimpleBlockInsertHandler;
import co.nums.intellij.aem.htl.icons.HtlIcons;
import com.google.common.collect.Sets;
import com.intellij.codeInsight.lookup.LookupElement;
import com.intellij.codeInsight.lookup.LookupElementBuilder;
import com.intellij.psi.tree.IElementType;
import com.intellij.psi.xml.XmlElementType;

import java.util.Set;

/**
 * Provides HTL blocks (HTML data-sly-* attributes).
 */
public class HtlBlocksCompletionProvider extends UniqueIdentifiersProviderBase {

	private static final HtlSimpleBlockInsertHandler SIMPLE_QUOTES_HANDLER = new HtlSimpleBlockInsertHandler();
	private static final HtlExprBlockInsertHandler EXPRESSION_HANDLER = new HtlExprBlockInsertHandler();

	private static final Set<LookupElement> BLOCKS_ELEMENTS = Sets.newHashSet(
			block("data-sly-attribute").withInsertHandler(EXPRESSION_HANDLER),
			block("data-sly-call").withInsertHandler(EXPRESSION_HANDLER),
			block("data-sly-element").withInsertHandler(EXPRESSION_HANDLER),
			block("data-sly-include").withInsertHandler(SIMPLE_QUOTES_HANDLER),
			block("data-sly-list").withInsertHandler(EXPRESSION_HANDLER),
			block("data-sly-repeat").withInsertHandler(EXPRESSION_HANDLER),
			block("data-sly-resource").withInsertHandler(SIMPLE_QUOTES_HANDLER),
			block("data-sly-template"),
			block("data-sly-test").withInsertHandler(EXPRESSION_HANDLER),
			block("data-sly-text").withInsertHandler(EXPRESSION_HANDLER),
			block("data-sly-unwrap"),
			block("data-sly-use").withInsertHandler(SIMPLE_QUOTES_HANDLER)
	);

	private static LookupElementBuilder block(String name) {
		return LookupElementBuilder.create(name)
				.withBoldness(true)
				.withIcon(HtlIcons.HTL_BLOCK)
				.withTypeText("HTL block", true);
	}

	@Override
	Set<LookupElement> getCandidateLookupElements() {
		return BLOCKS_ELEMENTS;
	}

	@Override
	IElementType getIdentifiersContainerElementType() {
		return XmlElementType.HTML_TAG;
	}

	@Override
	IElementType getIdentifiedElementType() {
		return XmlElementType.XML_ATTRIBUTE;
	}

}
