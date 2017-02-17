package co.nums.intellij.aem.htl.highlighter

import co.nums.intellij.aem.htl.HtlBlocks
import co.nums.intellij.aem.htl.psi.extensions.isHtl
import co.nums.intellij.aem.htl.psi.extensions.isHtlBlock
import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.lang.annotation.Annotator
import com.intellij.psi.PsiElement
import com.intellij.psi.xml.XmlAttribute

class HtlBlocksHighlighter : Annotator {

    override fun annotate(element: PsiElement, holder: AnnotationHolder) {
        if (element.containingFile.isHtl() && element is XmlAttribute && element.isHtlBlock()) {
            val blockName = element.localName
            if (blockName.contains('.')) {
                highlightIdentifiedBlock(blockName, element, holder)
            } else {
                holder.highlightText(element.nameElement.textRange, HtlHighlighterColors.BLOCK_TYPE)
            }
        }
    }

    private fun highlightIdentifiedBlock(blockName: String, element: XmlAttribute, holder: AnnotationHolder) {
        val blockStart = element.textOffset
        val dotOffset = blockStart + blockName.indexOf('.')
        val blockNameEnd = blockStart + blockName.length
        holder.highlightText(blockStart, dotOffset, HtlHighlighterColors.BLOCK_TYPE)
        if (HtlBlocks.isVariableBlock(blockName.substringBefore('.'))) {
            holder.highlightText(dotOffset + 1, blockNameEnd, HtlHighlighterColors.BLOCK_VARIABLE)
        }
    }

}
