package co.nums.intellij.aem.htl.completion.provider.insertHandler

import com.intellij.codeInsight.completion.*
import com.intellij.codeInsight.lookup.LookupElement
import com.intellij.codeInsight.template.*

abstract class AbstractHtlLiveTemplateBlockInsertHandler : InsertHandler<LookupElement> {

    abstract val template: Template

    override fun handleInsert(context: InsertionContext, item: LookupElement?) {
        template.setToIndent(false)
        template.isToReformat = false
        template.isToShortenLongNames = false
        TemplateManager.getInstance(context.project).startTemplate(context.editor, template)
    }

}
