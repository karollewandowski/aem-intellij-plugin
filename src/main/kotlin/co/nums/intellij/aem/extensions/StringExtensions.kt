package co.nums.intellij.aem.extensions

fun String.getSingularForm() = when {
    endsWith("ies") -> dropLast(3) + 'y'
    endsWith("IES") -> dropLast(3) + 'Y'
    toLowerCase().endsWith("sses") || toLowerCase().endsWith("shes") -> dropLast(2)
    toLowerCase().last() == 's' -> dropLast(1)
    else -> prependIndefiniteArticle()
}

fun String.prependIndefiniteArticle() = when (first()) {
    'a', 'e', 'i', 'o', 'u' -> "an${capitalize()}"
    else -> "a${capitalize()}"
}
