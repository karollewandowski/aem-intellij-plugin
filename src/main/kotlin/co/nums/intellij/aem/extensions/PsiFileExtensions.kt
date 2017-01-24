package co.nums.intellij.aem.extensions

import co.nums.intellij.aem.htl.HtlLanguage
import com.intellij.psi.PsiFile

fun PsiFile.isHtl() = this.viewProvider.baseLanguage.isKindOf(HtlLanguage)
