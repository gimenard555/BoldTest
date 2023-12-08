package com.jimenard.weatherapp.data.dataSource

import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.data.network.api.WeatherApi
import com.jimenard.weatherapp.data.network.response.Location
import com.jimenard.weatherapp.data.network.response.LocationDetail
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class WeatherDataSourceImpl(private val weatherApi: WeatherApi) : WeatherDataSource {
    //TODO Esta informacion es sensible por lo cual deberia ir en un archivo aparte bajo una incarptacion o en las preferencias
    private val apiKey = "de5553176da64306b86153651221606"
    private val numberOfDays = 3

    override suspend fun searchLocation(wordToSearch: String): Flow<NetworkHandler<List<Location>>> =
        flow {
            emit(NetworkHandler.Loading)
            val resource = try {
                val response =
                    weatherApi.searchLocation(apiKey, wordToSearch)
                NetworkHandler.Success(response)
            } catch (e: Throwable) {
                NetworkHandler.Error(null, e.message.orEmpty())
            }
            emit(resource)
        }

    override suspend fun getLocationData(placeToGet: String): Flow<NetworkHandler<LocationDetail>> =
        flow {
            emit(NetworkHandler.Loading)
            val resource = try {
                val response =
                    weatherApi.getLocationData(apiKey, numberOfDays, placeToGet)
                NetworkHandler.Success(response)
            } catch (e: Throwable) {
                NetworkHandler.Error(null, e.message.orEmpty())
            }
            emit(resource)
        }
}
