package com.example.league.presentation.championDetails

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import coil3.compose.AsyncImage
import com.example.league.core.domain.Consts
import com.example.league.domain.models.ChampionDetails
import com.example.league.presentation.championDetails.components.ChampionDetailsHeader
import com.example.league.presentation.championDetails.components.ChampionPassive
import com.example.league.presentation.championDetails.components.ChampionSpell
import org.koin.androidx.compose.koinViewModel

@Composable
fun ChampionDetailsScreenRoot(
    viewModel: ChampionDetailsViewModel = koinViewModel(),
    onBackClick: () -> Unit
) {
    val state by viewModel.state.collectAsStateWithLifecycle()

    ChampionDetailsScreen(
        state = state,
        onAction = { action ->
            when (action) {
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
    Scaffold { paddingValues ->

        if (state.isLoading) {
            CircularProgressIndicator(
                modifier = Modifier
                    .background(MaterialTheme.colorScheme.surface)
            )
        }
        if (state.errorMsg?.isNotBlank() == true) {
            Text(
                text = state.errorMsg,
                style = MaterialTheme.typography.titleLarge,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.background(MaterialTheme.colorScheme.surface)
            )
        }
        state.championDetails?.let {champion ->
            LazyColumn(
                contentPadding = paddingValues
            ) {
                item {
                    AsyncImage(
                        model = Consts.imageSplashUrl + "${champion.name}_0.jpg",
                        contentDescription = null,
                        contentScale = ContentScale.FillWidth,
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(200.dp)
                    )

                    ChampionDetailsHeader(
                        champion = champion,
                        modifier = Modifier
                            .padding(horizontal = 20.dp, vertical = 6.dp)
                    )

                    champion.passive?.let {
                        ChampionPassive(
                            passive = champion.passive,
                            modifier = Modifier
                                .padding(
                                    horizontal = 6.dp,
                                    vertical = 10.dp
                                )
                        )
                    }
                    champion.spells.forEach { spell ->
                        ChampionSpell(
                            spell = spell,
                            modifier = Modifier.padding(
                                horizontal = 6.dp,
                                vertical = 10.dp
                            )
                        )
                    }
                }
            }

        }
    }
}