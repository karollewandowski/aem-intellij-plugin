package co.nums.intellij.aem.messages

import com.intellij.AbstractBundle
import org.jetbrains.annotations.PropertyKey

private const val BUNDLE = "messages.HtlInspectionsBundle"

object HtlInspectionsBundle : AbstractBundle(BUNDLE) {

    @JvmStatic
    fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any?): String = getMessage(key, *params)

}
