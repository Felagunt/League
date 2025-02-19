package com.example.league.domain.models

data class ChampionDetails(
    val id: String? = "",
    val image: ImageModel? = ImageModel(),
    val key: String? = "",
    val lore: String? = "",
    val blurb: String? = "",
    val name: String? = "",
    val passive: Passive? = Passive(),
    val spells: List<Spell> = emptyList(),
    val tags: List<String> = emptyList(),
    val title: String? = ""
)