package io.github.kryszak.gwatlin.api.commerce.model.transaction

import kotlinx.serialization.Contextual
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import java.time.OffsetDateTime
import java.time.ZonedDateTime

/**
 * Data model for transaction object
 */
@Serializable
data class Transaction(
    val id: Long,
    @SerialName("item_id")
    val itemId: Long,
    val price: Long,
    val quantity: Int,
    @Contextual
    val created: OffsetDateTime,
    @Contextual
    val purchased: OffsetDateTime? = null,
)
