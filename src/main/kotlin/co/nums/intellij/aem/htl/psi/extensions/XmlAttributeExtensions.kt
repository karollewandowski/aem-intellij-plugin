package co.nums.intellij.aem.htl.psi.extensions

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.definitions.HtlBlock
import com.intellij.psi.util.PsiTreeUtil
import com.intellij.psi.xml.*

private val htlBlockTypes = HtlBlock.values().map { it.type }
private val htlVariableBlockTypes = HtlBlock.values().filter { it.identifierType.isVariable() }.map { it.type }
private val htlImplicitVariableBlockTypes = HtlBlock.values().filter { it.iterable }.map { it.type }

fun XmlAttribute.isHtlBlock() = (firstChild as? XmlToken)?.isHtlBlock() ?: false

fun XmlAttribute.isHtlVariableBlock(): Boolean {
    val blockType = (firstChild as? XmlToken)?.text?.substringBefore(".")?.toLowerCase()
    return htlVariableBlockTypes.contains(blockType)
}

fun XmlAttribute.isHtlVariableDeclaration(): Boolean {
    val blockName = (firstChild as? XmlToken)?.text ?: return false
    val blockType = blockName.substringBefore(".").toLowerCase()
    return htlVariableBlockTypes.contains(blockType)
            && (htlImplicitVariableBlockTypes.contains(blockType) || blockHasIdentifier(blockName))
}

private fun blockHasIdentifier(blockName: String): Boolean {
    val blockIdentifier = blockName.substringAfter('.', missingDelimiterValue = "")
    return blockIdentifier.isNotEmpty()
}

fun XmlToken.isHtlBlock() = htlBlockTypes.contains(text.substringBefore(".").toLowerCase())

/**
 * Returns declared use object type from XML attribute or from HTL expression in XML attribute.
 * Returns `null` if type cannot be retrieved. Method does not check whether this XML attribute
 * is actually `data-sly-use`.
 *
 * @return use object type or `null`
 */
fun XmlAttribute.getUseObjectType(): String? {
    var useObjectType: String
    val blockValue = this.valueElement ?: return null
    val htlFile = blockValue.containingFile.viewProvider.getPsi(HtlLanguage) ?: return null
    val htlExpressionStart = htlFile.findElementAt(blockValue.textOffset) ?: return null
    if (htlExpressionStart.isHtlExpressionToken()) {
        val nextToken = PsiTreeUtil.nextVisibleLeaf(htlExpressionStart) ?: return null
        useObjectType = if (nextToken.isPartOfHtlString()) nextToken.text else ""
    } else {
        useObjectType = blockValue.text
    }
    useObjectType = useObjectType.trim('"', '\'', ' ')
    if (useObjectType.isBlank()) {
        return null
    }
    return useObjectType
}
