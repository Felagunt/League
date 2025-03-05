package com.example.league.domain.repository

import com.example.league.core.domain.DataError
import com.example.league.core.domain.Result
import com.example.league.domain.models.Champion
import com.example.league.domain.models.ChampionDetails

interface LeagueRepository
{
    suspend fun getChampionsList(): Result<List<Champion>, DataError.Remote>

    suspend fun getChampionDetails(name: String): Result<ChampionDetails?, DataError>
}