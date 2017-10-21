package co.nums.intellij.aem.htl.definitions

enum class HtlPredefinedProperty(
        val identifier: String,
        val type: String
) {

    JCR_TITLE(
            identifier = "jcr:title",
            type = "String"
    ),
    JCR_DESCRIPTION(
            identifier = "jcr:description",
            type = "String"
    ),
    JCR_LANGUAGE(
            identifier = "jcr:language",
            type = "String"
    ),
    JCR_CREATED(
            identifier = "jcr:created",
            type = "Date"
    ),
    JCR_CREATED_BY(
            identifier = "jcr:createdBy",
            type = "String"
    ),
    JCR_LAST_MODIFIED(
            identifier = "jcr:lastModified",
            type = "Date"
    ),
    JCR_LAST_MODIFIED_BY(
            identifier = "jcr:lastModifiedBy",
            type = "String"
    ),
    JCR_PRIMARY_TYPE(
            identifier = "jcr:primaryType",
            type = "String"
    ),
    SLING_KEY(
            identifier = "sling:key",
            type = "String"
    ),
    SLING_MESSAGE(
            identifier = "sling:message",
            type = "String"
    ),
    SLING_RESOURCE_TYPE(
            identifier = "sling:resourceType",
            type = "String"
    ),
    SLING_RESOURCE_SUPER_TYPE(
            identifier = "sling:resourceSuperType",
            type = "String"
    ),
    CQ_LAST_MODIFIED(
            identifier = "cq:lastModified",
            type = "Date"
    ),
    CQ_LAST_MODIFIED_BY(
            identifier = "cq:lastModifiedBy",
            type = "String"
    ),
    CQ_TEMPLATE(
            identifier = "cq:template",
            type = "String"
    )

}
