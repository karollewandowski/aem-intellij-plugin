package co.nums.intellij.aem.errorreports

import com.intellij.AbstractBundle
import org.jetbrains.annotations.PropertyKey

private const val BUNDLE = "messages.ErrorReportingBundle"

object ErrorReportingBundle : AbstractBundle(BUNDLE) {

    @JvmStatic
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any?): String = getMessage(key, *params)

}
