package com.jimenard.weatherapp.domain

import com.jimenard.weatherapp.data.network.api.NetworkHandler
import com.jimenard.weatherapp.domain.model.LocationDetailUi
import kotlinx.coroutines.flow.Flow

interface LocationDetailUseCase {

    suspend fun getLocationDetail(placeToGet: String): Flow<NetworkHandler<LocationDetailUi>>

}