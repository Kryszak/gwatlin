package io.github.kryszak.gwatlin.api.characters

import io.github.kryszak.gwatlin.clients.characters.CharactersClient

/**
 * Client for characters endpoint. Doesn't include the /skills and /specializations sub-endpoints, as these
 * are not available anymore in the schema version that's being used by the internal client. Use buildtabs for that.
 * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/characters).
 * @param apiKey account api key
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWCharactersClient(apiKey: String) {

    private val charactersClient: CharactersClient = CharactersClient(apiKey)

    /**
     * Retrieves the list of characters
     */
    fun getCharacters() = charactersClient.getCharacters()

    /**
     * Retrieves main character info
     */
    fun getCharacter(characterName: String) = charactersClient.getCharacter(characterName)

    /**
     * Retrieves the character's backstory.
     */
    fun getBackstory(characterName: String) = charactersClient.getBackstory(characterName)

    /**
     * Retrieves the specified BuildTab
     */
    fun getBuildTab(characterName: String, tab: Int) = charactersClient.getBuildTab(characterName, tab)

    /**
     * Retrieves all buildtabs
     */
    fun getBuildTabs(characterName: String) = charactersClient.getBuildTabs(characterName)

    /**
     * Retrieves core character info
     */
    fun getCore(characterName: String) = charactersClient.getCore(characterName)

    /**
     * Retrieves character crafting info
     */
    fun getCrafting(characterName: String) = charactersClient.getCrafting(characterName)

    /**
     * Retrieves the character's equipment
     */
    fun getEquipment(characterName: String) = charactersClient.getEquipment(characterName)

    /**
     * Retrieves the specified EquipmentTab
     */
    fun getEquipmentTab(characterName: String, tab: Int) = charactersClient.getEquipmentTab(characterName, tab)

    /**
     * Retrieves all EquipmentTabs
     */
    fun getEquipmentTabs(characterName: String) = charactersClient.getEquipmentTabs(characterName)

    /**
     * Retrieves the current inventory contents, structured by bag
     */
    fun getInventory(characterName: String) = charactersClient.getInventory(characterName)

    /**
     * Retrieves all learned recipes for this character (recipes are shared account wide though)
     */
    fun getRecipes(characterName: String) = charactersClient.getRecipes(characterName)

    /**
     * Retrieves info about this characters' training, like specializations and skills
     */
    fun getTraining(characterName: String) = charactersClient.getTraining(characterName)

    /**
     * Retrieves the currently active BuildTab
     */
    fun getActiveBuildTab(characterName: String) = charactersClient.getActiveBuildTab(characterName)

    /**
     * Retrieves the currently active EquipmentTab
     */
    fun getActiveEquipmentTab(characterName: String) = charactersClient.getActiveEquipmentTab(characterName)

    /**
     * Retrieves all Heropoints that this character has claimed
     */
    fun getHeropoints(characterName: String) = charactersClient.getHeropoints(characterName)

    /**
     * Retrieves all quests that this character has done
     */
    fun getQuests(characterName: String) = charactersClient.getQuests(characterName)

    /**
     * Retrieves infos about this character's SAB progress and unlocks
     */
    fun getSAB(characterName: String) = charactersClient.getSAB(characterName)
}