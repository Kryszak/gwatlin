package io.github.kryszak.gwatlin.api.guild

import io.github.kryszak.gwatlin.api.guild.model.GuildMember
import io.github.kryszak.gwatlin.api.guild.model.GuildRank
import io.github.kryszak.gwatlin.api.guild.model.log.GuildLog
import io.github.kryszak.gwatlin.api.guild.model.stash.GuildStash
import io.github.kryszak.gwatlin.api.guild.model.team.GuildTeam
import io.github.kryszak.gwatlin.api.guild.model.treasury.GuildTreasury
import io.github.kryszak.gwatlin.clients.guild.GuildAuthenticatedClient

/**
 * Client for guild authenticated endpoints
 * @param apiKey account api key
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWGuildAuthenticatedClient(apiKey: String) {

    private val guildClient: GuildAuthenticatedClient = GuildAuthenticatedClient(apiKey)

    /**
     * Retrieves guild log
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/guild/:id/log)
     * @param id of the guild
     * @param since optional id of starting log entry to return
     * @see io.github.kryszak.gwatlin.api.guild.model.log.GuildLog
     */
    fun getGuildLog(id: String, since: String? = null): List<GuildLog> {
        return guildClient.getGuildLog(id, since)
    }

    /**
     * Retrieves guild members
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/guild/:id/members)
     * @param id of guild
     * @see io.github.kryszak.gwatlin.api.guild.model.GuildMember
     */
    fun getGuildMembers(id: String): List<GuildMember> {
        return guildClient.getGuildMembers(id)
    }

    /**
     * Retrieves guild ranks
     * * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/guild/:id/ranks)
     * @param id of guild
     * @see io.github.kryszak.gwatlin.api.guild.model.GuildRank
     */
    fun getGuildRanks(id: String): List<GuildRank> {
        return guildClient.getGuildRanks(id)
    }

    /**
     * Retrieves guild stash
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/guild/:id/stash)
     * @param id of guild
     * @see io.github.kryszak.gwatlin.api.guild.model.stash.GuildStash
     */
    fun getGuildStash(id: String): List<GuildStash> {
        return guildClient.getGuildStash(id)
    }

    /**
     * Retrieves guild treasury
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/guild/:id/treasury)
     * @param id of guild
     * @see io.github.kryszak.gwatlin.api.guild.model.treasury.GuildTreasury
     */
    fun getGuildTreasury(id: String): List<GuildTreasury> {
        return guildClient.getGuildTreasury(id)
    }

    /**
     * Retrieves guild teams
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/guild/:id/teams)
     * @param id of guild
     * @see io.github.kryszak.gwatlin.api.guild.model.team.GuildTeam
     */
    fun getGuildTeams(id: String): List<GuildTeam> {
        return guildClient.getGuildTeams(id)
    }

    /**
     * Retrieves guild upgrade ids (required Leader)
     * Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:2/guild/:id/upgrades)
     * @param id of guild
     */
    fun getGuildUpgrades(id: String): List<Int> {
        return guildClient.getGuildUpgrades(id)
    }
}
