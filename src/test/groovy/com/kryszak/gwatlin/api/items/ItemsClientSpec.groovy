package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.items.model.item.ItemRarity
import com.kryszak.gwatlin.api.items.model.item.ItemType
import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class ItemsClientSpec extends WiremockConfig {

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

        and: "External api is stubbed"
        stubResponse("/items?ids=28445&lang=en", "/responses/items/item.json")

        when: "Requesting item"
        def items = itemsClient.getItems([id], "en")

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
        }
    }
}
