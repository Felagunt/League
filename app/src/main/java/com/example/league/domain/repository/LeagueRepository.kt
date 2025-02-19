package com.example.league.domain.repository

import com.example.league.domain.models.Champion
import com.example.league.domain.models.ChampionDetails

interface LeagueRepository
{
    suspend fun getChampionsList(): List<Champion>

    suspend fun getChampionDetails(name: String): ChampionDetails?
}