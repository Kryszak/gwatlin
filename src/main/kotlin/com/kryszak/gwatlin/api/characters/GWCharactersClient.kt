package com.kryszak.gwatlin.api.characters

import com.kryszak.gwatlin.clients.characters.CharactersClient

class GWCharactersClient(apiKey: String) {

    private val charactersClient: CharactersClient = CharactersClient(apiKey)

    fun getCharacter(characterName: String) = charactersClient.getCharacter(characterName)

    fun getBackstory(characterName: String) = charactersClient.getBackstory(characterName)

    fun getBuildTab(characterName: String, tab: Int) = charactersClient.getBuildTab(characterName, tab)

    fun getBuildTabs(characterName: String) = charactersClient.getBuildTabs(characterName)

    fun getCore(characterName: String) = charactersClient.getCore(characterName)

    fun getCrafting(characterName: String) = charactersClient.getCrafting(characterName)

    fun getEquipment(characterName: String) = charactersClient.getEquipment(characterName)

    fun getEquipmentTab(characterName: String, tab: Int) = charactersClient.getEquipmentTab(characterName, tab)

    fun getEquipmentTabs(characterName: String) = charactersClient.getEquipmentTabs(characterName)

    fun getInventory(characterName: String) = charactersClient.getInventory(characterName)

    fun getRecipes(characterName: String) = charactersClient.getRecipes(characterName)


    fun getTraining(characterName: String) = charactersClient.getTraining(characterName)

    fun getActiveBuildTab(characterName: String) = charactersClient.getActiveBuildTab(characterName)

    fun getActiveEquipmentTab(characterName: String) = charactersClient.getActiveEquipmentTab(characterName)

    // "API not active"
    //fun getDungeons(characterName: String) = charactersClient.getDungeons(characterName)

    fun getHeropoints(characterName: String) = charactersClient.getHeropoints(characterName)

    fun getQuests(characterName: String) = charactersClient.getQuests(characterName)

    fun getSAB(characterName: String) = charactersClient.getSAB(characterName)
}