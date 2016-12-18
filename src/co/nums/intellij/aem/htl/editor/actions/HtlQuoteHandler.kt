package co.nums.intellij.aem.htl.editor.actions

import co.nums.intellij.aem.htl.psi.HtlTokenTypes
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler

class HtlQuoteHandler : SimpleTokenSetQuoteHandler(HtlTokenTypes.SINGLE_QUOTED_STRING, HtlTokenTypes.DOUBLE_QUOTED_STRING)
