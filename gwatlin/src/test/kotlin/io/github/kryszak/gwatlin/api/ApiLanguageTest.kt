package io.github.kryszak.gwatlin.api

import io.kotest.core.spec.style.ShouldSpec
import io.kotest.datatest.withData
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import java.util.*

internal class ApiLanguageTest : ShouldSpec({
    context("Parsing ApiLanguage from Locale") {
        withData(
            mapOf(
                "english" to LocaleLanguagePair(Locale.ENGLISH, ApiLanguage.EN),
                "german" to LocaleLanguagePair(Locale.GERMAN, ApiLanguage.DE),
                "spanish" to LocaleLanguagePair(Locale.forLanguageTag("es"), ApiLanguage.ES),
                "french" to LocaleLanguagePair(Locale.FRENCH, ApiLanguage.FR),
                "chinese" to LocaleLanguagePair(Locale.CHINESE, ApiLanguage.ZH),
            )
        ) { (locale, apiLanguage) ->
            // when
            val converted = ApiLanguage.fromLocale(locale)

            // then
            converted.shouldNotBeNull()
            converted shouldBe apiLanguage
            converted.locale shouldBe locale
        }
    }
    context("Parsing ApiLanguage from String") {
        withData(
            mapOf(
                "english" to StringLanguagePair("en", ApiLanguage.EN),
                "german" to StringLanguagePair("de", ApiLanguage.DE),
                "spanish" to StringLanguagePair("es", ApiLanguage.ES),
                "french" to StringLanguagePair("fr", ApiLanguage.FR),
                "chinese" to StringLanguagePair("zh", ApiLanguage.ZH),
            )
        ) { (languageString, apiLanguage) ->
            // when
            val converted = ApiLanguage.fromString(languageString)

            // then
            converted.shouldNotBeNull()
            converted shouldBe apiLanguage
            converted.locale.toLanguageTag() shouldBe languageString
        }
    }
}) {

    data class LocaleLanguagePair(
        val locale: Locale,
        val apiLanguage: ApiLanguage,
    )

    data class StringLanguagePair(
        val languageString: String,
        val apiLanguage: ApiLanguage,
    )
}

