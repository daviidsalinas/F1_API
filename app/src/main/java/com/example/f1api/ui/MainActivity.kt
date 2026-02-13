package com.example.f1api.ui

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.f1api.model.Driver
import com.example.f1api.viewmodel.F1ViewModel
import UiState

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            F1App()
        }
    }
}
@OptIn(ExperimentalMaterial3Api::class) // Permite usar TopAppBar, que esta de pruebas
@Composable
fun F1App(viewModel: F1ViewModel = viewModel()) {
    val driversState by viewModel.driversState.collectAsState()

    // Carga inicial
    LaunchedEffect(Unit) {
        viewModel.loadDrivers()
    }

    Scaffold(
        topBar = {
            TopAppBar(title = { Text("F1 Drivers") })
        }
    ) { paddingValues ->
        Box(modifier = Modifier.padding(paddingValues).fillMaxSize()) {
            when (driversState) {
                is UiState.Loading -> {
                    CircularProgressIndicator(modifier = Modifier.align(Alignment.Center))
                }
                is UiState.Success -> {
                    DriverList((driversState as UiState.Success<List<Driver>>).data)
                }
                is UiState.Error -> {
                    Text(
                        text = (driversState as UiState.Error).message,
                        color = MaterialTheme.colorScheme.error,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        }
    }
}

@Composable
fun DriverList(drivers: List<Driver>) {
    LazyColumn(
        modifier = Modifier.fillMaxSize(),
        contentPadding = PaddingValues(vertical = 8.dp, horizontal = 16.dp),
        verticalArrangement = Arrangement.spacedBy(8.dp)
    ) {
        items(drivers) { driver ->
            DriverItem(driver)
        }
    }
}

@Composable
fun DriverItem(driver: Driver) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(text = "${driver.name} ${driver.surname}", style = MaterialTheme.typography.titleMedium)
            Spacer(modifier = Modifier.height(4.dp))
            Text(text = driver.nationality ?: "Unknown", style = MaterialTheme.typography.bodyMedium)
        }
    }
}