package com.example.league.data.repository

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


    override suspend fun getChampionsList(): List<Champion> {
        return api.getListOfChampions()
            .champion.values.toList()
            .map { it.toChampion() }//TODO is it crazy?
            //.champion?.map { it.toChampion() } ?: emptyList()
            //.map { it.toChampion() }
    }

    override suspend fun getChampionDetails(name: String): ChampionDetails? {
        return api.getChampionDetails(name)
            .champion
            .map { it.value.toChampionDetails() }
            .firstOrNull()
    }
}