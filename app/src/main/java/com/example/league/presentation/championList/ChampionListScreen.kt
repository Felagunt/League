package com.example.league.presentation.championList

import android.R
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.platform.LocalSoftwareKeyboardController
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.example.league.domain.models.Champion
import com.example.league.presentation.championList.components.ChampionListItem
import org.koin.compose.viewmodel.koinViewModel

@Composable
fun ChampionListScreenRoot(
    viewModel: ChampionListViewModel = koinViewModel(),
    onChampionClick: (Champion) -> Unit
) {

    val state by viewModel.state.collectAsStateWithLifecycle()

    ChampionLisScreen(
        state = state,
        onAction = { action ->
            when (action) {
                is ChampionListAction.OnChampionClick -> onChampionClick(action.champion)
                else -> Unit
            }
            viewModel.onAction(action)
        }
    )

}

@Composable
private fun ChampionLisScreen(
    state: ChampionState,
    onAction: (ChampionListAction) -> Unit
) {

    val keyboardController = LocalSoftwareKeyboardController.current

    Scaffold { paddingValues ->
        Column(
            modifier = Modifier
                .padding(paddingValues)
                .fillMaxSize()
                .padding(horizontal = 16.dp)
        ) {
            OutlinedTextField(
                value = state.searchQuery ?: "",
                onValueChange = {
                    onAction(ChampionListAction.OnSearchQueryChange(it))
                },
                placeholder = {
                    Text(text = "Search...")
                },
                leadingIcon = {
                    Icon(
                        imageVector = Icons.Default.Search,
                        contentDescription = null
                    )
                },
                keyboardActions = KeyboardActions(
                    onSearch = {
                        keyboardController?.hide()
                    }
                ),
                keyboardOptions = KeyboardOptions(
                    imeAction = ImeAction.Search,
                    keyboardType = KeyboardType.Text
                ),
                shape = RoundedCornerShape(8.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(vertical = 20.dp)
            )
            LazyColumn(
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ) {
                items(
                    state.searchChampionList.ifEmpty {
                        state.championList
                    }
                ) { champion ->
                    ChampionListItem(
                        champion = champion,
                        modifier = Modifier
                            .animateItem()
                            .clickable {
                                onAction(ChampionListAction.OnChampionClick(champion))
                            }
                    )
                }
            }
        }
    }
}