package co.nums.intellij.aem.extensions

import com.intellij.openapi.editor.Document
import com.intellij.util.text.CharArrayUtil

fun Document.hasText(offset: Int, text: String) = CharArrayUtil.regionMatches(this.charsSequence, offset, text)

fun Document.hasQuotesAt(offset: Int) = this.hasText(offset, "=\"") || this.hasText(offset, "='")

fun Document.removeText(start: Int, end: Int) = this.replaceString(start, end, "")
