package co.nums.intellij.aem.htl.highlighter

import com.intellij.openapi.editor.*
import com.intellij.openapi.editor.colors.*

object HtlHighlighterColors {

    val BLOCK_TYPE = TextAttributesKey.createTextAttributesKey("BLOCK_TYPE", XmlHighlighterColors.HTML_ATTRIBUTE_NAME)
    val BLOCK_VARIABLE = TextAttributesKey.createTextAttributesKey("BLOCK_VARIABLE", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
    val GLOBAL_OBJECT = TextAttributesKey.createTextAttributesKey("GLOBAL_OBJECT", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)
    val UNREFERENCED_IDENTIFIER = TextAttributesKey.createTextAttributesKey("UNREFERENCED_IDENTIFIER", CodeInsightColors.WRONG_REFERENCES_ATTRIBUTES)
    val USE_DECLARATION = TextAttributesKey.createTextAttributesKey("USE_DECLARATION", DefaultLanguageHighlighterColors.CLASS_NAME)
    val PROPERTY_ACCESS = TextAttributesKey.createTextAttributesKey("PROPERTY_ACCESS", DefaultLanguageHighlighterColors.INSTANCE_FIELD)
    val OPTION_NAME = TextAttributesKey.createTextAttributesKey("OPTION_NAME", DefaultLanguageHighlighterColors.LOCAL_VARIABLE)

}
