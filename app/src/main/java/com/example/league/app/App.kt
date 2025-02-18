package com.example.league.app

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navigation
import androidx.navigation.compose.rememberNavController
import com.example.league.presentation.championList.ChampionListScreenRoot
import com.example.league.presentation.championList.ChampionListViewModel
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun App() {
    MaterialTheme {
        val navController = rememberNavController()
        NavHost(
            navController = navController,
            startDestination = Route.ChampionGraph
        ) {
            navigation<Route.ChampionGraph>(
                startDestination = Route.ChampionList
            ) {
                composable<Route.ChampionList>() {
                    val viewModel = koinViewModel<ChampionListViewModel>()

                    ChampionListScreenRoot(
                        viewModel = viewModel,
                        onChampionClick = { champion ->
                            navController.navigate(
                                Route.ChampionDetails(champion.name)
                            )
                        },
                    )
                }

                composable<Route.ChampionDetails> {back ->
                    val arg = back.arguments?.getString(route)
                    Text(text = arg.toString())
                }
            }
        }
    }
}