package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.psi.*
import com.intellij.lang.*
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.*
import com.intellij.psi.impl.source.PsiFileImpl
import com.intellij.psi.templateLanguages.*
import com.intellij.util.containers.ContainerUtil

class HtlFileViewProvider @JvmOverloads constructor(private val psiManager: PsiManager,
                                                    file: VirtualFile,
                                                    physical: Boolean,
                                                    private val baseLanguage: Language,
                                                    private val templateDataLanguage: Language = getTemplateDataLanguage(psiManager, file))
    : MultiplePsiFilesPerDocumentFileViewProvider(psiManager, file, physical), ConfigurableTemplateLanguageFileViewProvider {

    companion object {
        private val HTL_FRAGMENT = HtlElementType("HTL_FRAGMENT")
        private val TEMPLATE_DATA_TO_LANG = ContainerUtil.newConcurrentMap<String, TemplateDataElementType>()
    }

    override fun getBaseLanguage() = baseLanguage

    override fun getTemplateDataLanguage() = templateDataLanguage

    override fun getLanguages(): Set<Language> = setOf(baseLanguage, templateDataLanguage)

    override fun supportsIncrementalReparse(rootLanguage: Language) = false

    override fun cloneInner(virtualFile: VirtualFile) =
            HtlFileViewProvider(psiManager, virtualFile, false, baseLanguage, templateDataLanguage)

    override fun createFile(language: Language): PsiFile? {
        val parserDefinition = getParserDefinition(language)
        return when {
            language === templateDataLanguage -> {
                val file = parserDefinition.createFile(this) as PsiFileImpl
                file.contentElementType = getTemplateDataElementType(baseLanguage)
                file
            }
            language.isKindOf(baseLanguage) -> parserDefinition.createFile(this)
            else -> null
        }
    }

    private fun getParserDefinition(language: Language): ParserDefinition {
        val parserLanguage = when {
            language === baseLanguage -> language
            language.isKindOf(baseLanguage) -> baseLanguage
            else -> language
        }
        return LanguageParserDefinitions.INSTANCE.forLanguage(parserLanguage)
    }

    private fun getTemplateDataElementType(language: Language): TemplateDataElementType {
        val result = TEMPLATE_DATA_TO_LANG[language.id]
        if (result != null) {
            return result
        }
        val created = TemplateDataElementType("HTL_TEMPLATE_DATA", language, HtlTypes.OUTER_TEXT, HTL_FRAGMENT)
        val prevValue = TEMPLATE_DATA_TO_LANG.putIfAbsent(language.id, created)

        return prevValue ?: created
    }

}

private fun getTemplateDataLanguage(psiManager: PsiManager, file: VirtualFile): Language {
    var dataLanguage = TemplateDataLanguageMappings.getInstance(psiManager.project)?.getMapping(file)
            ?: HtlLanguage.getTemplateLanguageFileType().language

    val substituteLanguage = LanguageSubstitutors.INSTANCE.substituteLanguage(dataLanguage, file, psiManager.project)

    // only use a substituted language if it's templateable
    if (TemplateDataLanguageMappings.getTemplateableLanguages().contains(substituteLanguage)) {
        dataLanguage = substituteLanguage
    }
    return dataLanguage
}
