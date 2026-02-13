package com.example.f1api.repository

import com.example.f1api.model.DriversResponse
import com.example.f1api.network.F1ApiService
import com.example.f1api.network.RetrofitClient
import retrofit2.Response

class F1Repository {

    private val api: F1ApiService = RetrofitClient.instance.create(F1ApiService::class.java)

    suspend fun getDrivers(): Response<DriversResponse> {
        return api.getDrivers()
    }
}
