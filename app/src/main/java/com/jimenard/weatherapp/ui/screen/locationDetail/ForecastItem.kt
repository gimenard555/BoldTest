package com.jimenard.weatherapp.ui.screen.locationDetail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.wrapContentSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage
import com.jimenard.weatherapp.domain.model.DayUi

@Composable
fun ForecastItem(forecast: DayUi) {
    Card(
        modifier = Modifier
            .padding(top = 16.dp)
            .wrapContentSize(),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .height(800.dp)
        ) {
            Text(
                modifier = Modifier
                    .padding(16.dp)
                    .fillMaxWidth(),
                text = forecast.condition.text.uppercase(),
                textAlign = TextAlign.Center
            )
            AsyncImage(
                modifier = Modifier.height(300.dp),
                model = forecast.condition.icon,
                contentDescription = "This is an icon who show the condition ${forecast.condition.text}"
            )
        }
    }
}