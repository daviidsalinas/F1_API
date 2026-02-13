package com.example.f1api.model

data class TeamsResponse(
    val api: String,
    val url: String,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val teams: List<Team>
)
