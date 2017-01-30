package co.nums.intellij.aem.utils

import com.google.gson.Gson

object JsonReader {

    inline fun <reified T: Any> readJson(fileName: String): T {
        val reader = JsonReader::class.java.classLoader.getResourceAsStream(fileName).reader()
        return Gson().fromJson(reader, T::class.java)
    }

}
