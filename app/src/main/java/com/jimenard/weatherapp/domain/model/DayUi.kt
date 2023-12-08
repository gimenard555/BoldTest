package com.jimenard.weatherapp.domain.model

import com.jimenard.weatherapp.data.network.response.ForecastDay

data class DayUi(
    val condition: ConditionUi,
    val date: String,
    val tempAverage: Float
)

fun ForecastDay.getUiModel() =
    DayUi(this.condition.getUiModel(), this.date, (this.maxtemp_c + this.mintemp_c) / 2)
