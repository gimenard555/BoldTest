package com.jimenard.weatherapp.domain

import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.data.repository.WeatherRepository
import com.jimenard.weatherapp.domain.model.LocationUi
import kotlinx.coroutines.flow.Flow

class SearchWeatherUseCaseImpl(private val repository: WeatherRepository) : SearchWeatherUseCase {

    override suspend fun searchLocation(wordToSearch: String): Flow<NetworkHandler<List<LocationUi>>> =
        repository.searchLocation(wordToSearch)
}
