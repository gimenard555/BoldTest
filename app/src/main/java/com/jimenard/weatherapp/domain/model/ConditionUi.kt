package com.jimenard.weatherapp.domain.model

import com.jimenard.weatherapp.data.network.response.Condition

data class ConditionUi(val text: String, val icon: String)

fun Condition.getUiModel() = ConditionUi(this.text, this.icon)
