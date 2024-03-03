package io.github.kryszak.gwatlin.util

object ResourcesUtil {

    fun readResource(file: String): String {
        val rooted = if(file.startsWith("/")) file else "/$file"
        val url = ResourcesUtil::class.java.getResource(rooted)
        return requireNotNull(url) { "Resource not found: $file" }.readText()
    }

    fun readResponseItem(itemFile: String) = readResource("/responses/items/$itemFile")

}
