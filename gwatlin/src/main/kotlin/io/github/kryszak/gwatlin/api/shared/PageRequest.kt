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
    fun toQueryParams(): String {
        return "page=$page&page_size=$size"
    }
}
