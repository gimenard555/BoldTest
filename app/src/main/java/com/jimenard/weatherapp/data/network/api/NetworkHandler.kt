package com.jimenard.weatherapp.data.network.api

sealed class NetworkHandler<out T>(
    val data: T? = null,
    val message: String? = null
) {
    class Success<out T>(data: T) : NetworkHandler<T>(data)
    class Error<T>(data: T? = null, message: String) : NetworkHandler<T>(data, message)
    object Loading : NetworkHandler<Nothing>()
}