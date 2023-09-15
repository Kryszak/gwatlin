package com.kryszak.gwatlin.api.shared

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

/**
 * Enum for possible item binding values
 */
@Serializable
enum class ItemBinding {
    @SerialName("Account")
    ACCOUNT,
    @SerialName("Character")
    CHARACTER
}