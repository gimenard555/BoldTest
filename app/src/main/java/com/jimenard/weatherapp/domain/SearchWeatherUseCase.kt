package com.jimenard.weatherapp.domain

import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.domain.model.LocationUi
import kotlinx.coroutines.flow.Flow

interface SearchWeatherUseCase {
    suspend fun searchLocation(wordToSearch: String): Flow<NetworkHandler<List<LocationUi>>>
}
