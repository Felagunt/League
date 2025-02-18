package com.example.league.app

import kotlinx.serialization.Serializable

sealed interface Route{

    @Serializable
    data object ChampionGraph: Route

    @Serializable
    data object ChampionList: Route

    @Serializable
    data class ChampionDetails(val name: String): Route
}