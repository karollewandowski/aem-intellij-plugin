package co.nums.intellij.aem.htl.highlighting

import com.intellij.lang.annotation.*
import com.intellij.lang.annotation.Annotation
import com.intellij.openapi.editor.colors.TextAttributesKey
import com.intellij.openapi.util.TextRange
import com.intellij.psi.PsiElement

private val noMessage = null

fun AnnotationHolder.highlightText(start: Int, end: Int, textAttributesKey: TextAttributesKey) {
    highlightText(TextRange(start, end), textAttributesKey)
}

fun AnnotationHolder.highlightText(element: PsiElement, textAttributesKey: TextAttributesKey) {
    highlightText(element.textRange, textAttributesKey)
}

fun AnnotationHolder.highlightText(textRange: TextRange, textAttributesKey: TextAttributesKey) {
    val annotation = this.createInfoAnnotation(textRange, noMessage)
    annotation.textAttributes = textAttributesKey
}

fun AnnotationHolder.createReferenceErrorAnnotation(start: Int, end: Int, errorMessage: String) =
        createReferenceErrorAnnotation(TextRange(start, end), errorMessage)

fun AnnotationHolder.createReferenceErrorAnnotation(element: PsiElement, errorMessage: String) =
        createReferenceErrorAnnotation(element.textRange, errorMessage)

fun AnnotationHolder.createReferenceErrorAnnotation(textRange: TextRange, errorMessage: String): Annotation {
    val errorAnnotation = this.createErrorAnnotation(textRange, errorMessage)
    errorAnnotation.textAttributes = HtlHighlighterColors.UNREFERENCED_IDENTIFIER
    return errorAnnotation
}
