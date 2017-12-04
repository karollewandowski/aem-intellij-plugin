package co.nums.intellij.aem.htl.highlighting

import co.nums.intellij.aem.htl.definitions.HTL_BLOCK_PREFIX
import co.nums.intellij.aem.htl.extensions.*
import com.intellij.lang.annotation.*
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlAttribute

class HtlBlocksHighlighter : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.containingFile.isHtl() && element is XmlAttribute && element.name.toLowerCase().startsWith(HTL_BLOCK_PREFIX)) {
            holder.highlightHtlBlock(element)
        }
    }

    private fun AnnotationHolder.highlightHtlBlock(element: XmlAttribute) {
        val blockStart = element.textOffset
        val blockName = element.localName
        val dotOffset = blockName.indexOf('.')
        val blockTypeEnd = if (dotOffset != -1) (blockStart + dotOffset) else (blockStart + blockName.length)
        this.highlightText(blockStart, blockTypeEnd, HtlHighlighterColors.BLOCK_TYPE)
        if (element.isHtlVariableDeclaration()) {
            this.highlightBlockIdentifier(blockName, blockStart, dotOffset, HtlHighlighterColors.VARIABLE)
        } else if (dotOffset != -1 && element.isHtlTemplateBlock()) {
            this.highlightBlockIdentifier(blockName, blockStart, dotOffset, HtlHighlighterColors.TEMPLATE_IDENTIFIER)
        }
    }

    private fun AnnotationHolder.highlightBlockIdentifier(blockName: String, blockStart: Int, dotOffset: Int, color: TextAttributesKey) {
        val variableIdentifier = blockName.substringAfter('.', missingDelimiterValue = "")
        if (variableIdentifier.isNotEmpty()) {
            val blockNameEnd = blockStart + blockName.length
            this.highlightText(blockStart + dotOffset + 1, blockNameEnd, color)
        }
    }

}
