package co.nums.intellij.aem.htl.service

import co.nums.intellij.aem.htl.data.expressions.*
import co.nums.intellij.aem.htl.data.globalobjects.*
import co.nums.intellij.aem.utils.JsonReader

object HtlDefinitions {

    val displayContexts = JsonReader.readJson<Array<DisplayContext>>("definitions/htl-display-contexts.json")
    val expressionOptions = JsonReader.readJson<Array<ExpressionOption>>("definitions/htl-expression-options.json")
    val globalObjects = JsonReader.readJson<Array<GlobalObject>>("definitions/htl-global-objects.json")
    val predefinedProperties = JsonReader.readJson<Array<PredefinedProperty>>("definitions/htl-predefined-properties.json")
    val globalPropertyObjectsNames = setOf("properties", "pageProperties", "inheritedPageProperties")

}
