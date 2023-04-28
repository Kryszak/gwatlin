package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.WiremockSpec
import spock.lang.Subject

class MaterialsClientSpec extends WiremockSpec {

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

        and: "language"
        def lang = ApiLanguage.EN

        and: "External api is stubbed"

        and: "External api is stubbed"
        stubResponseWithLanguage("/materials?ids=5", "/responses/items/material.json", lang)

        when: "Requesting material"
        def materials = materialsClient.getMaterials(ids, lang)

        then: "Retrieved material matches expected"
        verifyAll(materials.get(0)) {
            id == 5
            name == "Cooking Materials"
            items.size() == 128
            order == 6
        }
    }
}
