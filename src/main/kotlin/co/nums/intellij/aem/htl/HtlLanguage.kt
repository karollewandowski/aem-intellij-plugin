package co.nums.intellij.aem.htl

import com.intellij.lang.Language
import com.intellij.openapi.fileTypes.LanguageFileType
import com.intellij.openapi.fileTypes.StdFileTypes
import com.intellij.psi.templateLanguages.TemplateLanguage

object HtlLanguage : Language("HTL"), TemplateLanguage {

    fun getTemplateLanguageFileType(): LanguageFileType = StdFileTypes.HTML

}
