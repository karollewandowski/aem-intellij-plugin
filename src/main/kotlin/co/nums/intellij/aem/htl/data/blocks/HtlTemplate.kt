package co.nums.intellij.aem.htl.data.blocks

data class HtlTemplate(val filePath: String, val name: String, val parameters: List<HtlTemplateParameter>)

data class HtlTemplateParameter(val name: String, val usageHint: String?)
