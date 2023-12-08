package com.jimenard.weatherapp.data.repository

import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.domain.model.LocationDetailUi
import com.jimenard.weatherapp.domain.model.LocationUi
import kotlinx.coroutines.flow.Flow

interface WeatherRepository {

    suspend fun searchLocation(wordToSearch: String): Flow<NetworkHandler<List<LocationUi>>>

    suspend fun getLocationDetail(wordToSearch: String): Flow<NetworkHandler<LocationDetailUi>>
}
