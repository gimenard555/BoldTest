package com.jimenard.weatherapp.data.repository

import com.jimenard.weatherapp.data.dataSource.WeatherDataSource
import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.domain.model.LocationDetailUi
import com.jimenard.weatherapp.domain.model.LocationUi
import com.jimenard.weatherapp.domain.model.getUiModel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class WeatherRepositoryImpl(private val dataSource: WeatherDataSource) : WeatherRepository {
    override suspend fun searchLocation(wordToSearch: String): Flow<NetworkHandler<List<LocationUi>>> =
        dataSource.searchLocation(wordToSearch)
            .map { location ->
                if (!location.data.isNullOrEmpty()) {
                    val locations = location.data.map { it.getUiModel() }
                    NetworkHandler.Success(locations)
                } else {
                    NetworkHandler.Error(null, "Empty list")
                }
            }
            .catch { exception -> emit(NetworkHandler.Error(null, exception.message.orEmpty())) }

    override suspend fun getLocationDetail(placeToGet: String): Flow<NetworkHandler<LocationDetailUi>> =
        dataSource.getLocationData(placeToGet)
            .map { detail ->
                if (detail.data != null) {
                    NetworkHandler.Success(detail.data.getUiModel())
                } else {
                    NetworkHandler.Error(null, "Empty list")
                }
            }
            .catch { exception -> emit(NetworkHandler.Error(null, exception.message.orEmpty())) }
}
