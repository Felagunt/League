package com.example.league.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class PassiveDto(
    @SerialName("description")
    val description: String? = "",
    @SerialName("image")
    val image: ImageDto? = ImageDto(),
    @SerialName("name")
    val name: String? = ""
)