package com.jimenard.weatherapp.domain

import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.data.repository.WeatherRepository
import com.jimenard.weatherapp.domain.model.LocationDetailUi
import kotlinx.coroutines.flow.Flow

class LocationDetailUseCaseImpl(private val repository: WeatherRepository) : LocationDetailUseCase {
    override suspend fun getLocationDetail(placeToGet: String): Flow<NetworkHandler<LocationDetailUi>> =
        repository.getLocationDetail(placeToGet)
}