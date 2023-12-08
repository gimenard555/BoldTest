package com.jimenard.weatherapp.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = ColorPrimaryDark,
    primaryVariant = ColorAccent,
    secondary = ColorPrimaryDark,
    secondaryVariant = ColorPrimaryDark,
    error = ColorError,
)

private val LightColorPalette = darkColors(
    primary = ColorPrimary,
    primaryVariant = ColorAccent,
    secondary = ColorPrimary,
    secondaryVariant = ColorPrimary,
    error = ColorError,
)

@Composable
fun WeatherAppTheme(
    darkTheme: Boolean = isSystemInDarkTheme(),
    content: @Composable () -> Unit,
) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        content = content,
    )
}
