package co.nums.intellij.aem.htl.completion.provider.insertHandler

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.definitions.HtlGlobalObject
import co.nums.intellij.aem.htl.extensions.getSingularHtlForm
import co.nums.intellij.aem.htl.psi.*
import com.intellij.codeInsight.daemon.impl.quickfix.EmptyExpression
import com.intellij.codeInsight.lookup.LookupElementBuilder
import com.intellij.codeInsight.template.*
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.psi.codeStyle.NameUtil
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.XmlAttribute

object HtlListBlockInsertHandler : AbstractHtlLiveTemplateBlockInsertHandler() {

    /*
     * TODO (when references will be implemented)
     * make complete() return all iterable properties of objects available in current context, eg:
     * there is `model` use object with `products` property - in content assist add `model.products` at the top
     */
    override val template = TemplateImpl("", ".\$VAR_NAME\$=\"${'$'}{\$LIST_EXPRESSION\$}\"\$END\$", "").apply {
        addVariable("LIST_EXPRESSION", "complete()", "", true)
        addVariable("VAR_NAME", ListItemVariableNames(), EmptyExpression(), true)
    }

}

private class ListItemVariableNames : Expression() {

    override fun calculateQuickResult(context: ExpressionContext) = calculateResult(context)

    override fun calculateResult(context: ExpressionContext) = null

    override fun calculateLookupItems(context: ExpressionContext) =
            getListVariableNames(context)
                    ?.map { LookupElementBuilder.create(it) }
                    ?.toTypedArray()

    private fun getListVariableNames(context: ExpressionContext): List<String>? {
        val simple = getSimpleElement(context) ?: return null
        val lastPropertyAccess = getLastPropertyAccess(simple)
        val listItemNameBase =
                if (lastPropertyAccess != null) lastPropertyAccess.getPropertyName()
                else PsiTreeUtil.findChildOfType(simple, HtlVariable::class.java)?.text
        if (listItemNameBase.isNullOrBlank()) return null
        return NameUtil.getSuggestionsByName(listItemNameBase!!.getSingularHtlForm(), "", "", false, true, false)
                .filterNot { HtlGlobalObject.allIdentifiers.contains(it) }
    }

    private fun getSimpleElement(context: ExpressionContext): HtlSimple? {
        val xmlAttribute = context.psiElementAtStartOffset?.parent as? XmlAttribute ?: return null
        val blockValue = xmlAttribute.valueElement ?: return null
        val htlFile = blockValue.containingFile.viewProvider.getPsi(HtlLanguage) ?: return null
        val htlExpressionStart = htlFile.findElementAt(blockValue.textOffset) ?: return null
        val expressionNode = PsiTreeUtil.getNextSiblingOfType(htlExpressionStart, HtlExprNode::class.java) ?: return null
        return PsiTreeUtil.findChildOfType(expressionNode, HtlSimple::class.java)
    }

    private fun getLastPropertyAccess(simple: HtlSimple): HtlPropertyAccess? {
        var lastPropertyAccess = PsiTreeUtil.getNextSiblingOfType(simple, HtlPropertyAccess::class.java) ?: return null
        while (true) {
            val nextPropertyAccess = PsiTreeUtil.getNextSiblingOfType(lastPropertyAccess, HtlPropertyAccess::class.java)
            if (nextPropertyAccess != null) {
                lastPropertyAccess = nextPropertyAccess
            } else {
                break
            }
        }
        return lastPropertyAccess
    }

    private fun HtlPropertyAccess.getPropertyName() = when (this) {
        is HtlBracketPropertyAccess -> PsiTreeUtil.findChildOfType(this, HtlStringLiteral::class.java)?.stringContent?.text
        is HtlDotPropertyAccess -> this.text.substringAfter('.')
        else -> null
    }

}
