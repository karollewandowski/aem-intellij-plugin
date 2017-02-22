package co.nums.intellij.aem.htl.editor.comments

import com.intellij.lang.xml.XmlCommenter
import com.intellij.openapi.editor.Document
import com.intellij.openapi.editor.RangeMarker
import com.intellij.util.text.CharArrayUtil

class HtlCommenter : XmlCommenter() {

    override fun getBlockCommentPrefix() = "<!--/*"

    override fun getBlockCommentSuffix() = "*/-->"

    /*
     * Code below is copied from XmlCommenter and adjusted to prevent from escaping
     * double dash when block comment's end is inserted at the beginning of line.
     * If there is another simple solution, implement it.
     */
    private val DOUBLE_DASH = "*/--" // adjusted from "--"
    private val ESCAPED_DOUBLE_DASH = "*/&#45;&#45;" // adjusted from "&#45;&#45;"
    private val GT = ">"
    private val ESCAPED_GT = "&gt;"

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

        for (i in end - DOUBLE_DASH.length downTo start) {
            if (CharArrayUtil.regionMatches(document.charsSequence, i, DOUBLE_DASH) &&
                    !CharArrayUtil.regionMatches(document.charsSequence, i, suffix) &&
                    !CharArrayUtil.regionMatches(document.charsSequence, i - 2, prefix)) {
                document.replaceString(i, i + DOUBLE_DASH.length, ESCAPED_DOUBLE_DASH)
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

}
