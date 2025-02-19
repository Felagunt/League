package com.example.league.presentation.championDetails

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Scaffold
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
        state = state.championDetails!!,
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
    state: ChampionDetails,
    onAction: (ChampionDetailsAction) -> Unit
) {
    Scaffold { paddingValues ->
        LazyColumn(
            contentPadding = paddingValues
        ) {
            item {
                AsyncImage(
                    model = Consts.imageSplashUrl + "${state.name}_0.jpg",
                    contentDescription = null,
                    contentScale = ContentScale.FillWidth,
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(200.dp)
                )

                ChampionDetailsHeader(
                    champion = state,
                    modifier = Modifier
                        .padding(horizontal = 20.dp, vertical = 6.dp)
                )

                state.passive?.let {
                    ChampionPassive(
                        passive = state.passive,
                        modifier = Modifier
                            .padding(
                                horizontal = 6.dp,
                                vertical = 10.dp
                            )
                    )
                }
                state.spells.forEach { spell ->
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