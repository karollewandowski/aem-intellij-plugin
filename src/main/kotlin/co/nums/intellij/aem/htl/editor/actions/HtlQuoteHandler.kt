package co.nums.intellij.aem.htl.editor.actions

import co.nums.intellij.aem.htl.psi.HtlTypes
import com.intellij.codeInsight.editorActions.SimpleTokenSetQuoteHandler

class HtlQuoteHandler : SimpleTokenSetQuoteHandler(HtlTypes.SINGLE_QUOTE, HtlTypes.DOUBLE_QUOTE)
