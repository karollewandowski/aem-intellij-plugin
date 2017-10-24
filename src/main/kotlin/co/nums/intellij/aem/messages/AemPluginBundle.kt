package co.nums.intellij.aem.messages

import com.intellij.AbstractBundle
import org.jetbrains.annotations.PropertyKey

private const val BUNDLE = "messages.AemPluginBundle"

object AemPluginBundle : AbstractBundle(BUNDLE) {

    @JvmStatic fun message(@PropertyKey(resourceBundle = BUNDLE) key: String, vararg params: Any?): String? =
            getMessage(key, *params)

    @JvmStatic fun messageOrDefault(@PropertyKey(resourceBundle = BUNDLE) key: String, defaultMessage: String, vararg params: Any?): String {
        return getMessage(key, *params) ?: defaultMessage
    }

}
