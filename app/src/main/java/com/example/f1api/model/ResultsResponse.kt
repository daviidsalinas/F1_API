package com.example.f1api.model

data class ResultsResponse(
    val api: String,
    val url: String,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val results: List<Result>
)
