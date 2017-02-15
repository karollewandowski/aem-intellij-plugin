package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.psi.extensions.isHtlBlock
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlAttribute

class HtlBlocksHighlighter : Annotator {

    private val NO_MESSAGE = null

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element is XmlAttribute && element.isHtlBlock()) {
            val (blockName, blockOffset) = highlightBlockName(element, holder)
            if (blockName.contains('.') && blockName.substringAfter('.').isNotBlank()) {
                highlightBlockIdentifier(blockName, blockOffset, holder)
            }
        }
    }

    private fun highlightBlockName(element: XmlAttribute, holder: AnnotationHolder): Pair<String, Int> {
        val blockOffset = element.textOffset
        val blockName = element.localName
        val blockType = blockName.substringBefore('.')
        val blockTypeRange = TextRange(blockOffset, blockOffset + blockType.length)
        val blockTypeAnnotation = holder.createInfoAnnotation(blockTypeRange, NO_MESSAGE)
        blockTypeAnnotation.textAttributes = HtlHighlighterColors.BLOCK_NAME
        return Pair(blockName, blockOffset)
    }

    private fun highlightBlockIdentifier(blockName: String, blockOffset: Int, holder: AnnotationHolder) {
        val identifierStartOffset = blockOffset + blockName.indexOf('.') + 1
        val identifierEndOffset = blockOffset + blockName.length
        val identifierRange = TextRange(identifierStartOffset, identifierEndOffset)
        val annotation = holder.createInfoAnnotation(identifierRange, NO_MESSAGE)
        annotation.textAttributes = HtlHighlighterColors.BLOCK_VARIABLE
    }

}
