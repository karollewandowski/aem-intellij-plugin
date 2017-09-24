package co.nums.intellij.aem.htl.editor.comments

import com.intellij.codeInsight.generation.EscapingCommenter
import com.intellij.openapi.editor.*
import com.intellij.util.text.CharArrayUtil


private const val CLOSING_DOUBLE_DASH = "*/--"
private const val ESCAPED_CLOSING_DOUBLE_DASH = "*/&#45;&#45;"
private const val GT = ">"
private const val ESCAPED_GT = "&gt;"

class HtlCommenter : EscapingCommenter {

    /*
     * Code below is copied from XmlCommenter and adjusted to prevent from escaping
     * double dash when block comment's end is inserted at the beginning of line.
     * If there is another simple solution, implement it.
     */

    override fun getBlockCommentPrefix() = "<!--/*"

    override fun getBlockCommentSuffix() = "*/-->"

    override fun getCommentedBlockCommentPrefix() = "&lt;!&ndash;/*"

    override fun getCommentedBlockCommentSuffix() = "*/&ndash;&gt;"

    override fun getLineCommentPrefix() = null

    override fun escape(document: Document, range: RangeMarker) {
        val prefix = blockCommentPrefix
        val suffix = blockCommentSuffix

        var start = range.startOffset
        start = CharArrayUtil.shiftForward(document.charsSequence, start, " \t\n")
        val prefixStart = start
        if (CharArrayUtil.regionMatches(document.charsSequence, prefixStart, prefix)) {
            start += prefix.length
        }
        var end = range.endOffset
        if (CharArrayUtil.regionMatches(document.charsSequence, end - suffix.length, suffix)) {
            end -= suffix.length
        }
        if (start >= end) return

        for (i in end - CLOSING_DOUBLE_DASH.length downTo start) {
            if (CharArrayUtil.regionMatches(document.charsSequence, i, CLOSING_DOUBLE_DASH) &&
                    !CharArrayUtil.regionMatches(document.charsSequence, i, suffix) &&
                    !CharArrayUtil.regionMatches(document.charsSequence, i - 2, prefix)) {
                document.replaceString(i, i + CLOSING_DOUBLE_DASH.length, ESCAPED_CLOSING_DOUBLE_DASH)
            }
        }
        if (CharArrayUtil.regionMatches(document.charsSequence, start, GT)) {
            document.replaceString(start, start + GT.length, ESCAPED_GT)
        }
        if (CharArrayUtil.regionMatches(document.charsSequence, prefixStart, prefix + "-")) {
            document.insertString(start, " ")
        }
        if (CharArrayUtil.regionMatches(document.charsSequence, range.endOffset - suffix.length - 1, "-" + suffix)) {
            document.insertString(range.endOffset - suffix.length, " ")
        }
    }

    override fun unescape(document: Document, range: RangeMarker) {
        val start = range.startOffset
        for (i in range.endOffset downTo start) {
            if (CharArrayUtil.regionMatches(document.charsSequence, i, ESCAPED_CLOSING_DOUBLE_DASH)) {
                document.replaceString(i, i + ESCAPED_CLOSING_DOUBLE_DASH.length, CLOSING_DOUBLE_DASH)
            }
        }
        if (CharArrayUtil.regionMatches(document.charsSequence, start, ESCAPED_GT)) {
            document.replaceString(start, start + ESCAPED_GT.length, GT)
        }
    }

}
