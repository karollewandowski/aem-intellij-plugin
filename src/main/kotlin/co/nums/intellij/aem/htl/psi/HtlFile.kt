package co.nums.intellij.aem.htl.psi

import co.nums.intellij.aem.htl.HtlLanguage
import co.nums.intellij.aem.htl.file.HtlFileType
import com.intellij.extapi.psi.PsiFileBase
import com.intellij.lang.Language
import com.intellij.psi.FileViewProvider

class HtlFile constructor(viewProvider: FileViewProvider, lang: Language = HtlLanguage) : PsiFileBase(viewProvider, lang) {

    override fun getFileType() = HtlFileType

    override fun toString() = "HTL File: $name"

}
