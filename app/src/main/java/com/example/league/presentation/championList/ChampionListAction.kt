package com.example.league.presentation.championList

import com.example.league.domain.models.Champion

sealed interface ChampionListAction {
    data class OnChampionClick(val champion: Champion): ChampionListAction
    data class OnSearchQueryChange(val query: String): ChampionListAction
}