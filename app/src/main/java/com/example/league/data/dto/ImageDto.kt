package com.example.league.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ImageDto(
    @SerialName("full")
    val full: String? = "",
    @SerialName("group")
    val group:String? = ""
)