package co.nums.intellij.aem.htl.definitions

import co.nums.intellij.aem.htl.definitions.HtlPredefinedPropertyContext.GLOBAL_PROPERTIES_OBJECT
import co.nums.intellij.aem.htl.definitions.HtlPredefinedPropertyContext.LIST

enum class HtlPredefinedProperty(
        val context: HtlPredefinedPropertyContext,
        val identifier: String,
        val type: String,
        val description: String? = "<code>$type</code>"
) {

    JCR_TITLE(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "jcr:title",
            type = "String"
    ),
    JCR_DESCRIPTION(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "jcr:description",
            type = "String"
    ),
    JCR_LANGUAGE(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "jcr:language",
            type = "String"
    ),
    JCR_CREATED(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "jcr:created",
            type = "Date"
    ),
    JCR_CREATED_BY(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "jcr:createdBy",
            type = "String"
    ),
    JCR_LAST_MODIFIED(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "jcr:lastModified",
            type = "Date"
    ),
    JCR_LAST_MODIFIED_BY(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "jcr:lastModifiedBy",
            type = "String"
    ),
    JCR_PRIMARY_TYPE(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "jcr:primaryType",
            type = "String"
    ),
    SLING_KEY(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "sling:key",
            type = "String"
    ),
    SLING_MESSAGE(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "sling:message",
            type = "String"
    ),
    SLING_RESOURCE_TYPE(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "sling:resourceType",
            type = "String"
    ),
    SLING_RESOURCE_SUPER_TYPE(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "sling:resourceSuperType",
            type = "String"
    ),
    CQ_LAST_MODIFIED(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "cq:lastModified",
            type = "Date"
    ),
    CQ_LAST_MODIFIED_BY(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "cq:lastModifiedBy",
            type = "String"
    ),
    CQ_TEMPLATE(
            context = GLOBAL_PROPERTIES_OBJECT,
            identifier = "cq:template",
            type = "String"
    ),

    // predefined list properties
    LIST_ELEMENT_INDEX(
            context = LIST,
            identifier = "index",
            type = "Number",
            description = "zero-based counter (<code>0..length-1</code>)"
    ),
    LIST_ELEMENT_COUNT(
            context = LIST,
            identifier = "count",
            type = "Number",
            description = "one-based counter (<code>1..length</code>)"
    ),
    LIST_ELEMENT_FIRST(
            context = LIST,
            identifier = "first",
            type = "Boolean",
            description = "<code>true</code> for the first element being iterated"
    ),
    LIST_ELEMENT_MIDDLE(
            context = LIST,
            identifier = "middle",
            type = "Boolean",
            description = "<code>true</code> if element being iterated is neither the first nor the last"
    ),
    LIST_ELEMENT_LAST(
            context = LIST,
            identifier = "last",
            type = "Boolean",
            description = "<code>true</code> for the last element being iterated"
    ),
    LIST_ELEMENT_ODD(
            context = LIST,
            identifier = "odd",
            type = "Boolean",
            description = "<code>true</code> if index is odd"
    ),
    LIST_ELEMENT_EVEN(
            context = LIST,
            identifier = "even",
            type = "Boolean",
            description = "<code>true</code> if index is even"
    )

}

enum class HtlPredefinedPropertyContext {

    LIST,
    GLOBAL_PROPERTIES_OBJECT

}
