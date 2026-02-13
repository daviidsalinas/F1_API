package com.example.f1api.model

data class Race(
    val raceId: String,
    val season: Int,
    val round: Int,
    val circuit: String?,
    val date: String?,
    val winner: String?
)
