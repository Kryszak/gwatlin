package com.kryszak.gwatlin.api.guild

import com.kryszak.gwatlin.api.guild.model.GuildMember
import com.kryszak.gwatlin.api.guild.model.GuildRank
import com.kryszak.gwatlin.api.guild.model.log.GuildLog
import com.kryszak.gwatlin.api.guild.model.stash.GuildStash
import com.kryszak.gwatlin.api.guild.model.team.GuildTeam
import com.kryszak.gwatlin.api.guild.model.treasury.GuildTreasury
import com.kryszak.gwatlin.clients.guild.GuildAuthenticatedClient

/**
 * Client for guild authenticated endpoints
 * @param apiKey account api key
 * @see com.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWGuildAuthenticatedClient(apiKey: String) {

    private val guildClient: GuildAuthenticatedClient = GuildAuthenticatedClient(apiKey)

    /**
     * Retrieves guild log
     * @param id of the guild
     * @param since optional id of starting log entry to return
     * @see com.kryszak.gwatlin.api.guild.model.log.GuildLog
     */
    fun getGuildLog(id: String, since: String? = null): List<GuildLog> {
        return guildClient.getGuildLog(id, since)
    }

    /**
     * Retrieves guild members
     * @param id of guild
     * @see com.kryszak.gwatlin.api.guild.model.GuildMember
     */
    fun getGuildMembers(id: String): List<GuildMember> {
        return guildClient.getGuildMembers(id)
    }

    /**
     * Retrieves guild ranks
     * @param id of guild
     * @see com.kryszak.gwatlin.api.guild.model.GuildRank
     */
    fun getGuildRanks(id: String): List<GuildRank> {
        return guildClient.getGuildRanks(id)
    }

    /**
     * Retrieves guild stash
     * @param id of guild
     * @see com.kryszak.gwatlin.api.guild.model.stash.GuildStash
     */
    fun getGuildStash(id: String): List<GuildStash> {
        return guildClient.getGuildStash(id)
    }

    /**
     * Retrieves guild treasury
     * @param id of guild
     * @see com.kryszak.gwatlin.api.guild.model.treasury.GuildTreasury
     */
    fun getGuildTreasury(id: String): List<GuildTreasury> {
        return guildClient.getGuildTreasury(id)
    }

    /**
     * Retrieves guild teams
     * @param id of guild
     * @see com.kryszak.gwatlin.api.guild.model.team.GuildTeam
     */
    fun getGuildTeams(id: String): List<GuildTeam> {
        return guildClient.getGuildTeams(id)
    }

    /**
     * Retrieves guild upgrade ids (required Leader)
     * @param id of guild
     */
    fun getGuildUpgrades(id: String): List<Int> {
        return guildClient.getGuildUpgrades(id)
    }
}
