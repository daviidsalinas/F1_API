package com.example.f1api.model

data class RacesResponse(
    val api: String,
    val url: String,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val races: List<Race>
)
