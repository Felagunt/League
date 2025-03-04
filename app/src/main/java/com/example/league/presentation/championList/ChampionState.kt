package com.example.league.presentation.championList

import com.example.league.domain.models.Champion

data class ChampionState(
    val championList: List<Champion> = emptyList(),
    val searchChampionList: List<Champion> = emptyList(),
    val searchQuery: String? = null,
    val errorMsg: String? = null,
    val isLoading: Boolean = true
)
