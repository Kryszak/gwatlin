package com.kryszak.gwatlin.api.gamemechanics

import com.kryszak.gwatlin.api.ApiLanguage
import com.kryszak.gwatlin.api.exception.ApiRequestException
import com.kryszak.gwatlin.config.BaseWiremockTest
import io.kotest.assertions.assertSoftly
import io.kotest.assertions.throwables.shouldThrow
import io.kotest.matchers.collections.shouldHaveSize
import io.kotest.matchers.shouldBe

internal class MountsClientTest : BaseWiremockTest() {

    private val mountsClient = GWMountsClient()

    init {
        should("Get mount skins ids") {
            // given
            stubResponse("/mounts/skins", "/responses/gamemechanics/mount_skin_ids.json")

            // when
            val idsList = mountsClient.getMountSkinsIds()

            // then
            idsList shouldHaveSize 172
        }

        should("Get list of mount skins") {
            // given
            val ids = listOf(1, 2)
            val lang = ApiLanguage.EN

            stubResponse("/mounts/skins?ids=1,2", "/responses/gamemechanics/mount_skins.json", language = lang)

            // when
            val mountSkins = mountsClient.getMountSkins(ids, lang)

            // then
            assertSoftly(mountSkins[0]) {
                id shouldBe 1
                name shouldBe "Raptor"
                icon shouldBe "https://render.guildwars2.com/file/2F4AAA52F573C5425BFCD7525FB70C9E6DCAD791/1766903.png"
                mount shouldBe "raptor"
                assertSoftly(dyeSlots[0]) {
                    colorId shouldBe 19
                    material shouldBe "leather"
                }
            }
        }

        should("Throw exception on non existing mount skin id") {
            // given
            val id = 1000

            stubResponse("/mounts/skins?ids=1000", "/responses/gamemechanics/mount_skins_error.json", responseStatus = 404)

            // when
            val exception = shouldThrow<ApiRequestException> { mountsClient.getMountSkins(listOf(id)) }

            // then
            exception.message shouldBe "RetrieveError(text=all ids provided are invalid)"
        }

        should("Get all mount skins") {
            // given
            val lang = ApiLanguage.EN

            stubResponse("/mounts/skins?ids=all", "/responses/gamemechanics/mount_skins_all.json", language = lang)

            // when
            val mountSkins = mountsClient.getAllMountSkins(lang)

            // then
            mountSkins shouldHaveSize 172
        }

        should("Get mount types ids") {
            // given
            stubResponse("/mounts/types", "/responses/gamemechanics/mount_types_ids.json")

            // when
            val typesIds = mountsClient.getMountTypesIds()

            // then
            typesIds shouldHaveSize 8
        }

        should("Get mount types") {
            // given
            val ids = listOf("griffon", "jackal")
            val lang = ApiLanguage.EN

            stubResponse(
                "/mounts/types?ids=griffon,jackal",
                "/responses/gamemechanics/mount_types.json",
                language = lang
            )

            // when
            val mountTypes = mountsClient.getMountTypes(ids, lang)

            // then
            assertSoftly(mountTypes.get(0)) {
                id shouldBe "griffon"
                name shouldBe "Griffon"
                defaultSkin shouldBe 4
                skins shouldHaveSize 29
                skills shouldHaveSize 1
                assertSoftly(skills[0]) {
                    id shouldBe 40576
                    slot shouldBe "Weapon_1"
                }
            }
        }

        should("Get all mount types") {
            // given
            val lang = ApiLanguage.EN

            stubResponse("/mounts/types?ids=all", "/responses/gamemechanics/mount_types_all.json", language = lang)

            // when
            val mountTypes = mountsClient.getAllMountTypes(lang)

            // then
            mountTypes shouldHaveSize 8
        }

        should("Throw exception on non existing mount type id") {
            // given
            val id = "i_do_not_exist"

            stubResponse("/mounts/types?ids=i_do_not_exist", "/responses/gamemechanics/mount_type_error.json", responseStatus = 404)

            // when
            val exception = shouldThrow<ApiRequestException> { mountsClient.getMountTypes(listOf(id)) }

            // then
            exception.message shouldBe "RetrieveError(text=all ids provided are invalid)"
        }
    }
}