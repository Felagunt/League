package com.example.league.data.repository

import com.example.league.core.domain.DataError
import com.example.league.core.domain.Result
import com.example.league.core.domain.map
import com.example.league.data.mappers.toChampion
import com.example.league.data.mappers.toChampionDetails
import com.example.league.data.mappers.toChampionList
import com.example.league.data.network.KtorRemoteSource
import com.example.league.domain.models.Champion
import com.example.league.domain.models.ChampionDetails
import com.example.league.domain.repository.LeagueRepository

class LeagueRepositoryImpl(
    private val api: KtorRemoteSource
): LeagueRepository {


    override suspend fun getChampionsList(): Result<List<Champion>, DataError.Remote> {
        return api.getListOfChampions().map { it.champion.toChampionList() }


    }

    override suspend fun getChampionDetails(name: String): Result<ChampionDetails?, DataError> {
        return api.getChampionDetails(name)
            .map { it.champion.toChampionDetails() }
    }
}