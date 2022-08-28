package com.kryszak.gwatlin.api.shared

import com.google.gson.annotations.SerializedName

enum class ItemBinding {
    @SerializedName("Account")
    ACCOUNT,
    @SerializedName("Character")
    CHARACTER
}