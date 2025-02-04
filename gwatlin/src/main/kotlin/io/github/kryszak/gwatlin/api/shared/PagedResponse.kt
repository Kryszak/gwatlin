package io.github.kryszak.gwatlin.api.shared

/**
 * Model for paged responses from endpoints
 * @param data response data for requested page
 * @param pageSize – The size of a page (like the page_size query parameter).
 * @param pageTotal – The total number of pages.
 * @param resultCount – The number of resources on the current page (lower or equal to the page size).
 * @param resultTotal – The total number of resources.
 */
data class PagedResponse<T>(
    val data: T,
    val pageSize: Int,
    val pageTotal: Int,
    val resultCount: Int,
    val resultTotal: Int,
)
