package co.nums.intellij.aem.version

/*
 * Versions values are simply strings (no objects storing eg. major, minor and bug-fixing
 * parts), because there shouldn't be AEM versions containing anything more than major and
 * minor parts (improvements and bug fixes are provided by service packs and hot fixes
 * packages). String representation is enough and will be efficient in comparisons.
 */
class AemVersion(val aem: String, val htl: String) {

    companion object {

        val AEM_60 = AemVersion(
                aem = "6.0",
                htl = "1.0"
        )

        val AEM_61 = AemVersion(
                aem = "6.1",
                htl = "1.1"
        )

        val AEM_62 = AemVersion(
                aem = "6.2",
                htl = "1.2"
        )

        val AEM_63 = AemVersion(
                aem = "6.3",
                htl = "1.3"
        )

        val ALL = arrayOf(AEM_60, AEM_61, AEM_62, AEM_63)

    }

}
