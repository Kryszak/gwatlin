package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.config.WiremockConfig
import spock.lang.Subject

class MaterialsClientSpec extends WiremockConfig {

    @Subject
    def materialsClient = new GWMaterialsClient()

    def "Should get material ids"() {
        given: "Expected material ids"
        def ids = parseResponse("/responses/items/material_ids.json")

        and: "External api is stubbed"
        stubResponse("/materials", "/responses/items/material_ids.json")

        when: "Requesting material ids"
        def materialIds = materialsClient.getMaterialIds()

        then: "Retrieved list matches expected"
        materialIds == ids
    }

    def "Should get material"() {
        given: "Material id"
        def ids = [5]

        and: "External api is stubbed"
        stubResponse("/materials?ids=5&lang=en", "/responses/items/material.json")

        when: "Requesting material"
        def materials = materialsClient.getMaterials(ids, "en")

        then: "Retrieved material matches expected"
        verifyAll(materials.get(0)) {
            id == 5
            name == "Cooking Materials"
            items.size() == 128
            order == 6
        }
    }
}
