package co.nums.intellij.aem.htl.highlighter

import com.intellij.openapi.editor.DefaultLanguageHighlighterColors
import com.intellij.openapi.editor.XmlHighlighterColors
import com.intellij.openapi.editor.colors.TextAttributesKey

object HtlHighlighterColors {

    val BLOCK_NAME = TextAttributesKey.createTextAttributesKey("BLOCK_NAME", XmlHighlighterColors.HTML_ATTRIBUTE_NAME)
    val BLOCK_VARIABLE = TextAttributesKey.createTextAttributesKey("BLOCK_VARIABLE", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val GLOBAL_VARIABLE = TextAttributesKey.createTextAttributesKey("GLOBAL_VARIABLE", DefaultLanguageHighlighterColors.INSTANCE_FIELD)

}
