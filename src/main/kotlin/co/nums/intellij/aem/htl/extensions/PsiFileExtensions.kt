package co.nums.intellij.aem.htl.extensions

import co.nums.intellij.aem.htl.HtlLanguage
import com.intellij.lang.html.HTMLLanguage
import com.intellij.psi.PsiFile

fun PsiFile.isHtl() = viewProvider.baseLanguage.isKindOf(HtlLanguage)

fun PsiFile.asHtmlFile(): PsiFile? = viewProvider.getPsi(HTMLLanguage.INSTANCE)
