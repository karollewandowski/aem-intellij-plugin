package co.nums.intellij.aem.htl.service

import co.nums.intellij.aem.htl.data.blocks.Block
import co.nums.intellij.aem.htl.data.expressions.DisplayContext
import co.nums.intellij.aem.htl.data.expressions.ExpressionOption
import co.nums.intellij.aem.htl.data.expressions.ListProperty
import co.nums.intellij.aem.htl.data.globalobjects.GlobalObject
import co.nums.intellij.aem.htl.data.globalobjects.PredefinedProperty
import co.nums.intellij.aem.utils.JsonReader

object HtlDefinitions {

    val blocks = JsonReader.readJson<Array<Block>>("definitions/htl-blocks.json")
    val displayContexts = JsonReader.readJson<Array<DisplayContext>>("definitions/htl-display-contexts.json")
    val expressionOptions = JsonReader.readJson<Array<ExpressionOption>>("definitions/htl-expression-options.json")
    val globalObjects = JsonReader.readJson<Array<GlobalObject>>("definitions/htl-global-objects.json")
    val predefinedProperties = JsonReader.readJson<Array<PredefinedProperty>>("definitions/htl-predefined-properties.json")
    val listProperties = JsonReader.readJson<Array<ListProperty>>("definitions/htl-list-properties.json")

    val globalPropertyObjectsNames = setOf("properties", "pageProperties", "inheritedPageProperties")

}
