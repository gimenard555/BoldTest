package com.jimenard.weatherapp.data.network.response

data class ForecastDay(
    val condition: Condition,
    val date: String,
    val maxtemp_c: Float,
    val mintemp_c: Float
)