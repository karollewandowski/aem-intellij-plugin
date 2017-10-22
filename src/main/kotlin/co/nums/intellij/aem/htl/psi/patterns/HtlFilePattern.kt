package co.nums.intellij.aem.htl.psi.patterns

import co.nums.intellij.aem.htl.psi.extensions.isHtl
import com.intellij.patterns.*
import com.intellij.patterns.PlatformPatterns.psiFile
import com.intellij.psi.PsiFile
import com.intellij.util.ProcessingContext

class HtlFilePattern : PatternCondition<PsiFile?>("HTL file") {

    override fun accepts(file: PsiFile, context: ProcessingContext?): Boolean {
        return file.isHtl()
    }

    companion object {
        fun htlFile(): PsiFilePattern.Capture<PsiFile> = psiFile().with(HtlFilePattern())
    }

}
