package com.kryszak.gwatlin.api

import java.util.*

/**
 * Enum representing the languages supported by the API
 * @param locale the locale that's backing the language
 */
enum class ApiLanguage(val locale: Locale) {
    /**
     * English
     */
    EN(Locale.ENGLISH),

    /**
     * German
     */
    DE(Locale.GERMAN),

    /**
     * Spanish
     */
    ES(Locale.forLanguageTag("es")),

    /**
     * French
     */
    FR(Locale.FRENCH),

    /**
     * Chinese
     */
    ZH(Locale.CHINESE);

    companion object {
        private val localeLanguageMap by lazy { values().associateBy { it.locale } }
        private val stringLanguageMap by lazy { values().associateBy { it.locale.language } }

        /**
         * @return the language that's corresponding to the supplied locale, or null if no language matches the locale
         */
        @JvmStatic
        fun fromLocale(locale: Locale) = localeLanguageMap[locale]

        /**
         * @return the language that's corresponding to the supplied ISO 639-1 string, or null if no language matches the string
         */
        @JvmStatic
        fun fromString(languageString: String) = stringLanguageMap[languageString]
    }

    /**
     * @return a string representing this language in an API compatible format. For now, this format is the
     * ISO 639-1 code.
     */
    val apiString: String get() = locale.language
}