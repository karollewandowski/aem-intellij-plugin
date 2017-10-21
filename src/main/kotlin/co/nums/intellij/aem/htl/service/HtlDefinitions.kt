package co.nums.intellij.aem.htl.service

import co.nums.intellij.aem.htl.data.expressions.ExpressionOption
import co.nums.intellij.aem.utils.JsonReader

object HtlDefinitions {

    val expressionOptions = JsonReader.readJson<Array<ExpressionOption>>("definitions/htl-expression-options.json")

}
