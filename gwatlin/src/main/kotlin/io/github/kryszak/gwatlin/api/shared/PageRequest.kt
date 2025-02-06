package io.github.kryszak.gwatlin.api.shared

/**
 * Class for requesting paged resources
 * @property page - number of requested page
 * @property size - number of elements per page to be returned
 */
data class PageRequest(
    val page: Int,
    val size: Int,
) {

    internal fun toQueryParams(): List<Pair<String, String>> {
        return listOf("page" to page.toString(), "page_size" to size.toString())
    }
}
