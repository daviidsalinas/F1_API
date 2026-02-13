package com.example.f1api.model

data class Result(
    val raceId: String,
    val season: Int,
    val round: Int,
    val winner: String?,
    val team: String?,
    val laps: Int?,
    val time: String?
)
