package com.example.league.presentation.championDetails

import com.example.league.domain.models.ChampionDetails

data class ChampionDetailsState(
    val isLoading: Boolean = true,
    val errorMsg: String? = null,
    val championDetails: ChampionDetails? = null
)
