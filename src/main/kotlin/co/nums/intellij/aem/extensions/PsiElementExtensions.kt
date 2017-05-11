package co.nums.intellij.aem.extensions

import com.intellij.codeInsight.FileModificationService
import com.intellij.psi.PsiElement

fun PsiElement.canBeEdited() =
        isValid && FileModificationService.getInstance().prepareFileForWrite(containingFile)
