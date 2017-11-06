package co.nums.intellij.aem.extensions

fun String.getSingularForm() = when {
    endsWith("ies") -> dropLast(3) + 'y'
    endsWith("IES") -> dropLast(3) + 'Y'
    endsWith("sses") || endsWith("shes") || endsWith("SSES") || endsWith("SHES") -> dropLast(2)
    last() == 's' || last() == 'S' -> dropLast(1)
    else -> prependIndefiniteArticle()
}

fun String.prependIndefiniteArticle() = when (first()) {
    'a', 'e', 'i', 'o', 'u' -> "an${capitalize()}"
    else -> "a${capitalize()}"
}
