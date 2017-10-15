package co.nums.intellij.aem.htl.completion.provider.insertHandler

import co.nums.intellij.aem.htl.psi.extensions.getUseObjectType
import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.daemon.impl.quickfix.EmptyExpression
import com.intellij.codeInsight.lookup.*
import com.intellij.codeInsight.template.*
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.psi.codeStyle.NameUtil
import com.intellij.psi.xml.XmlAttribute

object HtlUseBlockInsertHandler : InsertHandler<LookupElement> {

    private val useBlockTemplate = TemplateImpl("", ".\$VAR_NAME\$=\"\$USE_TARGET\$\"\$END\$", "").apply {
        addVariable("USE_TARGET", "complete()", "", true)
        addVariable("VAR_NAME", UseVariableNames(), EmptyExpression(), true)
        isToIndent = false
        isToReformat = false
        isToShortenLongNames = false
    }

    override fun handleInsert(context: InsertionContext, item: LookupElement?) =
            TemplateManager.getInstance(context.project).startTemplate(context.editor, useBlockTemplate)

}

private class UseVariableNames : Expression() {

    override fun calculateQuickResult(context: ExpressionContext) = calculateResult(context)

    override fun calculateResult(context: ExpressionContext) = null

    override fun calculateLookupItems(context: ExpressionContext): Array<LookupElement>? {
        return getUseVariableNames(context)
                ?.map { LookupElementBuilder.create(it) }
                ?.toTypedArray()
    }

    private fun getUseVariableNames(context: ExpressionContext): List<String>? {
        val useObjectNameBase = getUseObjectNameBase(context) ?: return null
        return NameUtil.getSuggestionsByName(useObjectNameBase, "", "", false, true, false).toList()
    }

    private fun getUseObjectNameBase(context: ExpressionContext): String? {
        val xmlAttribute = context.psiElementAtStartOffset?.parent as? XmlAttribute ?: return null
        val useObjectType = xmlAttribute.getUseObjectType() ?: return null
        return when {
            useObjectType.hasExtension("html") -> useObjectType.getUseName()
            useObjectType.hasExtension("js") -> useObjectType.getUseName()
            useObjectType.contains('/') -> return null // if contains '/' and is not HTML/JS, then it's incorrect
            else -> useObjectType.substringAfterLast('.')
        }
    }

    private fun String.hasExtension(extension: String) = endsWith(".$extension", ignoreCase = true)
    private fun String.getUseName() = substringAfterLast('/').substringBeforeLast('.')

}
