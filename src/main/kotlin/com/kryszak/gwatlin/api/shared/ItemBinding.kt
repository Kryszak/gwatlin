package com.kryszak.gwatlin.api.shared

import com.google.gson.annotations.SerializedName

/**
 * Enum for possible item binding values
 */
enum class ItemBinding {
    @SerializedName("Account")
    ACCOUNT,
    @SerializedName("Character")
    CHARACTER
}