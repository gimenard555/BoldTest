package com.jimenard.weatherapp.ui.screen.search

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.jimenard.weatherapp.domain.model.LocationUi

@Composable
fun LocationItemList(location: LocationUi) {
    Card(
        modifier = Modifier.padding(top = 16.dp),
        elevation = 2.dp,
        shape = RoundedCornerShape(8.dp),
        backgroundColor = Color.White,
    ) {
        Row(
            modifier = Modifier.height(56.dp).fillMaxWidth(),
            horizontalArrangement = Arrangement.Start,
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(modifier = Modifier.padding(start = 16.dp, end = 20.dp), text = location.name)
            Text(text = location.country)
        }
    }
}
