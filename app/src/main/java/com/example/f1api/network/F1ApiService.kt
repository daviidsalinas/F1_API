package com.example.f1api.network


import com.example.f1api.model.DriversResponse
import retrofit2.Response
import retrofit2.http.GET

interface F1ApiService {

    @GET("drivers")
    suspend fun getDrivers(): Response<DriversResponse>
}