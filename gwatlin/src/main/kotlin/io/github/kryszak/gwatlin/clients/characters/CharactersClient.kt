package io.github.kryszak.gwatlin.clients.characters

import io.github.kryszak.gwatlin.api.characters.model.Character
import io.github.kryszak.gwatlin.api.characters.model.character.*
import io.github.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentItem
import io.github.kryszak.gwatlin.api.characters.model.character.equipment.EquipmentTab
import io.github.kryszak.gwatlin.api.characters.model.character.inventory.Bag
import io.github.kryszak.gwatlin.api.characters.model.character.sab.CharacterSAB
import io.github.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class CharactersClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {
    private val endpoint = "/characters"

    fun getCharacters() = getRequestAuth<List<String>>(endpoint)

    fun getCharacter(characterName: String) = getRequestAuth<Character>("$endpoint/$characterName")

    fun getBackstory(characterName: String) =
        getRequestAuth<Map<String, List<String>>>("$endpoint/$characterName/backstory").values.firstOrNull()

    fun getBuildTab(characterName: String, tab: Int) =
        getRequestAuth<BuildTab>("$endpoint/$characterName/buildtabs?tab=$tab")

    fun getBuildTabs(characterName: String) =
        getRequestAuth<List<BuildTab>>("$endpoint/$characterName/buildtabs?tabs=all")

    fun getCore(characterName: String) = getRequestAuth<CharacterCore>("$endpoint/$characterName/core")

    fun getCrafting(characterName: String) =
        getRequestAuth<Map<String, List<Crafting>>>("$endpoint/$characterName/crafting").values.firstOrNull()

    fun getEquipment(characterName: String) =
        getRequestAuth<Map<String, List<EquipmentItem>>>("$endpoint/$characterName/equipment").values.firstOrNull()

    fun getEquipmentTab(characterName: String, tab: Int) =
        getRequestAuth<EquipmentTab>("$endpoint/$characterName/equipmenttabs?tab=$tab")

    fun getEquipmentTabs(characterName: String) =
        getRequestAuth<List<EquipmentTab>>("$endpoint/$characterName/equipmenttabs?tabs=all")

    fun getInventory(characterName: String) =
        getRequestAuth<Map<String, List<Bag?>>>("$endpoint/$characterName/inventory").values.firstOrNull()

    fun getRecipes(characterName: String) =
        getRequestAuth<Map<String, List<Int>>>("$endpoint/$characterName/recipes").values.firstOrNull()

    fun getTraining(characterName: String) =
        getRequestAuth<Map<String, List<Training>>>("$endpoint/$characterName/training").values.firstOrNull()

    fun getActiveBuildTab(characterName: String) =
        getRequestAuth<BuildTab>("$endpoint/$characterName/buildtabs/active")

    fun getActiveEquipmentTab(characterName: String) =
        getRequestAuth<EquipmentTab>("$endpoint/$characterName/equipmenttabs/active")

    fun getHeropoints(characterName: String) =
        getRequestAuth<List<String>>("$endpoint/$characterName/heropoints")

    fun getQuests(characterName: String) = getRequestAuth<List<Int>>("$endpoint/$characterName/quests")

    fun getSAB(characterName: String) = getRequestAuth<CharacterSAB>("$endpoint/$characterName/sab")

    fun getSkills(characterName: String) = getRequestAuth<CharacterSkills>("$endpoint/$characterName/skills")

    fun getSpecializations(characterName: String) = getRequestAuth<CharacterSpecialization>("$endpoint/$characterName/specializations")
}