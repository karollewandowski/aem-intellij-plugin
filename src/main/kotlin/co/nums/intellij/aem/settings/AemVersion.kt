package co.nums.intellij.aem.settings

/*
 * Versions values are simply strings (no objects storing eg. major, minor and bug-fixing
 * parts), because there shouldn't be AEM versions containing anything more than major and
 * minor parts (improvements and bug fixes are provided by service packs and hot fixes
 * packages). String representation is enough and will be efficient in comparisons.
 */
enum class AemVersion(val aem: String, val htl: String) {

    AEM_60(
            aem = "6.0",
            htl = "1.0"
    ),

    AEM_61(
            aem = "6.1",
            htl = "1.1"
    ),

    AEM_62(
            aem = "6.2",
            htl = "1.2"
    ),

    AEM_63(
            aem = "6.3",
            htl = "1.3"
    )

}
