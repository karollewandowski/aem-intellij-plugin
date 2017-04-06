package co.nums.intellij.aem.htl.completion.provider.inserthandlers

import com.intellij.codeInsight.AutoPopupController
import com.intellij.codeInsight.completion.InsertionContext
import com.intellij.codeInsight.lookup.LookupElement

object HtlUseBlockInsertHandler : HtlValueBlockInsertHandler(insertionString = "=\"\"", insertionOffset = 2) { // FIXME: DRY (see HtlSimpleBlockInsertHandler)

    override fun handleInsert(context: InsertionContext, item: LookupElement) {
        super.handleInsert(context, item)
        AutoPopupController.getInstance(context.project).scheduleAutoPopup(context.editor)
    }

}
