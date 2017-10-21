package co.nums.intellij.aem.htl.service

import co.nums.intellij.aem.htl.data.expressions.*
import co.nums.intellij.aem.utils.JsonReader

object HtlDefinitions {

    val displayContexts = JsonReader.readJson<Array<DisplayContext>>("definitions/htl-display-contexts.json")
    val expressionOptions = JsonReader.readJson<Array<ExpressionOption>>("definitions/htl-expression-options.json")

}
