package com.example.f1api.model

data class SeasonsResponse(
    val api: String,
    val url: String,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val seasons: List<Season>
)
