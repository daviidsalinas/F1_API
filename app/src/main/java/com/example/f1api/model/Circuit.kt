package com.example.f1api.model

data class Circuit(
    val circuitId: String,
    val name: String,
    val location: String?,
    val country: String?,
    val url: String?
)
