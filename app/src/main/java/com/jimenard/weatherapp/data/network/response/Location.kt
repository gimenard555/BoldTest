package com.jimenard.weatherapp.data.network.response

data class Location(
    val id: Long,
    val name: String,
    val region: String,
    val country: String,
)