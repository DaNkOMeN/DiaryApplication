package com.example.testdiary.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable

private val DarkColorPalette = darkColors(
    primary = BlueOuterSpace,
    primaryVariant = BlueRaven,
    secondary = GrayGrey,
    background = BisonHide,
    surface = BlueGreen,
    error = LightBlue
)

private val LightColorPalette = lightColors(
    primary = RonchiYellow,
    primaryVariant = OrangeRed,
    secondary = FountainBlue,
    background = CinnabarRed,
    surface = LimedSpruce,
    error = White
)

@Composable
fun DiaryAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}