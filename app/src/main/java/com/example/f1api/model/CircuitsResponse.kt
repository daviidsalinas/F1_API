package com.example.f1api.model

data class CircuitsResponse(
    val api: String,
    val url: String,
    val limit: Int,
    val offset: Int,
    val total: Int,
    val circuits: List<Circuit>
)
