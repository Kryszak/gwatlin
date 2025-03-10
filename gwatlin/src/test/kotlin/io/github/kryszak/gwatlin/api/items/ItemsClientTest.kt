package io.github.kryszak.gwatlin.api.items

import io.github.kryszak.gwatlin.api.ApiLanguage.EN
import io.github.kryszak.gwatlin.api.items.model.item.*
import io.github.kryszak.gwatlin.api.shared.PageRequest
import io.github.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.datatest.withData
import io.kotest.matchers.booleans.shouldBeFalse
import io.kotest.matchers.collections.shouldBeEmpty
import io.kotest.matchers.collections.shouldContainExactly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.nulls.shouldBeNull
import io.kotest.matchers.should
import io.kotest.matchers.shouldBe
import io.kotest.matchers.string.shouldBeBlank
import io.kotest.matchers.types.beOfType
import io.kotest.matchers.types.shouldBeTypeOf

internal class ItemsClientTest : BaseWiremockTest() {

    private val itemsClient = GWItemsClient()

    init {
        should("Get item ids") {
            // given
            stubResponse("/v2/items", "/responses/items/item_ids.json")

            // when
            val itemIds = itemsClient.getItemIds()

            // then
            itemIds shouldHaveSize 57117
        }

        context("Get item of type") {
            withData(
                mapOf(
                    "armor" to ItemTestInput(1234, "armor_item.json", ::armorAssertion),
                    "back" to ItemTestInput(56, "back_item.json", ::backAssertion),
                    "back infusion" to ItemTestInput(56, "back_infusion_item.json", ::backInfusionAssertion),
                    "bag" to ItemTestInput(1371, "bag_item.json", ::bagAssertion),
                    "consumable" to ItemTestInput(12421, "consumable_item.json", ::consumableAssertion),
                    "container" to ItemTestInput(9335, "container_item.json", ::containerAssertion),
                    "crafting material" to ItemTestInput(
                        19701,
                        "crafting_material_item.json",
                        ::craftingMaterialAssertion
                    ),
                    "gathering" to ItemTestInput(68376, "gathering_item.json", ::gatheringAssertion),
                    "gizmo" to ItemTestInput(67391, "gizmo_item.json", ::gizmoAssertion),
                    "jade tech" to ItemTestInput(97656, "jade_tech_item.json", ::jadeTechAssertion),
                    "miniature" to ItemTestInput(47845, "miniature_item.json", ::miniatureAssertion),
                    "power core" to ItemTestInput(97020, "power_core_item.json", ::powerCoreAssertion),
                    "relic" to ItemTestInput(70093, "relic_item.json", ::relicAssertion),
                    "tool" to ItemTestInput(23041, "salvage_kit_item.json", ::toolAssertion),
                    "trinket" to ItemTestInput(80553, "trinket_item.json", ::trinketAssertion),
                    "trophy" to ItemTestInput(70093, "trophy_item.json", ::trophyAssertion),
                    "upgrade component" to ItemTestInput(
                        72339,
                        "upgrade_component_item.json",
                        ::upgradeComponentAssertion
                    ),
                    "weapon" to ItemTestInput(28445, "weapon_item.json", ::weaponAssertion)
                )
            ) { (id, responseFile, assertion) ->
                // given
                val lang = EN
                stubResponse("/v2/items?ids=$id", "/responses/items/$responseFile", language = lang)

                // when
                val items = itemsClient.getItems(listOf(id), lang)

                // then
                items shouldHaveSize 1
                assertion(items[0])
            }
        }

        should("Get paged items") {
            // given
            stubResponse(
                "/v2/items?page=0&page_size=10",
                "/responses/items/items_paged.json",
                pageParams = PageParameters(10, 6776, 10, 67751)
            )

            // when
            val pagedItems = itemsClient.getPagedItems(PageRequest(0, 10))

            // then
            assertSoftly(pagedItems) {
                it.data shouldHaveSize 10
                it.pageSize shouldBe 10
                it.pageTotal shouldBe 6776
                it.resultCount shouldBe 10
                it.resultTotal shouldBe 67751
            }
        }
    }

