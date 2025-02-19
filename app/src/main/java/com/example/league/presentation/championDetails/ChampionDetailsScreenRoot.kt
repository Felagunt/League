package com.example.league.presentation.championDetails

import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.compose.collectAsStateWithLifecycle

@Composable
fun ChampionDetailsScreenRoot(
    viewModel: ChampionDetailsViewModel,
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ChampionDetailsScreen(
        state = state,
        onAction = { action ->
            when(action) {
                is ChampionDetailsAction.OnBackClick -> onBackClick()
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )
}

@Composable
private fun ChampionDetailsScreen(
    state: ChampionDetailsState,
    onAction: (ChampionDetailsAction) -> Unit
) {

}