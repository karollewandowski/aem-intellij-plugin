package co.nums.intellij.aem.extensions

import com.intellij.openapi.editor.Editor
import com.intellij.openapi.editor.ScrollType

fun Editor.moveCaret(offset: Int) {
    val position = this.caretModel.offset
    this.caretModel.moveToOffset(position + offset)
    this.scrollingModel.scrollToCaret(ScrollType.RELATIVE)
    this.selectionModel.removeSelection()
}
