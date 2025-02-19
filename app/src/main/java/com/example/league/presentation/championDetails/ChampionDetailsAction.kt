package com.example.league.presentation.championDetails

sealed interface ChampionDetailsAction {
    data object OnBackClick: ChampionDetailsAction
}