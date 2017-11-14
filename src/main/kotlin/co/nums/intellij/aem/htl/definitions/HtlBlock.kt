package co.nums.intellij.aem.htl.definitions

import co.nums.intellij.aem.htl.completion.provider.insertHandler.*
import com.intellij.codeInsight.completion.InsertHandler
import com.intellij.codeInsight.lookup.LookupElement

const val HTL_BLOCK_PREFIX = "data-sly"

private val htlIterableBlocksTypes = HtlBlock.values().filter { it.iterable }.map { it.type }.toSet()
fun isHtlIterableBlock(blockType: String) = blockType in htlIterableBlocksTypes

enum class HtlBlock(
        val type: String,
        val identifierType: BlockIdentifierType,
        val iterable: Boolean = false,
        val insertHandler: InsertHandler<LookupElement>? = null,
        val doc: BlockDocumentation
) {

    ATTRIBUTE(
            type = "$HTL_BLOCK_PREFIX-attribute",
            identifierType = BlockIdentifierType.ATTRIBUTE_NAME,
            insertHandler = HtlExprBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Sets an attribute or a group of attributes on the current element (<code>style</code> and <code>on</code>* attributes are not supported due to XSS vulnerability).",
                    element = "always shown",
                    elementContent = "always shown",
                    attributeValue = "optional; <code>String</code> for setting attribute content, or <code>Boolean</code> for setting boolean attributes, or <code>Object</code> for setting multiple attributes; removes the attribute if the value is omitted.",
                    attributeIdentifier = "optional; the attribute name; must be omitted only if attribute value is an <code>Object</code>."
            )
    ),
    CALL(
            type = "$HTL_BLOCK_PREFIX-call",
            identifierType = BlockIdentifierType.NONE,
            insertHandler = HtlExprBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Calls a declared HTML block, passing parameters to it.",
                    element = "always shown",
                    elementContent = "replaced with the content of the called <code>$HTL_BLOCK_PREFIX-template</code> element",
                    attributeValue = "optional; an expression defining the template identifier and the parameters to pass",
                    attributeIdentifier = "none"
            )
    ),
    ELEMENT(
            type = "$HTL_BLOCK_PREFIX-element",
            identifierType = BlockIdentifierType.NONE,
            insertHandler = HtlExprBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Replaces the element's tag name.",
                    element = "always shown",
                    elementContent = "always shown",
                    attributeValue = "required; <code>String</code>; the element's tag name",
                    attributeIdentifier = "none"
            )
    ),
    INCLUDE(
            type = "$HTL_BLOCK_PREFIX-include",
            identifierType = BlockIdentifierType.NONE,
            insertHandler = HtlSimpleBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Includes the output of a rendering script run with the current context.",
                    element = "always shown",
                    elementContent = "replaced with the content of the included script",
                    attributeValue = "required; the file to include",
                    attributeIdentifier = "none"
            )
    ),
    LIST(
            type = "$HTL_BLOCK_PREFIX-list",
            identifierType = BlockIdentifierType.ELEMENT_CHILDREN_SCOPE_VARIABLE,
            iterable = true,
            insertHandler = HtlListBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Iterates over the content of each item in the attribute value.",
                    element = "shown only if the number of items from the attribute value is greater than 0, or if the attribute value is a string or number",
                    elementContent = "repeated as many times as there are items in the attribute value",
                    attributeValue = "optional; the item to iterate over; if omitted the content will not be shown",
                    attributeIdentifier = "optional; customised identifier name to access the item within the list element"
            )
    ),
    REPEAT(
            type = "$HTL_BLOCK_PREFIX-repeat",
            identifierType = BlockIdentifierType.ELEMENT_SCOPE_VARIABLE,
            iterable = true,
            insertHandler = HtlListBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Iterates over the content of each item in the attribute value and displays the containing element as many times as items in the attribute value.",
                    element = "shown only if the number of items from the attribute value is greater than 0, or if the attribute value is a string or number",
                    elementContent = "repeated as many times as there are items in the attribute value",
                    attributeValue = "optional; the item to iterate over; if omitted the containing element and its content will not be shown",
                    attributeIdentifier = "optional; customised identifier name to access the item within the repeat element"
            )
    ),
    RESOURCE(
            type = "$HTL_BLOCK_PREFIX-resource",
            identifierType = BlockIdentifierType.NONE,
            insertHandler = HtlSimpleBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Includes a rendered resource.",
                    element = "always shown",
                    elementContent = "replaced with the content of the resource",
                    attributeValue = "required; the path to include",
                    attributeIdentifier = "none"
            )
    ),
    TEMPLATE(
            type = "$HTL_BLOCK_PREFIX-template",
            identifierType = BlockIdentifierType.TEMPLATE_NAME,
            doc = BlockDocumentation(
                    description = "Declares an HTML block, naming it with an identifier and defining the parameters it can get.",
                    element = "never shown",
                    elementContent = "shown upon calling the template with <code>$HTL_BLOCK_PREFIX-call</code>",
                    attributeValue = "optional; an expression with only options, defining the parameters it can get",
                    attributeIdentifier = "required; the template identifier to declare"
            )
    ),
    TEST(
            type = "$HTL_BLOCK_PREFIX-test",
            identifierType = BlockIdentifierType.GLOBAL_VARIABLE,
            insertHandler = HtlExprBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Keeps, or removes the element depending on the attribute value.",
                    element = "shown if test evaluates to <code>true</code>",
                    elementContent = "shown if test evaluates to <code>true</code>",
                    attributeValue = "optional; evaluated as <code>Boolean</code> (but not type-cased to <code>Boolean</code> when exposed in a variable); evaluates to <code>false</code> if the value is omitted",
                    attributeIdentifier = "optional; identifier name to access the result of the test"
            )
    ),
    TEXT(
            type = "$HTL_BLOCK_PREFIX-text",
            identifierType = BlockIdentifierType.NONE,
            insertHandler = HtlExprBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Sets the content for the current element.",
                    element = "always shown",
                    elementContent = "replaced with evaluated result",
                    attributeValue = "required; evaluates to <code>String</code>; the element content",
                    attributeIdentifier = "none"
            )
    ),
    UNWRAP(
            type = "$HTL_BLOCK_PREFIX-unwrap",
            identifierType = BlockIdentifierType.NONE,
            doc = BlockDocumentation(
                    description = "Unwraps the element.",
                    element = "never shown",
                    elementContent = "always shown",
                    attributeValue = "none",
                    attributeIdentifier = "none"
            )
    ),
    USE(
            type = "$HTL_BLOCK_PREFIX-use",
            identifierType = BlockIdentifierType.GLOBAL_VARIABLE,
            insertHandler = HtlUseBlockInsertHandler,
            doc = BlockDocumentation(
                    description = "Exposes logic to the template.",
                    element = "always shown",
                    attributeValue = "required; evaluates to <code>String</code>; the object to instantiate",
                    attributeIdentifier = "optional; customised identifier name to access the instantiated logic"
            )
    );

}

data class BlockDocumentation(
        val description: String,
        val element: String,
        val elementContent: String? = null,
        val attributeValue: String?,
        val attributeIdentifier: String?
)

enum class BlockIdentifierType {

    NONE,
    ATTRIBUTE_NAME,
    TEMPLATE_NAME,
    GLOBAL_VARIABLE,
    ELEMENT_SCOPE_VARIABLE,
    ELEMENT_CHILDREN_SCOPE_VARIABLE;

    fun isVariable() =
            this == GLOBAL_VARIABLE || this == ELEMENT_SCOPE_VARIABLE || this == ELEMENT_CHILDREN_SCOPE_VARIABLE

}
