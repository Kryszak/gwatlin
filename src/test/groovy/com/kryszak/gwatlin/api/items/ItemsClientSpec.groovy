package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.items.model.item.ItemRarity
import com.kryszak.gwatlin.api.items.model.item.ItemType
import com.kryszak.gwatlin.config.WiremockTest
import kotlinx.serialization.json.JsonElementKt
import kotlinx.serialization.json.JsonObject
import spock.lang.Subject

class ItemsClientSpec extends WiremockTest {

    @Subject
    def itemsClient = new GWItemsClient()

    def "Should get item ids"() {
        given: "Expected item ids"
        def ids = parseResponse("/responses/items/item_ids.json")

        and: "External api is stubbed"
        stubResponse("/items", "/responses/items/item_ids.json")

        when: "Requesting item ids"
        def itemIds = itemsClient.getItemIds()

        then: "Retrieved list matches expected"
        itemIds == ids
    }

    def "Should get item"() {
        given: "Item id"
        def id = 28445

        and: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"
        stubResponseWithLanguage("/items?ids=28445", "/responses/items/item.json", lang)

        when: "Requesting item"
        def items = itemsClient.getItems([id], lang)

        then: "Retrieved item matches expected"
        items.size() == 1
        verifyAll(items.get(0)) {
            name == "Strong Soft Wood Longbow of Fire"
            description == ""
            type == ItemType.WEAPON
            level == 44
            rarity == ItemRarity.MASTERWORK
            vendorValue == 120
            defaultSkin == 3942
            gameTypes.size() == 4
            flags.size() == 1
            restrictions == []
            id == 28445
            chatLink == "[&AgEdbwAA]"
            icon == "https://render.guildwars2.com/file/C6110F52DF5AFE0F00A56F9E143E9732176DDDE9/65015.png"
            verifyAll(details) {
                jsonObjectKeyToString(it, "type") == "LongBow"
                jsonObjectKeyToString(it, "damage_type") == "Physical"
                jsonObjectKeyToInt(it, "min_power") == 385
                jsonObjectKeyToInt(it, "max_power") == 452
                jsonObjectKeyToInt(it, "defense") == 0
                jsonObjectKeyToList(it, "infusion_slots") == []
                jsonObjectKeyToInt(it, "suffix_item_id") == 24547
                jsonObjectKeyToString(it, "secondary_suffix_item_id") == ""
                verifyAll(get("infix_upgrade")) {
                    it instanceof JsonObject
                    jsonObjectKeyToInt(it as JsonObject, "id") == 142
                    jsonObjectKeyToList(it as JsonObject, "attributes").size() == 2
                }
            }
        }
    }

    def jsonObjectKeyToString(JsonObject o, String s) {
        JsonElementKt.getJsonPrimitive(o.get(s)).content
    }

    def jsonObjectKeyToInt(JsonObject o, String s) {
        jsonObjectKeyToString(o, s).toInteger()
    }

    def jsonObjectKeyToList(JsonObject o, String s) {
        JsonElementKt.getJsonArray(o.get(s)).toList()
    }
}
