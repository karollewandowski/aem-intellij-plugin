package co.nums.intellij.aem.htl.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.XmlHighlighterColors
import com.intellij.openapi.editor.colors.CodeInsightColors
import com.intellij.openapi.editor.colors.TextAttributesKey

object HtlHighlighterColors {

    val BLOCK_TYPE = TextAttributesKey.createTextAttributesKey("BLOCK_TYPE", XmlHighlighterColors.HTML_ATTRIBUTE_NAME)
    val BLOCK_VARIABLE = TextAttributesKey.createTextAttributesKey("BLOCK_VARIABLE", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val GLOBAL_OBJECT = TextAttributesKey.createTextAttributesKey("GLOBAL_OBJECT", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val UNREFERENCED_IDENTIFIER = TextAttributesKey.createTextAttributesKey("UNREFERENCED_IDENTIFIER", CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES)

}
