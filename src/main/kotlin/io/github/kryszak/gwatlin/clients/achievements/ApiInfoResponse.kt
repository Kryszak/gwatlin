package io.github.kryszak.gwatlin.clients.achievements

import io.github.kryszak.gwatlin.api.apiinfo.model.SchemaVersion
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ApiInfoResponse(
    @SerialName("schema_versions") val schemaVersions: List<SchemaVersion>
)