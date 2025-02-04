package io.github.kryszak.gwatlin.clients.homestead

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.homestead.model.Category
import io.github.kryszak.gwatlin.api.homestead.model.Decoration
import io.github.kryszak.gwatlin.api.homestead.model.Glyph
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
        return getRequest("$decorationsEndpoint/$id", language)
    }

    fun getDecorations(ids: List<Int>, language: ApiLanguage?): List<Decoration> {
        val params = ids.joinToString(",")
        return getRequest("$decorationsEndpoint?ids=$params", language)
    }

    fun getDecorationCategoryIds(): List<Int> {
        return getRequest(decorationCategoriesEndpoint)
    }

    fun getDecorationCategory(id: Int, language: ApiLanguage?): Category {
        return getRequest("$decorationCategoriesEndpoint/$id", language)
    }

    fun getDecorationCategories(ids: List<Int>, language: ApiLanguage?): List<Category> {
        val params = ids.joinToString(",")
        return getRequest("$decorationCategoriesEndpoint?ids=$params", language)
    }

    fun getGlyphIds(): List<String> {
        return getRequest(glyphsEndpoint)
    }

    fun getGlyph(id: String): Glyph {
        return getRequest("$glyphsEndpoint/$id")
    }

    fun getGlyphs(ids: List<String>): List<Glyph> {
        val params = ids.joinToString(",")
        return getRequest("$glyphsEndpoint?ids=$params")
    }
}