package com.jimenard.weatherapp.data.network.api

import com.jimenard.weatherapp.data.network.response.Location
import com.jimenard.weatherapp.data.network.response.LocationDetail
import retrofit2.http.GET
import retrofit2.http.Query

interface WeatherApi {

    @GET("search.json")
    suspend fun searchLocation(
        @Query("key") apikey: String,
        @Query("q") wordToSearch: String
    ): List<Location>

    @GET("forecast.json")
    suspend fun getLocationData(
        @Query("key") apikey: String,
        @Query("number") number: Int,
        @Query("q") name: String,
    ): LocationDetail
}
