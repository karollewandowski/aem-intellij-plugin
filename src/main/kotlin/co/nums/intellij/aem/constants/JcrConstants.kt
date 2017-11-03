package co.nums.intellij.aem.constants

const val JCR_ROOT_DIRECTORY_NAME = "jcr_root"

/**
 * List is limited to directories that are likely to be
 * used by developers to store files.
 */
val JCR_SPECIFIC_DIRECTORIES = hashSetOf(
        "apps",
        "conf",
        "content",
        "etc",
        "home",
        "libs")
