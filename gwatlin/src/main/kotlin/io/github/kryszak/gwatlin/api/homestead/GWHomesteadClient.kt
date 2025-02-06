package io.github.kryszak.gwatlin.api.homestead

import io.github.kryszak.gwatlin.api.ApiLanguage
import io.github.kryszak.gwatlin.api.homestead.model.Category
import io.github.kryszak.gwatlin.api.homestead.model.Decoration
import io.github.kryszak.gwatlin.api.homestead.model.Glyph
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.api.shared.PagedResponse
import io.github.kryszak.gwatlin.clients.homestead.HomesteadClient


/**
 * Client for homesteads endpoints.
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWHomesteadClient {

    private val homesteadClient = HomesteadClient()

    /**
     * Returns list of all available decoration ids
     */
    fun getDecorationIds(): List<Int> {
        return homesteadClient.getDecorationIds()
    }

    /**
     * Returns decoration for given
     * @param id of decoration
     * @param language for response
     */
    fun getDecoration(id: Int, language: ApiLanguage? = null): Decoration {
        return homesteadClient.getDecoration(id, language)
    }

    /**
     * Returns decorations for given
     * @param ids of decorations
     * @param language for response
     */
    fun getDecorations(ids: List<Int>, language: ApiLanguage? = null): List<Decoration> {
        return homesteadClient.getDecorations(ids, language)
    }

    /**
     * Returns paged decorations
     * @param language for response
     */
    fun getPagedDecorations(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Decoration>> {
        return homesteadClient.getPagedDecorations(pageRequest, language)
    }

    /**
     * Returns list of all available decoration category ids
     */
    fun getDecorationCategoryIds(): List<Int> {
        return homesteadClient.getDecorationCategoryIds()
    }

    /**
     * Returns decoration category for given
     * @param id of category
     * @param language for response
     */
    fun getDecorationCategory(id: Int, language: ApiLanguage? = null): Category {
        return homesteadClient.getDecorationCategory(id, language)
    }

    /**
     * Returns decoration categories for given
     * @param ids of categories
     * @param language for response
     */
    fun getDecorationCategories(ids: List<Int>, language: ApiLanguage? = null): List<Category> {
        return homesteadClient.getDecorationCategories(ids, language)
    }

    /**
     * Returns paged decoration categories
     * @param language for response
     */
    fun getPagedDecorationCategories(
        pageRequest: PageRequest,
        language: ApiLanguage? = null,
    ): PagedResponse<List<Category>> {
        return homesteadClient.getPagedDecorationCategories(pageRequest, language)
    }

    /**
     * Returns list of all available glyph ids
     */
    fun getGlyphIds(): List<String> {
        return homesteadClient.getGlyphIds()
    }

    /**
     * Returns glyph for given
     * @param id of glyph
     */
    fun getGlyph(id: String): Glyph {
        return homesteadClient.getGlyph(id)
    }

    /**
     * Returns glyphs for given
     * @param ids of glyphs
     */
    fun getGlyphs(ids: List<String>): List<Glyph> {
        return homesteadClient.getGlyphs(ids)
    }

    /**
     * Returns paged glyphs
     */
    fun getPagedGlyphs(pageRequest: PageRequest, language: ApiLanguage? = null): PagedResponse<List<Glyph>> {
        return homesteadClient.getPagedGlyphs(pageRequest, language)
    }
}