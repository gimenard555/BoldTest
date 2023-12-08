package com.jimenard.weatherapp.domain.model

import com.jimenard.weatherapp.data.network.response.Location

data class LocationUi(val name: String, val country: String, val region: String)

fun Location.getUiModel() = LocationUi(this.name, this.country, this.region)

