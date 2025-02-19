package com.example.league.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionResponseDto (
    @SerialName("data")
    val champion: Map<String, ChampionDto> = emptyMap(),
    //val champion: List<ChampionDto>? = emptyList(),
    @SerialName("format")
    val format: String? = "",
    @SerialName("type")
    val type: String? = "",
    @SerialName("version")
    val version: String? = ""
    )

