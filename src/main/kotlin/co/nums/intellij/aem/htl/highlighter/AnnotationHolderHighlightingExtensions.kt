package co.nums.intellij.aem.htl.highlighter

import com.intellij.lang.annotation.AnnotationHolder
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.util.TextRange

private val NO_MESSAGE = null

fun AnnotationHolder.highlightText(start: Int, end: Int, textAttributesKey: TextAttributesKey) {
    this.highlightText(TextRange(start, end), textAttributesKey)
}

fun AnnotationHolder.highlightText(textRange: TextRange, textAttributesKey: TextAttributesKey) {
    val annotation = this.createInfoAnnotation(textRange, NO_MESSAGE)
    annotation.textAttributes = textAttributesKey
}
