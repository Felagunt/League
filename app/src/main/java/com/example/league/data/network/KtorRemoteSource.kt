package com.example.league.data.network

import com.example.league.data.dto.ChampionResponseDto
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get

private val BASE_URL = "https://ddragon.leagueoflegends.com/cdn/9.19.1/data/en_US/"

class KtorRemoteSource(
    private val httpClient: HttpClient
) {


    suspend fun getListOfChampions(): ChampionResponseDto {
        return httpClient.get(
            urlString = "$BASE_URL/champion.json"
        ).body()
    }

    suspend fun getChampionDetails(name: String): ChampionResponseDto {
        return httpClient.get(
            urlString = "$BASE_URL/champion/$name"
        ).body()
    }
}
