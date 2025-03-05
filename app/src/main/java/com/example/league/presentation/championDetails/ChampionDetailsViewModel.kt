package com.example.league.presentation.championDetails

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.toRoute
import com.example.league.app.Route
import com.example.league.core.Resource
import com.example.league.core.domain.onError
import com.example.league.core.domain.onSuccess
import com.example.league.domain.repository.LeagueRepository
import com.example.league.domain.use_cases.GetChampionDetailsUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class ChampionDetailsViewModel(
    //private val getChampionDetailsUseCase: GetChampionDetailsUseCase,
    private val repository: LeagueRepository,
    private val savedStateHandle: SavedStateHandle
): ViewModel() {

    private val champion_name = savedStateHandle.toRoute<Route.ChampionDetails>().name

    private val _state = MutableStateFlow(ChampionDetailsState())
    val state = _state
        .onStart {
            getChampionDetails(champion_name)
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: ChampionDetailsAction) {
        when(action) {
            is ChampionDetailsAction.OnBackClick -> {

            }
        }
    }

    private fun getChampionDetails(name: String) = viewModelScope.launch {
        _state.update {
            it.copy(
                isLoading = true
            )
        }
        repository
            .getChampionDetails(name)
            .onSuccess { details ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        errorMsg = null,
                        championDetails = details
                    )
                }
            }
            .onError { error ->
                _state.update {
                    it.copy(
                        isLoading = false,
                        championDetails = null,
                        errorMsg = error.toString()
                    )
                }
            }
    }

//    private fun getChampionDetails(name: String) {
//        getChampionDetailsUseCase(name).onEach { result ->
//            when(result) {
//                is Resource.Error -> {
//                    _state.update {
//                        it.copy(
//                            errorMsg = result.message ?: "unknown"
//                        )
//                    }
//                }
//                is Resource.Loading ->
//                    _state.update {
//                        it.copy(
//                            isLoading = true
//                        )
//                    }
//                is Resource.Success -> {
//                    _state.update {
//                        it.copy(
//                            championDetails = result.data!!,
//                            isLoading = false
//                        )
//                    }
//                }
//            }
//        }.launchIn(viewModelScope)
//    }
}