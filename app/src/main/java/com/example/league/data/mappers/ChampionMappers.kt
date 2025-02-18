package com.example.league.data.mappers

import com.example.league.data.dto.ChampionDto
import com.example.league.data.dto.ImageDto
import com.example.league.domain.models.Champion

fun ChampionDto.toChampion(): Champion {
    return Champion(
        id = id!!,
        image = image!!.full!!,
        key = key!!,
        blurb = blurb,
        name = name!!,
        lore = lore!!,
        title = title!!
    )
}