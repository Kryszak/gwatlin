package com.kryszak.gwatlin.clients.guild

import com.kryszak.gwatlin.api.guild.model.log.GuildLog
import com.kryszak.gwatlin.http.AuthenticatedHttpClient

internal class GuildAuthenticatedClient(apiKey: String) : AuthenticatedHttpClient(apiKey) {

    private val guildEndpoint = "guild"

    fun getGuildLog(id: String, since: String? = ""): List<GuildLog> {
        val sinceQuery = formSinceQuery(since)
        return getRequestAuth("$guildEndpoint/$id/log$sinceQuery")
    }

    private fun formSinceQuery(since: String?): String {
        return when (since) {
            "", null -> ""
            else -> "?since=$since"
        }
    }
}