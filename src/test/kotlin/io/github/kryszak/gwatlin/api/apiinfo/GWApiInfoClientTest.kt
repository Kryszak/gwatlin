package io.github.kryszak.gwatlin.api.apiinfo

import io.github.kryszak.gwatlin.api.apiinfo.model.SchemaVersion
import io.github.kryszak.gwatlin.clients.apiinfo.ApiInfoClient
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.matchers.collections.shouldContainExactly

internal class GWApiInfoClientTest : BaseWiremockTest() {
    private val apiInfoClient = ApiInfoClient()

    init {
        should("Get available schema versions") {
            // given
            stubResponse("/v2.json?v=latest", "/responses/apiinfo/api_info.json")

            // when
            val schemaVersions = apiInfoClient.getSchemaVersions()

            // then
            schemaVersions shouldContainExactly expectedSchemaVersions()
        }
    }

    private fun expectedSchemaVersions() = listOf(
        SchemaVersion(
            "2019-02-21T00:00:00.000Z",
            "Add `last_modified` field to account and character records."
        ),
        SchemaVersion(
            "2019-03-22T00:00:00.000Z",
            "Change `/v2/account/home/cats` to just list cat ids"
        ),
        SchemaVersion(
            "2019-05-16T00:00:00.000Z",
            "Change the `access_required` field in `v2/achievements/daily` to show product conditions"
        ),
        SchemaVersion(
            "2019-05-21T23:00:00.000Z",
            "Add `schema_versions` to `/v2.json`"
        ),
        SchemaVersion(
            "2019-05-22T00:00:00.000Z",
            "Change `/v2/tokeninfo` to show subtoken information when one is provided."
        ),
        SchemaVersion(
            "2019-12-19T00:00:00.000Z",
            "Add `code` and `skills_by_palette` to `/v2/professions`. Add `code` to `/v2/legends`. Add `build_storage_slots` to `/v2/account`. Add `build_tabs_unlocked`, `active_build_tab`, `build_tabs`, `equipment_tabs_unlocked`, `active_equipment_tab` and `equipment_tabs` to `/v2/characters/:id`. Add `equipment[i].location` and `equipment[i].tabs` to `/v2/characters/:id`. Remove `skills` and `specializations` from `/v2/characters/:id`."
        ),
        SchemaVersion(
            "2020-11-17T00:30:00.000Z",
            "Change the type of `details.secondary_suffix_item_id` from `/v2/items` to be an optional int."
        ),
        SchemaVersion(
            "2021-04-06T21:00:00.000Z",
            "Move `equipment_pvp` under `equipment_tabs` in `/v2/characters/:id`"
        ),
        SchemaVersion(
            "2021-07-15T13:00:00.000Z",
            "Add `EquippedFromLegendaryArmory` and `LegendaryArmory` values to `equipment[i].location` field"
        ),
        SchemaVersion(
            "2022-03-09T02:00:00.000Z",
            "Change `ingredients` to `item_ingredients` and add `currency_ingredients` to `/v2/recipes`."
        ),
        SchemaVersion(
            "2022-03-23T19:00:00.000Z",
            "Change `/v2/achievements/categories` to show tomorrow's dailies and show access requirements for relevant achievements."
        )
    )
}
