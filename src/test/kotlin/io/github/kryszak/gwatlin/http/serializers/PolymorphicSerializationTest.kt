package io.github.kryszak.gwatlin.http.serializers

import io.github.kryszak.gwatlin.api.gamemechanics.model.skill.Skill
import io.github.kryszak.gwatlin.api.gamemechanics.model.trait.Trait
import io.github.kryszak.gwatlin.api.guild.model.log.GuildLog
import io.github.kryszak.gwatlin.api.items.model.item.Item
import io.github.kryszak.gwatlin.http.serializers.JsonConfigurer.json
import io.github.kryszak.gwatlin.http.serializers.PolymorphicSerializationTest.PolymorphicSerializationTestInput.Companion.ofGuildLogs
import io.github.kryszak.gwatlin.http.serializers.PolymorphicSerializationTest.PolymorphicSerializationTestInput.Companion.ofItems
import io.github.kryszak.gwatlin.http.serializers.PolymorphicSerializationTest.PolymorphicSerializationTestInput.Companion.ofSkills
import io.github.kryszak.gwatlin.http.serializers.PolymorphicSerializationTest.PolymorphicSerializationTestInput.Companion.ofTraits
import io.github.kryszak.gwatlin.util.ResourcesUtil
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.datatest.withData
import kotlinx.serialization.KSerializer
import kotlinx.serialization.json.Json
import kotlinx.serialization.serializer

internal class PolymorphicSerializationTest : ShouldSpec() {

    init {
        context("Polymorphic deserialization and serialization") {
            withData(
                mapOf(
                    "one armor" to ofItems("armor_item.json"),
                    "mixed list of items" to ofItems("armor_item.json", "bag_item.json", "gizmo_item.json"),
                    // "ItemDetails" is not directly used in concrete classes (except when null by default)
                    "Polymorhic type 'Fact' is used by Skill" to ofSkills(),
                    "Polymorhic type 'Fact' is used by Trait" to ofTraits(),
                    "GuildLog" to ofGuildLogs(),
                )
            ) { input ->
                input.decodeFlattenAndEncode()
            }
        }
    }

    private data class PolymorphicSerializationTestInput<T>(
        private val json: Json,
        private val serializer: KSerializer<List<T>>,
        private val fileNames: List<String>,
        private val fileRead: (String) -> String,
    ) {

        fun decodeFlattenAndEncode() {
            // Decode all input files, flatten to single list
            val values = fileNames.map(fileRead)
                .map { json.decodeFromString(serializer, it) }
                .flatten()
            // Encode the list
            json.encodeToString(serializer, values)
        }

        companion object {

            inline fun <reified T> ofTestResourceJsons(
                first: String,
                vararg rest: String,
                noinline fileRead: (String) -> String,
            ): PolymorphicSerializationTestInput<T> {
                return PolymorphicSerializationTestInput(
                    json = json,
                    serializer = json.serializersModule.serializer<List<T>>(),
                    fileNames = listOf(first) + rest,
                    fileRead = fileRead,
                )
            }

            fun ofItems(first: String, vararg rest: String): PolymorphicSerializationTestInput<Item> {
                return ofTestResourceJsons(first, *rest) { ResourcesUtil.readResponseItem(it) }
            }

            fun ofSkills(): PolymorphicSerializationTestInput<Skill> {
                return ofTestResourceJsons("responses/gamemechanics/skills.json") { ResourcesUtil.readResource(it) }
            }

            fun ofTraits(): PolymorphicSerializationTestInput<Trait> {
                return ofTestResourceJsons("responses/gamemechanics/traits.json") { ResourcesUtil.readResource(it) }
            }

            fun ofGuildLogs(): PolymorphicSerializationTestInput<GuildLog> {
                return ofTestResourceJsons(
                    "responses/guild/guild_log.json",
                    "responses/guild/guild_log_since.json",
                ) { ResourcesUtil.readResource(it) }
            }
        }
    }

}
