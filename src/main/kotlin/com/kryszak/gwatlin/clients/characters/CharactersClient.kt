package com.kryszak.gwatlin.clients.characters

import com.kryszak.gwatlin.api.characters.model.Character
import com.kryszak.gwatlin.api.characters.model.character.*
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItem
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentTab
import com.kryszak.gwatlin.api.characters.model.character.inventory.Bag
import com.kryszak.gwatlin.api.characters.model.character.sab.CharacterSAB
import com.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class CharactersClient(apiKey: String) : AuthenticatedHttpClient(
    apiKey,
    // Latest schema version with changes to the characters endpoint
    "2021-07-15T13:00:00.000Z"
) {
    private val endpoint = "characters"

    fun getCharacter(characterName: String) = getRequestAuth<Character>("$endpoint/$characterName")

    fun getBackstory(characterName: String) =
        getRequestAuth<Map<String, List<String>>>("$endpoint/$characterName/backstory").values.firstOrNull()

    fun getBuildTab(characterName: String, tab: Int) =
        getRequestAuth<BuildTab>("$endpoint/$characterName/buildtabs?tab=$tab")

    fun getBuildTabs(characterName: String) =
        getRequestAuth<List<BuildTab>>("$endpoint/$characterName/buildtabs?tabs=all")

    fun getCore(characterName: String) = getRequestAuth<CharacterCore>("$endpoint/$characterName")

    fun getCrafting(characterName: String) =
        getRequestAuth<Map<String, List<Crafting>>>("$endpoint/$characterName/crafting").values.firstOrNull()

    fun getEquipment(characterName: String) =
        getRequestAuth<Map<String, List<EquipmentItem>>>("$endpoint/$characterName/equipment").values.firstOrNull()

    fun getEquipmentTab(characterName: String, tab: Int) =
        getRequestAuth<EquipmentTab>("$endpoint/$characterName/equipmenttabs?tab=$tab")

    fun getEquipmentTabs(characterName: String) =
        getRequestAuth<List<EquipmentTab>>("$endpoint/$characterName/equipmenttabs?tabs=all")

    fun getInventory(characterName: String) =
        getRequestAuth<Map<String, List<Bag>>>("$endpoint/$characterName/inventory").values.firstOrNull()

    fun getRecipes(characterName: String) =
        getRequestAuth<Map<String, List<String>>>("$endpoint/$characterName/recipes").values.firstOrNull()

    fun getTraining(characterName: String) =
        getRequestAuth<Map<String, Training>>("$endpoint/$characterName/training").values.firstOrNull()

    fun getActiveBuildTab(characterName: String) =
        getRequestAuth<BuildTab>("$endpoint/$characterName/buildtabs/active")

    fun getActiveEquipmentTab(characterName: String) =
        getRequestAuth<EquipmentTab>("$endpoint/$characterName/equipmenttabs/active")

    // "API not active"
    //fun getDungeons(characterName: String) = getRequestAuth<Character>("$endpoint/$characterName/dungeons")

    fun getHeropoints(characterName: String) =
        getRequestAuth<List<String>>("$endpoint/$characterName/heropoints")

    fun getQuests(characterName: String) = getRequestAuth<List<Int>>("$endpoint/$characterName/quests")

    fun getSAB(characterName: String) = getRequestAuth<CharacterSAB>("$endpoint/$characterName/sab")
}