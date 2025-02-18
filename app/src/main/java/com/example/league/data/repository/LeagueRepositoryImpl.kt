package com.example.league.data.repository

import com.example.league.data.mappers.toChampion
import com.example.league.data.network.KtorRemoteSource
import com.example.league.domain.models.Champion
import com.example.league.domain.repository.LeagueRepository

class LeagueRepositoryImpl(
    private val api: KtorRemoteSource
): LeagueRepository {


    override suspend fun getChampionsList(): List<Champion> {
        return api.getListOfChampions()
            .map { it.toChampion() }
    }


}