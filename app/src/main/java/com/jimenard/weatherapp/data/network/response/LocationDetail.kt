package com.jimenard.weatherapp.data.network.response

data class LocationDetail(val location: Location, val days: List<ForecastDay>)
