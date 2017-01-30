package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.psi.HtlElementType
import co.nums.intellij.aem.htl.psi.HtlElementTypes
import com.intellij.lang.Language
import com.intellij.lang.LanguageParserDefinitions
import com.intellij.lang.ParserDefinition
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.LanguageSubstitutors
import com.intellij.psi.MultiplePsiFilesPerDocumentFileViewProvider
import com.intellij.psi.PsiFile
import com.intellij.psi.PsiManager
import com.intellij.psi.impl.source.PsiFileImpl
import com.intellij.psi.templateLanguages.ConfigurableTemplateLanguageFileViewProvider
import com.intellij.psi.templateLanguages.TemplateDataElementType
import com.intellij.psi.templateLanguages.TemplateDataLanguageMappings
import com.intellij.util.containers.ContainerUtil

// TODO: clean it
class HtlFileViewProvider @JvmOverloads constructor(manager: PsiManager, file: VirtualFile, physical: Boolean, private val baseLanguage: Language,
                                                    private val templateDataLanguage: Language = HtlFileViewProvider.getTemplateDataLanguage(manager, file)) : MultiplePsiFilesPerDocumentFileViewProvider(manager, file, physical), ConfigurableTemplateLanguageFileViewProvider {

    override fun getBaseLanguage() = baseLanguage

    override fun getTemplateDataLanguage() = templateDataLanguage

    override fun getLanguages(): Set<Language> = hashSetOf(baseLanguage, templateDataLanguage)

    override fun supportsIncrementalReparse(rootLanguage: Language) = false

    override fun cloneInner(virtualFile: VirtualFile) =
            HtlFileViewProvider(manager, virtualFile, false, baseLanguage, templateDataLanguage)

    override fun createFile(language: Language): PsiFile? {
        val parserDefinition = getParserDefinition(language)
        if (language.`is`(templateDataLanguage)) {
            val file = parserDefinition.createFile(this) as PsiFileImpl
            file.contentElementType = getTemplateDataElementType(getBaseLanguage())
            return file
        } else if (language.isKindOf(getBaseLanguage())) {
            return parserDefinition.createFile(this)
        } else {
            return null
        }
    }

    private fun getParserDefinition(language: Language): ParserDefinition {
        val parserDefinition: ParserDefinition
        if (language.isKindOf(getBaseLanguage())) {
            parserDefinition = LanguageParserDefinitions.INSTANCE.forLanguage(
                    if (language.`is`(getBaseLanguage())) language else getBaseLanguage())
        } else {
            parserDefinition = LanguageParserDefinitions.INSTANCE.forLanguage(language)
        }
        return parserDefinition
    }

    companion object {
        private val HTL_FRAGMENT = HtlElementType("HTL_FRAGMENT")
        private val TEMPLATE_DATA_TO_LANG = ContainerUtil.newConcurrentMap<String, TemplateDataElementType>()

        private fun getTemplateDataLanguage(manager: PsiManager, file: VirtualFile): Language {
            var dataLanguage = TemplateDataLanguageMappings.getInstance(manager.project).getMapping(file)
                    ?: HtlLanguage.getTemplateLanguageFileType().language

            val substituteLanguage = LanguageSubstitutors.INSTANCE.substituteLanguage(dataLanguage, file, manager.project)

            // only use a substituted language if it's templateable
            if (TemplateDataLanguageMappings.getTemplateableLanguages().contains(substituteLanguage)) {
                dataLanguage = substituteLanguage
            }
            return dataLanguage
        }

        private fun getTemplateDataElementType(lang: Language): TemplateDataElementType {
            val result = TEMPLATE_DATA_TO_LANG[lang.id]
            if (result != null) {
                return result
            }
            val created = TemplateDataElementType("HTL_TEMPLATE_DATA", lang, HtlElementTypes.HTML_FRAGMENT, HTL_FRAGMENT)
            val prevValue = TEMPLATE_DATA_TO_LANG.putIfAbsent(lang.id, created)

            return prevValue ?: created
        }
    }

}
