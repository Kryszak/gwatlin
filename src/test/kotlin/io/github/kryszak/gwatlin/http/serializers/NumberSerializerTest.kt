package io.github.kryszak.gwatlin.http.serializers

import io.github.kryszak.gwatlin.http.serializers.JsonConfigurer.json
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.doubles.plusOrMinus
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldStartWith

internal class NumberSerializerTest : ShouldSpec({
    val s = NumberSerializer()
    should("serialize int") {
        json.encodeToString(s, 1 as Number) shouldBe "1"
    }
    should("serialize double") {
        json.encodeToString(s, 1.5 as Number) shouldStartWith "1."
    }
    should("deserialize int") {
        json.decodeFromString(s, "1") shouldBe 1
    }
    should("deserialize double") {
        json.decodeFromString(s, "1.5") shouldBe (1.5 plusOrMinus 0.01)
    }
})
