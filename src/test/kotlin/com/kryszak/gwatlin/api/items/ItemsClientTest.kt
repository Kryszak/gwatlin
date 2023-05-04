package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.items.model.item.ItemRarity
import com.kryszak.gwatlin.api.items.model.item.ItemType
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeBlank

internal class ItemsClientTest : BaseWiremockTest() {

    private val itemsClient = GWItemsClient()

    init {
        should("Get item ids") {
            // given
            stubResponse("/items", "/responses/items/item_ids.json")

            // when
            val itemIds = itemsClient.getItemIds()

            // then
            itemIds shouldHaveSize 57117
        }

        should("Get item") {
            // given
            val id = 28445
            val lang = ApiLanguage.EN

            stubResponse("/items?ids=28445", "/responses/items/item.json", language = lang)

            // when
            val items = itemsClient.getItems(listOf(id), lang)

            // then
            items shouldHaveSize 1
            assertSoftly(items[0]) {
                name shouldBe "Strong Soft Wood Longbow of Fire"
                description.shouldBeBlank()
                type shouldBe ItemType.WEAPON
                level shouldBe 44
                rarity shouldBe ItemRarity.MASTERWORK
                vendorValue shouldBe 120
                defaultSkin shouldBe 3942
                gameTypes shouldHaveSize 4
                flags shouldHaveSize 1
                restrictions.shouldBeEmpty()
                id shouldBe 28445
                chatLink shouldBe "[&AgEdbwAA]"
                icon shouldBe "https://render.guildwars2.com/file/C6110F52DF5AFE0F00A56F9E143E9732176DDDE9/65015.png"
                assertSoftly(details) {
                    get("type") shouldBe "LongBow"
                    get("damage_type") shouldBe "Physical"
                    get("min_power") shouldBe 385
                    get("max_power") shouldBe 452
                    get("defense") shouldBe 0
                    (get("infusion_slots") as List<*>).shouldBeEmpty()
                    get("suffix_item_id") shouldBe 24547
                    get("secondary_suffix_item_id") shouldBe ""
                }
            }
        }
    }
}