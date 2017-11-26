package co.nums.intellij.aem.htl.completion.provider.insertHandler

import co.nums.intellij.aem.htl.extensions.getUseObjectType
import com.intellij.codeInsight.daemon.impl.quickfix.EmptyExpression
import com.intellij.codeInsight.lookup.*
import com.intellij.codeInsight.template.*
import com.intellij.codeInsight.template.impl.TemplateImpl
import com.intellij.psi.codeStyle.NameUtil
import com.intellij.psi.xml.XmlAttribute
import org.apache.commons.lang.WordUtils

object HtlUseBlockInsertHandler : AbstractHtlLiveTemplateBlockInsertHandler() {

    override val template = TemplateImpl("", ".\$VAR_NAME\$=\"\$USE_TARGET\$\"\$END\$", "").apply {
        addVariable("USE_TARGET", "complete()", "", true)
        addVariable("VAR_NAME", UseVariableNames(), EmptyExpression(), true)
    }

}

private class UseVariableNames : Expression() {

    private val commonUseClassesSuffixes = arrayOf("Component", "Model", "Use")
    private val commonUseClassesSuffixesLowerCase = commonUseClassesSuffixes.map { it.toLowerCase() }.toHashSet()

    override fun calculateQuickResult(context: ExpressionContext) = calculateResult(context)

    override fun calculateResult(context: ExpressionContext) = null

    override fun calculateLookupItems(context: ExpressionContext): Array<LookupElement>? {
        return getUseVariableNames(context)
                ?.map { LookupElementBuilder.create(it) }
                ?.toTypedArray()
    }

    private fun getUseVariableNames(context: ExpressionContext): List<String>? {
        val useObjectNameBase = getUseObjectNameBase(context) ?: return emptyList()
        val nameSuggestions = NameUtil.getSuggestionsByName(useObjectNameBase, "", "", false, true, false)
                .filterNot { commonUseClassesSuffixesLowerCase.contains(it) }
                .toList()
        val nameSuggestionsWithoutCommonSuffix = nameSuggestions.withoutCommonSuffix()
        return nameSuggestionsWithoutCommonSuffix + nameSuggestions
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

    private fun String.getUseName(): String {
        val userFileName = substringAfterLast('/').substringBeforeLast('.')
        return WordUtils.capitalizeFully(userFileName, charArrayOf('-', '_'))
                .replace("-", "")
                .replace("_", "")
    }

    private fun List<String>?.withoutCommonSuffix(): List<String> {
        this ?: return emptyList()
        return this.mapNotNull { it.withoutSuffixOrNull() }
    }

    private fun String.withoutSuffixOrNull(): String? {
        return commonUseClassesSuffixes
                .firstOrNull { this.endsWith(it) }
                ?.let { this.removeSuffix(it) }
    }

}
