package com.jimenard.weatherapp.domain.model

import com.jimenard.weatherapp.data.network.response.Day

data class DayUi(
    val condition: ConditionUi,
    val tempAverage: Float
)


fun Day.getUiModel() =
    DayUi(this.condition.getUiModel(), (this.maxtemp_c + this.mintemp_c) / 2)
