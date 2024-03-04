package io.github.kryszak.gwatlin.http.serializers

import io.github.kryszak.gwatlin.api.gamemechanics.model.facts.BuffConversion
import io.github.kryszak.gwatlin.api.guild.model.log.GuildLogInvited
import io.github.kryszak.gwatlin.api.items.model.item.ArmorItem
import io.github.kryszak.gwatlin.api.items.model.item.ItemRarity
import io.github.kryszak.gwatlin.api.items.model.item.ItemType
import io.kotest.core.spec.style.ShouldSpec
import io.kotest.matchers.shouldBe

/**
 * Ensure the [SerialNameDelegate] works as expected by checking a couple of selected classes.
 * The values used in instance creation don't matter.
 */
internal class SerialNameDelegateTest : ShouldSpec({
    should("Item->Armor serialname") {
        val armor = ArmorItem(0,"","","","",ItemRarity.FINE,0,0)
        armor.type shouldBe ItemType.ARMOR
    }
    should("Fact->BuffConversion serialname") {
        val bc = BuffConversion(null,null,0.0f,"","")
        bc.type shouldBe "BuffConversion"
    }
    should("GuildLog->GuildLogInvited serialname") {
        val gli = GuildLogInvited(0,"","","")
        gli.type shouldBe "invited"
    }

})
