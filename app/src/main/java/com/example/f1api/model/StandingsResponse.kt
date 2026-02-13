package com.example.f1api.model

data class StandingsResponse(
    val api: String,
    val url: String,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val standings: List<Standing>
)
