package com.example.f1api.network

import com.example.f1api.model.*
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path

interface F1ApiService {

    // -------------------
    // Drivers
    // -------------------
    @GET("drivers")
    suspend fun getDrivers(): Response<DriversResponse>

    @GET("drivers/{driverId}")
    suspend fun getDriverDetail(@Path("driverId") id: String): Response<Driver>

    // -------------------
    // Teams
    // -------------------
    @GET("teams")
    suspend fun getTeams(): Response<TeamsResponse>

    @GET("teams/{teamId}")
    suspend fun getTeamDetail(@Path("teamId") id: String): Response<Team>

    // -------------------
    // Seasons
    // -------------------
    @GET("seasons")
    suspend fun getSeasons(): Response<SeasonsResponse>

    @GET("seasons/{year}")
    suspend fun getSeasonDetail(@Path("year") year: Int): Response<Season>

    // -------------------
    // Results
    // -------------------
    @GET("results")
    suspend fun getResults(): Response<ResultsResponse>

    @GET("results/{raceId}")
    suspend fun getResultDetail(@Path("raceId") raceId: String): Response<Result>

    // -------------------
    // Standings
    // -------------------
    @GET("standings")
    suspend fun getStandings(): Response<StandingsResponse>

    @GET("standings/{season}")
    suspend fun getStandingsBySeason(@Path("season") season: Int): Response<StandingsResponse>

    // -------------------
    // Circuits
    // -------------------
    @GET("circuits")
    suspend fun getCircuits(): Response<CircuitsResponse>

    @GET("circuits/{circuitId}")
    suspend fun getCircuitDetail(@Path("circuitId") circuitId: String): Response<Circuit>

    // -------------------
    // Races
    // -------------------
    @GET("races")
    suspend fun getRaces(): Response<RacesResponse>

    @GET("races/{raceId}")
    suspend fun getRaceDetail(@Path("raceId") raceId: String): Response<Race>
}
