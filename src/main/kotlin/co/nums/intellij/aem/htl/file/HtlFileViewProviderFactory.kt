package co.nums.intellij.aem.htl.file

import co.nums.intellij.aem.htl.HtlLanguage
import com.intellij.lang.Language
import com.intellij.openapi.vfs.VirtualFile
import com.intellij.psi.FileViewProviderFactory
import com.intellij.psi.PsiManager

class HtlFileViewProviderFactory : FileViewProviderFactory {

    override fun createFileViewProvider(file: VirtualFile, language: Language, manager: PsiManager, eventSystemEnabled: Boolean) =
            if (language.isKindOf(HtlLanguage)) HtlFileViewProvider(manager, file, eventSystemEnabled, language)
            else throw AssertionError("HTL language expected")

}
