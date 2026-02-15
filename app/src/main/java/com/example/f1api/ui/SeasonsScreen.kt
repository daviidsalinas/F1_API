package com.example.f1api.ui

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.f1api.model.Season
import com.example.f1api.util.UiState
import com.example.f1api.viewmodel.SeasonsViewModel

@Composable
fun SeasonsScreen(viewModel: SeasonsViewModel = viewModel()) {
    val seasonsState by viewModel.seasonsState.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadSeasons() }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        when (seasonsState) {
            is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is UiState.Success -> {
                val seasons = (seasonsState as UiState.Success<List<Season>>).data

                LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    items(seasons) { season ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(season.championshipName, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "Year: ${season.year}",
                                    style = MaterialTheme.typography.bodyMedium
                                )
                                Text(
                                    text = "URL: ${season.url ?: "Unknown"}",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
            is UiState.Error -> Text(
                text = (seasonsState as UiState.Error).message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
