package com.jimenard.weatherapp.data.dataSource

import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.data.network.response.Location
import com.jimenard.weatherapp.data.network.response.LocationDetail
import kotlinx.coroutines.flow.Flow

interface WeatherDataSource {

    suspend fun searchLocation(wordToSearch: String): Flow<NetworkHandler<List<Location>>>

    suspend fun getLocationData(wordToSearch: String): Flow<NetworkHandler<LocationDetail>>
}
