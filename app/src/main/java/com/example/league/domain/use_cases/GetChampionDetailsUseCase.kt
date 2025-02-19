package com.example.league.domain.use_cases

import coil3.network.HttpException
import com.example.league.core.Resource
import com.example.league.domain.models.ChampionDetails
import com.example.league.domain.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException

class GetChampionDetailsUseCase(
    private val repository: LeagueRepository
) {

    operator fun invoke(name: String): Flow<Resource<ChampionDetails>> = flow {
        try {
            val championDetails = repository.getChampionDetails(name)
            championDetails?.let {
                emit(Resource.Success(championDetails))
            }
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}