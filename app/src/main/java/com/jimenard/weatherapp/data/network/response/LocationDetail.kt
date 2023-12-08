package com.jimenard.weatherapp.data.network.response

data class LocationDetail(val location: Location, val forecast: Forecast)

data class Forecast(val forecastday: List<ForecastDay>)
