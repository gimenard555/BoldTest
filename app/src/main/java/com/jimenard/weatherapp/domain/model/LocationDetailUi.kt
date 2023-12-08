package com.jimenard.weatherapp.domain.model

import com.jimenard.weatherapp.data.network.response.LocationDetail

data class LocationDetailUi(val location: LocationUi, val days: List<DayUi>)

fun LocationDetail.getUiModel() =
    LocationDetailUi(
        this.location.getUiModel(),
        this.forecast.forecastday.map { it.day.getUiModel() })
