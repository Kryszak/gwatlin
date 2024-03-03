package io.github.kryszak.gwatlin.http.serializers

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.BuffConversion
import io.github.kryszak.gwatlin.api.guild.model.log.GuildLogInvited
import io.github.kryszak.gwatlin.api.items.model.item.ArmorItem
import io.github.kryszak.gwatlin.api.items.model.item.ItemRarity
import io.github.kryszak.gwatlin.api.items.model.item.ItemType
import io.kotest.core.spec.style.FunSpec
import io.kotest.matchers.shouldBe

class SerialNameDelegateTest : FunSpec({
    test("Item->Armor serialname") {
        val armor = ArmorItem(0,"","","","",ItemRarity.FINE,0,0)
        armor.type shouldBe ItemType.ARMOR
    }
    test("Fact->BuffConversion serialname") {
        val bc = BuffConversion(null,null,0.0f,"","")
        bc.type shouldBe "BuffConversion"
    }
    test("GuildLog->GuildLogInvited serialname") {
        val gli = GuildLogInvited(0,"","","")
        gli.type shouldBe "invited"
    }

})
