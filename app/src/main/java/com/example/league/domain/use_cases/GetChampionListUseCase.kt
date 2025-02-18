package com.example.league.domain.use_cases

import coil3.network.HttpException
import com.example.league.core.Resource
import com.example.league.domain.models.Champion
import com.example.league.domain.repository.LeagueRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.io.IOException

class GetChampionListUseCase(
    private val repository: LeagueRepository
) {

    operator fun invoke(): Flow<Resource<List<Champion>>> = flow {
        try {
            val championList = repository.getChampionsList()
            emit(Resource.Success(championList))
        } catch (e: HttpException) {
            emit(Resource.Error(e.localizedMessage))
        } catch (e: IOException) {
            emit(Resource.Error(e.localizedMessage))
        }
    }
}