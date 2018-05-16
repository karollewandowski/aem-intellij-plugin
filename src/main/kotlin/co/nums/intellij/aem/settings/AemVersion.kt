package co.nums.intellij.aem.settings

/*
 * Versions values are simply strings (no objects storing eg. major, minor and bug-fixing
 * parts), because there shouldn't be AEM versions containing anything more than major and
 * minor parts (improvements and bug fixes are provided by service packs and hot fixes
 * packages). String representation is enough and will be efficient in comparisons.
 */
enum class AemVersion(val aem: String, val htl: String) {

    AEM_6_0(
            aem = "6.0",
            htl = "1.0"
    ),

    AEM_6_1(
            aem = "6.1",
            htl = "1.1"
    ),

    AEM_6_2(
            aem = "6.2",
            htl = "1.2"
    ),

    AEM_6_3(
            aem = "6.3",
            htl = "1.3"
    ),

    AEM_6_4(
            aem = "6.4",
            htl = "1.3.1"
    )

}
