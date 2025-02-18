package com.example.league.presentation.championList

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.league.core.Resource
import com.example.league.domain.repository.LeagueRepository
import com.example.league.domain.use_cases.GetChampionListUseCase
import com.example.league.presentation.ChampionListAction
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update

class ChampionListViewModel(
    private val getChampionListUseCase: GetChampionListUseCase
): ViewModel() {

    private val _state = MutableStateFlow(ChampionState())
    val state = _state
        .onStart {
            getChampionList()
        }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            _state.value
        )

    fun onAction(action: ChampionListAction) {
        when(action) {
            is ChampionListAction.OnChampionClick -> {

            }
            is ChampionListAction.OnSearchQueryChange ->
                _state.update {
                    it.copy(
                        searchQuery = action.query
                    )
                }
        }
    }

    private fun getChampionList() {
        getChampionListUseCase().onEach { result ->
            when(result) {
                is Resource.Error -> {
                    _state.update {
                        it.copy(
                            errorMsg = result.message ?: "unknown"
                        )
                    }
                }
                is Resource.Loading ->
                    _state.update {
                        it.copy(
                            isLoading = true
                        )
                    }
                is Resource.Success -> {
                    _state.update {
                        it.copy(
                            championList = result.data ?: emptyList(),
                            isLoading = false
                        )
                    }
                }
            }
        }
    }

}