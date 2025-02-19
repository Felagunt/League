package com.example.league.domain.models

data class Spell(
    val id: String? = null,
    val name: String? = null,
    val description: String? = null,
    val image: ImageModel? = ImageModel()
)
