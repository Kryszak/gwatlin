package io.github.kryszak.gwatlin.util

/**
 * A small helper for reading resources.
 */
internal object ResourcesUtil {

    /**
     * Read a resource from classpath.
     * Ensures its read from the root, and not relative to this class.
     */
    fun readResource(file: String): String {
        val rooted = if(file.startsWith("/")) file else "/$file"
        val url = ResourcesUtil::class.java.getResource(rooted)
        return requireNotNull(url) { "Resource not found: $file" }.readText()
    }

    /**
     * Convenience method for reading `responses/items` files.
     */
    fun readResponseItem(itemFile: String) = readResource("/responses/items/$itemFile")

}
