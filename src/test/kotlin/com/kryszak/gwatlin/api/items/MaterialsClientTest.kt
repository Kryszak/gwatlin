package com.kryszak.gwatlin.api.items

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class MaterialsClientTest : BaseWiremockTest() {

    private val materialsClient = GWMaterialsClient()

    init {
        should("Get material ids") {
            // given
            stubResponse("/materials", "/responses/items/material_ids.json")

            // when
            val materialIds = materialsClient.getMaterialIds()

            // then
            materialIds shouldHaveSize 9
        }

        should("Get material") {
            // given
            val ids = listOf(5)
            val lang = ApiLanguage.EN

            stubResponse("/materials?ids=5", "/responses/items/material.json", language = lang)

            // when
            val materials = materialsClient.getMaterials(ids, lang)

            // then
            assertSoftly(materials[0]) {
                id shouldBe 5
                name shouldBe "Cooking Materials"
                items shouldHaveSize 128
                order shouldBe 6
            }
        }
    }
}