package co.nums.intellij.aem.htl.extensions

import co.nums.intellij.aem.extensions.*
import co.nums.intellij.aem.htl.definitions.HtlGlobalObject

fun String.getSingularHtlForm(): String {
    val singularForm = this.getSingularForm()
    return if (HtlGlobalObject.allIdentifiers.contains(singularForm)) singularForm.prependIndefiniteArticle()
    else singularForm
}
