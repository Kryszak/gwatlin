package io.github.kryszak.gwatlin.clients.homestead

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.homestead.model.Category
import io.github.kryszak.gwatlin.api.homestead.model.Decoration
import io.github.kryszak.gwatlin.api.homestead.model.Glyph
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class HomesteadClient : BaseHttpClient() {

    private val baseEndpoint = "/homestead"

    private val decorationsEndpoint = "$baseEndpoint/decorations"

    private val decorationCategoriesEndpoint = "$decorationsEndpoint/categories"

    private val glyphsEndpoint = "$baseEndpoint/glyphs"

    fun getDecorationIds(): List<Int> {
        return getRequest(decorationsEndpoint)
    }

    fun getDecoration(id: Int, language: ApiLanguage?): Decoration {
        return getRequest("$decorationsEndpoint/$id", listOf(), language)
    }

    fun getDecorations(ids: List<Int>, language: ApiLanguage?): List<Decoration> {
        val params = ids.joinToString(",")
        return getRequest(decorationsEndpoint, listOf("ids" to params), language)
    }

    fun getPagedDecorations(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Decoration>> {
        return getPagedRequest(decorationsEndpoint, pageRequest.toQueryParams(), language)
    }

    fun getDecorationCategoryIds(): List<Int> {
        return getRequest(decorationCategoriesEndpoint)
    }

    fun getDecorationCategory(id: Int, language: ApiLanguage?): Category {
        return getRequest("$decorationCategoriesEndpoint/$id", listOf(), language)
    }

    fun getDecorationCategories(ids: List<Int>, language: ApiLanguage?): List<Category> {
        val params = ids.joinToString(",")
        return getRequest(decorationCategoriesEndpoint, listOf("ids" to params), language)
    }

    fun getPagedDecorationCategories(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Category>> {
        return getPagedRequest(decorationCategoriesEndpoint, pageRequest.toQueryParams(), language)
    }

    fun getGlyphIds(): List<String> {
        return getRequest(glyphsEndpoint)
    }

    fun getGlyph(id: String): Glyph {
        return getRequest("$glyphsEndpoint/$id")
    }

    fun getGlyphs(ids: List<String>): List<Glyph> {
        val params = ids.joinToString(",")
        return getRequest(glyphsEndpoint, listOf("ids" to params))
    }

    fun getPagedGlyphs(pageRequest: PageRequest, language: ApiLanguage?): PagedResponse<List<Glyph>> {
        return getPagedRequest(glyphsEndpoint, pageRequest.toQueryParams(), language)
    }
}