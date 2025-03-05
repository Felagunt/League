package com.example.league.data.mappers

import com.example.league.data.dto.ChampionDto
import com.example.league.data.dto.ChampionResponseDto
import com.example.league.data.dto.ImageDto
import com.example.league.data.dto.PassiveDto
import com.example.league.data.dto.SpellDto
import com.example.league.domain.models.Champion
import com.example.league.domain.models.ChampionDetails
import com.example.league.domain.models.ImageModel
import com.example.league.domain.models.Passive
import com.example.league.domain.models.Spell

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
fun Map<String, ChampionDto>.toChampionList(): List<Champion> =
    this.values.toList().map { it.toChampion() }

//fun ChampionResponseDto.toChampionList(): List<Champion> =
//    this.champion?.toList()!!.map { it.toChampion() }

fun Map<String, ChampionDto>.toChampionDetails(): ChampionDetails? =
    this.values.map { it.toChampionDetails() }.firstOrNull()

fun ChampionDto.toChampionDetails(): ChampionDetails {
    return ChampionDetails(
        id = id,
        image = image?.toImageModel(),
        key = key,
        lore = lore,
        blurb = blurb,
        name = name,
        passive = passive?.toPassive(),
        spells = spells.map { it.toSpell() },
        tags = tags,
        title = title
    )
}

fun ImageDto.toImageModel(): ImageModel {
    return ImageModel(
        full = full,
        group = group
    )
}

fun PassiveDto.toPassive(): Passive {
    return Passive(
        description = description,
        image = image?.toImageModel(),
        name = name
    )
}

fun SpellDto.toSpell(): Spell {
    return Spell(
        id = id,
        name = name,
        description = description,
        image = image?.toImageModel()
    )
}
//
//fun ChampionDto.toChampionDetails(): ChampionDetails {
//    return ChampionDetails(
//        id = id,
//        image = image?.toImageModel(),
//        key = key,
//        lore = lore,
//        blurb = blurb,
//        name = name,
//        tags = tags,
//        title = title,
//        passive = PassiveDto().toPassive(),
//        spells = spells.map { it.toSpell() }
////        spells = spells.mapIndexed { index, spellDto ->
////            spellDto.toSpell()
////       }
//    )
//}