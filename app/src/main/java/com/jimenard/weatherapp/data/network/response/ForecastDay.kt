package com.jimenard.weatherapp.data.network.response

data class ForecastDay(
    val day: Day,
    val date: String
)

data class Day(
    val condition: Condition,
    val maxtemp_c: Float,
    val mintemp_c: Float
)