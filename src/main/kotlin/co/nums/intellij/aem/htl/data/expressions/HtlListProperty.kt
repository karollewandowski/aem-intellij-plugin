package co.nums.intellij.aem.htl.data.expressions

enum class HtlListProperty(
        val identifier: String,
        val type: String,
        val description: String
) {
    INDEX(
            identifier = "index",
            type = "Number",
            description = "zero-based counter (<code>0..length-1</code>)"
    ),
    COUNT(
            identifier = "count",
            type = "Number",
            description = "one-based counter (<code>1..length</code>)"
    ),
    FIRST(
            identifier = "first",
            type = "Boolean",
            description = "<code>true</code> for the first element being iterated"
    ),
    MIDDLE(
            identifier = "middle",
            type = "Boolean",
            description = "<code>true</code> if element being iterated is neither the first nor the last"
    ),
    LAST(
            identifier = "last",
            type = "Boolean",
            description = "<code>true</code> for the last element being iterated"
    ),
    ODD(
            identifier = "odd",
            type = "Boolean",
            description = "<code>true</code> if index is odd"
    ),
    EVEN(
            identifier = "even",
            type = "Boolean",
            description = "<code>true</code> if index is even"
    )
}
