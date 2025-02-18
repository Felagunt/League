package com.example.league.data.dto

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class ChampionDto(
    @SerialName("blurb")
    val blurb: String,
    @SerialName("id")
    val id: String? = "",
    @SerialName("image")
    val image: ImageDto? = ImageDto(),
    @SerialName("key")
    val key: String? = "",
    @SerialName("lore")
    val lore: String? = "",
    @SerialName("name")
    val name: String? = "",
    @SerialName("passive")
    val passive: PassiveDto? = PassiveDto(),
    @SerialName("spells")
    val spells: List<SpellDto> = listOf(),
    @SerialName("tags")
    val tags: List<String> = listOf(),
    @SerialName("title")
    val title: String? = ""
)