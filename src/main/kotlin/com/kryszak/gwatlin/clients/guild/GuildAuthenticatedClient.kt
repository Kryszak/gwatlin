package com.kryszak.gwatlin.clients.guild

import com.kryszak.gwatlin.api.guild.model.GuildMember
import com.kryszak.gwatlin.api.guild.model.GuildRank
import com.kryszak.gwatlin.api.guild.model.log.GuildLog
import com.kryszak.gwatlin.api.guild.model.stash.GuildStash
import com.kryszak.gwatlin.api.guild.model.team.GuildTeam
import com.kryszak.gwatlin.api.guild.model.treasury.GuildTreasury
import com.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class GuildAuthenticatedClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val guildEndpoint = "guild"

    fun getGuildLog(id: String, since: String? = ""): List<GuildLog> {
        val sinceQuery = formSinceQuery(since)
        return getRequestAuth("$guildEndpoint/$id/log$sinceQuery")
    }

    fun getGuildMembers(id: String): List<GuildMember> {
        return getRequestAuth("$guildEndpoint/$id/members")
    }

    fun getGuildRanks(id: String): List<GuildRank> {
        return getRequestAuth("$guildEndpoint/$id/ranks")
    }

    fun getGuildStash(id: String): List<GuildStash> {
        return getRequestAuth("$guildEndpoint/$id/stash")
    }

    fun getGuildTreasury(id: String): List<GuildTreasury> {
        return getRequestAuth("$guildEndpoint/$id/treasury")
    }

    fun getGuildTeams(id: String): List<GuildTeam> {
        return getRequestAuth("$guildEndpoint/$id/teams")
    }

    fun getGuildUpgrades(id: String): List<Int> {
        return getRequestAuth("$guildEndpoint/$id/upgrades")
    }

    private fun formSinceQuery(since: String?): String {
        return when (since) {
            "", null -> ""
            else -> "?since=$since"
        }
    }
}
