package io.github.kryszak.gwatlin.clients.apiinfo

import io.github.kryszak.gwatlin.api.miscellaneous.model.SchemaVersion
import io.github.kryszak.gwatlin.clients.achievements.ApiInfoResponse
import io.github.kryszak.gwatlin.http.BaseHttpClient

internal class ApiInfoClient : BaseHttpClient() {

    private val baseEndpoint: String = ".json?v=latest"

    fun getSchemaVersions(): List<SchemaVersion> {
        val apiInfo: ApiInfoResponse = getRequest(baseEndpoint)
        return apiInfo.schemaVersions
    }
}
