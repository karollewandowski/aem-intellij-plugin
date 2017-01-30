package co.nums.intellij.aem.htl.editor.actions

import co.nums.intellij.aem.htl.psi.HtlElementTypes
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler

class HtlQuoteHandler : SimpleTokenSetQuoteHandler(HtlElementTypes.SINGLE_QUOTED_STRING, HtlElementTypes.DOUBLE_QUOTED_STRING)
