package io.github.kryszak.gwatlin.api.apiinfo

import io.github.kryszak.gwatlin.api.apiinfo.model.SchemaVersion
import io.github.kryszak.gwatlin.clients.apiinfo.ApiInfoClient

/**
 * Client for fetching available schema versions. Documentation can be found in the [GW2 Wiki](https://wiki.guildwars2.com/wiki/API:Main#Schema_Versions).
 * @see io.github.kryszak.gwatlin.api.exception.ApiRequestException for errors
 */
class GWApiInfoClient {

    private val client: ApiInfoClient = ApiInfoClient()

    /**
     * Retrieves list of all existing schema versions
     * @return list of schema versions
     */
    fun getSchemaVersions(): List<SchemaVersion> {
        return client.getSchemaVersions()
    }
}