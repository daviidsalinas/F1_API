package com.example.f1api.viewmodel


import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import UiState
import com.example.f1api.model.Driver
import com.example.f1api.repository.F1Repository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class F1ViewModel : ViewModel() {

    private val repository = F1Repository()

    private val _driversState = MutableStateFlow<UiState<List<Driver>>>(UiState.Loading)
    val driversState: StateFlow<UiState<List<Driver>>> = _driversState

    fun loadDrivers() {
        viewModelScope.launch {
            _driversState.value = UiState.Loading
            try {
                val response = repository.getDrivers()
                if (response.isSuccessful) {
                    val list = response.body()?.drivers ?: emptyList()
                    _driversState.value = UiState.Success(list)
                } else {
                    _driversState.value = UiState.Error("HTTP error ${response.code()}")
                }
            } catch (e: Exception) {
                _driversState.value = UiState.Error("Network error: ${e.message}")
            }
        }
    }
}
