package com.example.f1api.ui

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.f1api.model.Circuit
import com.example.f1api.util.UiState
import com.example.f1api.viewmodel.CircuitsViewModel

@Composable
fun CircuitsScreen(viewModel: CircuitsViewModel = viewModel()) {
    val circuitsState by viewModel.circuitsState.collectAsState()

    LaunchedEffect(Unit) { viewModel.loadCircuits() }

    Box(modifier = Modifier.fillMaxSize().padding(16.dp)) {
        when (circuitsState) {
            is UiState.Loading -> CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
            is UiState.Success -> {
                val circuits = (circuitsState as UiState.Success<List<Circuit>>).data

                LazyColumn(verticalArrangement = Arrangement.spacedBy(14.dp)) {
                    items(circuits) { circuit ->
                        Card(
                            modifier = Modifier.fillMaxWidth(),
                            colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surfaceVariant),
                            elevation = CardDefaults.cardElevation(defaultElevation = 8.dp)
                        ) {
                            Column(modifier = Modifier.padding(16.dp)) {
                                Text(circuit.circuitName, style = MaterialTheme.typography.titleMedium)
                                Spacer(modifier = Modifier.height(4.dp))
                                Text(
                                    text = "${circuit.city ?: "Unknown"}, ${circuit.country ?: "Unknown"}",
                                    style = MaterialTheme.typography.bodyMedium,
                                    color = MaterialTheme.colorScheme.onSurfaceVariant
                                )
                                Text(
                                    text = "Length: ${circuit.circuitLength ?: "-"} m",
                                    style = MaterialTheme.typography.bodySmall
                                )
                            }
                        }
                    }
                }
            }
            is UiState.Error -> Text(
                text = (circuitsState as UiState.Error).message,
                color = MaterialTheme.colorScheme.error,
                modifier = Modifier.align(Alignment.Center)
            )
        }
    }
}