    data class ItemTestInput(
        val itemId: Int,
        val responseFile: String,
        val assertion: (item: Item) -> Unit
    )

    private fun armorAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<ArmorItem>()
            name shouldBe "Precise Studded Pants"
            description.shouldBeBlank()
            type shouldBe ItemType.ARMOR
            level shouldBe 17
            rarity shouldBe ItemRarity.FINE
            vendorValue shouldBe 30
            defaultSkin shouldBe 22
            gameTypes shouldHaveSize 4
            flags.shouldBeEmpty()
            restrictions.shouldBeEmpty()
            id shouldBe 1234
            chatLink shouldBe "[&AgHSBAAA]"
            icon shouldBe "https://render.guildwars2.com/file/9EDA0D4C957DA89F653E9D27695592B139ECD035/61049.png"
            assertSoftly(details!!) {
                val armorDetails = details.shouldBeTypeOf<ArmorDetails>()
                armorDetails.type shouldBe "Leggings"
                armorDetails.weightClass shouldBe "Medium"
                armorDetails.defense shouldBe 34
                (armorDetails.infusionSlots as List<*>).shouldBeEmpty()
                armorDetails.attributeAdjustment shouldBe 26.736
                assertSoftly(armorDetails.infixUpgrade!!) {
                    id shouldBe 138
                    attributes shouldContainExactly listOf(InfixUpgradeAttribute("Precision", 9.0))
                }
                armorDetails.secondarySuffixItemId shouldBe ""
            }
        }

    private fun backAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<BackItem>()
            name shouldBe "Strong Back Brace"
            description shouldBe "This equipment goes under armor and can hold an additional upgrade."
            type shouldBe ItemType.BACK
            level shouldBe 0
            rarity shouldBe ItemRarity.BASIC
            vendorValue shouldBe 16
            defaultSkin shouldBe 2329
            gameTypes shouldContainExactly listOf("Activity", "Wvw", "Dungeon", "Pve")
            flags shouldContainExactly listOf("SoulbindOnAcquire", "SoulBindOnUse")
            restrictions.shouldBeEmpty()
            id shouldBe 56
            chatLink shouldBe "[&AgE4AAAA]"
            icon shouldBe "https://render.guildwars2.com/file/03B65C435B15EB2C10E04F3454B03718AAF3AE90/61004.png"
            assertSoftly(details!!) {
                val backItemDetails = details.shouldBeTypeOf<BackItemDetails>()
                (backItemDetails.infusionSlots as List<*>).shouldBeEmpty()
                backItemDetails.attributeAdjustment shouldBe 13.585
                assertSoftly(backItemDetails.infixUpgrade!!) {
                    id shouldBe 142
                    attributes shouldContainExactly listOf(
                        InfixUpgradeAttribute("Power", 5.0),
                        InfixUpgradeAttribute("Precision", 3.0)
                    )
                }
                backItemDetails.secondarySuffixItemId shouldBe ""
            }
        }

    private fun backInfusionAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<BackItem>()
            name shouldBe "Elegant Armorsmith's Backpack"
            description shouldBe "This equipment can hold an additional upgrade. Unequip this backpack to use it as a crafting ingredient. Using this as a crafting ingredient will destroy any upgrades held within."
            type shouldBe ItemType.BACK
            level shouldBe 78
            rarity shouldBe ItemRarity.EXOTIC
            vendorValue shouldBe 256
            defaultSkin shouldBe 2305
            gameTypes shouldContainExactly listOf("Activity", "Wvw", "Dungeon", "Pve")
            flags shouldContainExactly listOf("AccountBound", "NoSalvage", "NoSell", "AccountBindOnUse")
            restrictions.shouldBeEmpty()
            id shouldBe 62889
            chatLink shouldBe "[&AgGp9QAA]"
            icon shouldBe "https://render.guildwars2.com/file/5893AD0A6C6CB94E66CE0C1613E45172EE9996CD/866528.png"
            assertSoftly(details!!) {
                val backItemDetails = details.shouldBeTypeOf<BackItemDetails>()
                backItemDetails.attributeAdjustment shouldBe 82.34
                backItemDetails.statChoices shouldContainExactly listOf(
                    1052,
                    1042,
                    1044,
                    1043,
                    1046,
                    1048,
                    1047,
                    1041,
                    1050,
                    1051
                )
                assertSoftly(backItemDetails.infusionSlots) { slots ->
                    slots[0].itemId.shouldBeNull()
                    slots[0].flags shouldContainExactly listOf("Infusion")
                }
                backItemDetails.secondarySuffixItemId shouldBe ""
            }
        }

    private fun bagAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<BagItem>()
            name shouldBe "Red Leather Bag"
            description shouldBe "5 Slots"
            type shouldBe ItemType.BAG
            level shouldBe 0
            rarity shouldBe ItemRarity.BASIC
            vendorValue shouldBe 4
            gameTypes shouldContainExactly listOf("Activity", "Wvw", "Dungeon", "Pve")
            flags shouldContainExactly listOf("NoSalvage")
            restrictions.shouldBeEmpty()
            id shouldBe 8953
            chatLink shouldBe "[&AgH5IgAA]"
            icon shouldBe "https://render.guildwars2.com/file/9A17A2CE63A1C86D803E183671BB49BBBDB90715/63179.png"
            assertSoftly(details!!) {
                val bagItemDetails = details.shouldBeTypeOf<BagItemDetails>()
                bagItemDetails.noSellOrSort.shouldBeFalse()
                bagItemDetails.size shouldBe 5
            }
        }

    private fun consumableAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<ConsumableItem>()
            name shouldBe "Raspberry Peach Compote"
            description.shouldBeBlank()
            type shouldBe ItemType.CONSUMABLE
            level shouldBe 65
            rarity shouldBe ItemRarity.FINE
            vendorValue shouldBe 30
            gameTypes shouldHaveSize 3
            flags shouldHaveSize 1
            restrictions.shouldBeEmpty()
            id shouldBe 12421
            chatLink shouldBe "[&AgGFMAAA]"
            icon shouldBe "https://render.guildwars2.com/file/1405CDA2DD331A667F0A5DDA5E3FFF7D4A4A2F7B/433615.png"
            assertSoftly(details!!) {
                val consumableDetails = details.shouldBeTypeOf<ConsumableDetails>()
                consumableDetails.type shouldBe "Food"
                consumableDetails.durationMs shouldBe 1800000
                consumableDetails.applyCount shouldBe 1
                consumableDetails.name shouldBe "Nourishment"
                consumableDetails.icon shouldBe "https://render.guildwars2.com/file/779D3F0ABE5B46C09CFC57374DA8CC3A495F291C/436367.png"
                consumableDetails.description shouldBe "80% Chance to Gain Health on Kill\n+60 Healing Power\n+10% Experience from Kills"
            }
        }

    private fun containerAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<ContainerItem>()
            name shouldBe "Small Thorned Bag"
            description shouldBe "Double-click to open."
            type shouldBe ItemType.CONTAINER
            level shouldBe 0
            rarity shouldBe ItemRarity.BASIC
            vendorValue shouldBe 11
            gameTypes shouldHaveSize 5
            flags shouldHaveSize 1
            restrictions.shouldBeEmpty()
            id shouldBe 9335
            chatLink shouldBe "[&AgF3JAAA]"
            icon shouldBe "https://render.guildwars2.com/file/EBC5CEC199D1E51B02756A1C796A65E9D24F04B5/63171.png"
            assertSoftly(details!!) {
                val containerDetails = details.shouldBeTypeOf<ContainerDetails>()
                containerDetails.type shouldBe "Default"
            }
        }

    private fun craftingMaterialAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<CraftingMaterialItem>()
            name shouldBe "Orichalcum Ore"
            description shouldBe "Refine into Ingots."
            type shouldBe ItemType.CRAFTING_MATERIAL
            level shouldBe 0
            rarity shouldBe ItemRarity.BASIC
            vendorValue shouldBe 8
            gameTypes shouldHaveSize 4
            flags shouldHaveSize 1
            restrictions.shouldBeEmpty()
            id shouldBe 19701
            chatLink shouldBe "[&AgH1TAAA]"
            icon shouldBe "https://render.guildwars2.com/file/A6E2C82153BA684E2D05D3FCA09F3E02431366ED/220461.png"
            details.shouldBeNull()
        }

    private fun gatheringAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<GatheringItem>()
            name shouldBe "Frostbitten Orichalcum Mining Pick"
            description shouldBe "Can harvest Orichalcum, Mithril, and all lesser metals.<br>Gain snowflakes when gathering."
            type shouldBe ItemType.GATHERING
            level shouldBe 15
            rarity shouldBe ItemRarity.MASTERWORK
            vendorValue shouldBe 36
            gameTypes shouldHaveSize 4
            flags shouldHaveSize 6
            restrictions.shouldBeEmpty()
            id shouldBe 68376
            chatLink shouldBe "[&AgEYCwEA]"
            icon shouldBe "https://render.guildwars2.com/file/58C7529768D906B5286ADF4C7865970B1D29A2BE/924597.png"
            assertSoftly(details!!) {
                val gatheringDetails = details.shouldBeTypeOf<GatheringDetails>()
                gatheringDetails.type shouldBe "Mining"
            }
        }

    private fun gizmoAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<GizmoItem>()
            name shouldBe "Haunted Gramophone"
            description shouldBe "Click to equip and use this Halloween themed Haunted Gramophone."
            type shouldBe ItemType.GIZMO
            level shouldBe 0
            rarity shouldBe ItemRarity.MASTERWORK
            vendorValue shouldBe 0
            gameTypes shouldHaveSize 5
            flags shouldHaveSize 9
            restrictions.shouldBeEmpty()
            id shouldBe 67391
            chatLink shouldBe "[&AgE/BwEA]"
            icon shouldBe "https://render.guildwars2.com/file/0706600765442A452029B5A01896502B97493BEA/888386.png"
            assertSoftly(details!!) {
                val gizmoDetails = details.shouldBeTypeOf<GizmoDetails>()
                gizmoDetails.type shouldBe "Default"
            }
        }

    private fun jadeTechAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<JadeTechModuleItem>()
            name shouldBe "Recycler: Dragonite"
            description shouldBe "Looted junk items will automatically be turned into dragonite ore."
            type shouldBe ItemType.JADE_TECH_MODULE
            level shouldBe 80
            rarity shouldBe ItemRarity.MASTERWORK
            vendorValue shouldBe 128
            gameTypes shouldHaveSize 5
            flags shouldHaveSize 1
            restrictions.shouldBeEmpty()
            id shouldBe 97656
            chatLink shouldBe "[&AgF4fQEA]"
            icon shouldBe "https://render.guildwars2.com/file/FA35141D0E150D660A741A75F6A31B90A2F19599/2595063.png"
            details.shouldBeNull()
        }

    private fun miniatureAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<MiniatureItem>()
            name shouldBe "Mini Risen Hylek"
            description shouldBe "Double-click to summon this mini to follow you around. Only one mini may be in use at a time."
            type shouldBe ItemType.MINI_PET
            level shouldBe 0
            rarity shouldBe ItemRarity.EXOTIC
            vendorValue shouldBe 50
            gameTypes shouldHaveSize 5
            flags shouldHaveSize 3
            restrictions.shouldBeEmpty()
            id shouldBe 47845
            chatLink shouldBe "[&AgHlugAA]"
            icon shouldBe "https://render.guildwars2.com/file/54C862643766B67646992A650A5FFB3F1A5304B6/638237.png"
            assertSoftly(details!!) {
                val miniatureDetails = details.shouldBeTypeOf<MiniatureDetails>()
                miniatureDetails.minipetId shouldBe 193
            }
        }

    private fun powerCoreAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<PowerCoreItem>()
            name shouldBe "Jade Bot Core: Tier 7"
            description shouldBe "Increases vitality by 190. Required to equip jade bot sensory arrays and service chips."
            type shouldBe ItemType.POWER_CORE
            level shouldBe 80
            rarity shouldBe ItemRarity.RARE
            vendorValue shouldBe 192
            gameTypes shouldHaveSize 5
            flags shouldHaveSize 1
            restrictions.shouldBeEmpty()
            id shouldBe 97020
            chatLink shouldBe "[&AgH8egEA]"
            icon shouldBe "https://render.guildwars2.com/file/C10A05A05140D940C40607D268250D3E6293755A/2595073.png"
            details.shouldBeNull()
        }

    private fun relicAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<RelicItem>()
            name shouldBe "Relic of Isgarren"
            description shouldBe "After evading an enemy's attack, place the Eye of Isgarren on them for a duration. You deal increased strike and condition duration against enemies with the Eye of Isgarren."
            type shouldBe ItemType.RELIC
            level shouldBe 60
            rarity shouldBe ItemRarity.EXOTIC
            vendorValue shouldBe 200
            gameTypes shouldHaveSize 4
            flags shouldHaveSize 2
            restrictions.shouldBeEmpty()
            id shouldBe 99997
            chatLink shouldBe "[&AgGdhgEA]"
            icon shouldBe "https://render.guildwars2.com/file/5FB808F04E427650A84031E46B632DC292A3583F/3122354.png"
        }

    private fun toolAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<SalvageKitItem>()
            name shouldBe "Fine Salvage Kit"
            description shouldBe "Double-click to salvage crafting materials from an item in your inventory. 15% chance of rarer materials. 40% chance of salvaging upgrades."
            type shouldBe ItemType.TOOL
            level shouldBe 0
            rarity shouldBe ItemRarity.FINE
            vendorValue shouldBe 36
            gameTypes shouldHaveSize 6
            flags shouldHaveSize 4
            restrictions.shouldBeEmpty()
            id shouldBe 23041
            chatLink shouldBe "[&AgEBWgAA]"
            icon shouldBe "https://render.guildwars2.com/file/CA01D91B20EB19359FFF313C6DC5C2480A0872B0/66766.png"
            assertSoftly(details!!) {
                val salvageKitDetails = details.shouldBeTypeOf<SalvageKitDetails>()
                salvageKitDetails.type shouldBe "Salvage"
                salvageKitDetails.charges shouldBe 25
            }
        }

    private fun trinketAssertion(item: Item) = assertSoftly(item) {
        it should beOfType<TrinketItem>()
        name shouldBe "Justiciar's Pendant"
        type shouldBe ItemType.TRINKET
        level shouldBe 80
        rarity shouldBe ItemRarity.EXOTIC
        vendorValue shouldBe 528
        gameTypes shouldContainExactly listOf("Activity", "Wvw", "Dungeon", "Pve")
        flags shouldContainExactly listOf(
            "HideSuffix",
            "AccountBound",
            "NoMysticForge",
            "NoSalvage",
            "NoSell",
            "SoulBindOnUse"
        )
        restrictions.shouldBeEmpty()
        id shouldBe 80553
        chatLink shouldBe "[&AgGpOgEA]"
        icon shouldBe "https://render.guildwars2.com/file/E433A51B5D0FB70203480845BAFA23990322CDA3/1633843.png"
        assertSoftly(details!! as TrinketDetails) {
            type shouldBe "Amulet"
            infusionSlots.shouldBeEmpty()
            attributeAdjustment shouldBe 341.44
            assertSoftly(infixUpgrade!!) {
                id shouldBe 1222
                assertSoftly(attributes) { infixUpgradeAttributes ->
                    shouldHaveSize(4)
                    assertSoftly(infixUpgradeAttributes[0]) {
                        attribute shouldBe "Precision"
                        modifier shouldBe 102
                    }
                }
            }
        }
    }

    private fun trophyAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<TrophyItem>()
            name shouldBe "Shiny Bauble"
            description.shouldBeBlank()
            type shouldBe ItemType.TROPHY
            level shouldBe 0
            rarity shouldBe ItemRarity.JUNK
            vendorValue shouldBe 3000
            gameTypes shouldHaveSize 5
            flags shouldHaveSize 4
            restrictions.shouldBeEmpty()
            id shouldBe 70093
            chatLink shouldBe "[&AgHNEQEA]"
            icon shouldBe "https://render.guildwars2.com/file/034B091471E6067C2B0BCC70FE04D2F3AE51F291/1010539.png"
        }


    private fun upgradeComponentAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<UpgradeComponentItem>()
            name shouldBe "Superior Sigil of Concentration"
            description shouldBe "<c=@abilitytype>Element: </c>Enhancement<br>Double-click to apply to a weapon."
            type shouldBe ItemType.UPGRADE_COMPONENT
            level shouldBe 60
            rarity shouldBe ItemRarity.EXOTIC
            vendorValue shouldBe 216
            gameTypes shouldHaveSize 4
            flags shouldHaveSize 1
            restrictions.shouldBeEmpty()
            id shouldBe 72339
            chatLink shouldBe "[&AgGTGgEA]"
            icon shouldBe "https://render.guildwars2.com/file/C501D2CCF95A7B59F15EEDEF9C7D42C2DECE48E7/1201533.png"
            assertSoftly(details!!) {
                val upgradeDetails = details.shouldBeTypeOf<UpgradeDetails>()
                upgradeDetails.type shouldBe "Sigil"
                upgradeDetails.flags shouldHaveSize 19
                upgradeDetails.infusionUpgradeFlags.shouldBeEmpty()
                upgradeDetails.attributeAdjustment shouldBe 0
                assertSoftly(upgradeDetails.infixUpgrade!!) {
                    id shouldBe 1146
                    attributes.shouldBeEmpty()
                    assertSoftly(buff!!) {
                        description shouldBe "+10% Boon Duration"
                        itemId shouldBe 33913
                    }
                }
            }
        }

    private fun weaponAssertion(item: Item) =
        assertSoftly(item) {
            it should beOfType<WeaponItem>()
            name shouldBe "Celestial Bronze Axe of Force"
            description.shouldBeBlank()
            type shouldBe ItemType.WEAPON
            level shouldBe 80
            rarity shouldBe ItemRarity.EXOTIC
            vendorValue shouldBe 0
            defaultSkin shouldBe 3946
            gameTypes shouldHaveSize 3
            flags shouldHaveSize 5
            restrictions.shouldBeEmpty()
            id shouldBe 95356
            chatLink shouldBe "[&AgF8dAEA]"
            icon shouldBe "https://render.guildwars2.com/file/C8EAC643C02F41B918D79031B6060B0CBFE40CAD/64967.png"
            assertSoftly(details!!) {
                val weaponDetails = details.shouldBeTypeOf<WeaponDetails>()
                weaponDetails.type shouldBe "Axe"
                weaponDetails.damageType shouldBe "Choking"
                weaponDetails.minPower shouldBe 857
                weaponDetails.maxPower shouldBe 1048
                weaponDetails.defense shouldBe 0
                weaponDetails.attributeAdjustment shouldBe 341.44
                (weaponDetails.infusionSlots as List<*>).shouldBeEmpty()
                weaponDetails.suffixItemId shouldBe 24615
                weaponDetails.secondarySuffixItemId shouldBe ""
                assertSoftly(weaponDetails.infixUpgrade!!) {
                    id shouldBe 559
                    attributes shouldHaveSize 9
                    attributes shouldContainExactly listOf(
                        InfixUpgradeAttribute("Power", 56.0),
                        InfixUpgradeAttribute("Precision", 56.0),
                        InfixUpgradeAttribute("Toughness", 56.0),
                        InfixUpgradeAttribute("Vitality", 56.0),
                        InfixUpgradeAttribute("CritDamage", 56.0),
                        InfixUpgradeAttribute("Healing", 56.0),
                        InfixUpgradeAttribute("ConditionDamage", 56.0),
                        InfixUpgradeAttribute("BoonDuration", 56.0),
                        InfixUpgradeAttribute("ConditionDuration", 56.0),
                    )
                }
            }
        }

}