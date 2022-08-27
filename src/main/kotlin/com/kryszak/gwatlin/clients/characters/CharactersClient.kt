package com.kryszak.gwatlin.clients.characters

import com.kryszak.gwatlin.api.characters.model.Character
import com.kryszak.gwatlin.api.characters.model.character.*
import com.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentTab
import com.kryszak.gwatlin.api.characters.model.character.sab.CharacterSAB
import com.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class CharactersClient(apiKey: String) : AuthenticatedHttpClient(
    apiKey,
    // Latest schema version with changes to the characters endpoint
    "2021-07-15T13:00:00.000Z"
) {
    private val endpoint = "characters"

    fun getCharacter(characterName: String) = getRequestAuth<Character>("$endpoint/$characterName")

    fun getBackstory(characterName: String) = getRequestAuth<List<String>>("$endpoint/$characterName/backstory")

    fun getBuildTab(characterName: String, tab: Int) =
        getRequestAuth<BuildTab>("$endpoint/$characterName/buildtabs?tab=$tab")

    fun getBuildTabs(characterName: String) =
        getRequestAuth<List<BuildTab>>("$endpoint/$characterName/buildtabs?tabs=all")

    fun getCore(characterName: String) = getRequestAuth<Character>("$endpoint/$characterName")

    fun getCrafting(characterName: String) = getRequestAuth<List<Crafting>>("$endpoint/$characterName/crafting")

    fun getEquipment(characterName: String) = getRequestAuth<CharacterEquipment>("$endpoint/$characterName/equipment")

    fun getEquipmentTab(characterName: String, tab: Int) =
        getRequestAuth<EquipmentTab>("$endpoint/$characterName/equipmenttabs?tab=$tab")

    fun getEquipmentTabs(characterName: String) =
        getRequestAuth<List<EquipmentTab>>("$endpoint/$characterName/equipmenttabs?tabs=all")

    // TODO
    fun getInventory(characterName: String) = getRequestAuth<Character>("$endpoint/$characterName")

    fun getRecipes(characterName: String) = getRequestAuth<CharacterRecipes>("$endpoint/$characterName/recipes")

    // TODO
    fun getSkills(characterName: String) = getRequestAuth<List<CharacterSkills>>("$endpoint/$characterName/skills")

    // TODO
    fun getSpecializations(characterName: String) = getRequestAuth<Character>("$endpoint/$characterName/specializations")

    fun getTraining(characterName: String) = getRequestAuth<CharacterTraining>("$endpoint/$characterName/training")

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