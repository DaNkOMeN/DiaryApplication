package com.example.testdiary.ui.theme

import android.annotation.SuppressLint
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

private val DarkColorPalette = darkColors(
    primary = BlueOuterSpace,
    primaryVariant = BlueRaven,
    secondary = GrayGrey,
    background = BisonHide,
    surface = BlueGreen,
    error = LightBlue
)

//private val LightColorPalette = lightColors(
//    primary = RonchiYellow,
//    primaryVariant = OrangeRed,
//    secondary = FountainBlue,
//    background = CinnabarRed,
//    surface = LimedSpruce,
//    error = White
//)

val SaloTheme = lightColors(
    primary = CtotamBlue,
    onPrimary = CtotamPurple,
    secondary = CtotamOrange,
    onSecondary = CtotamSand,
    background = CtotamYellow,
    onBackground = CtotamLightYellow,
    surface = CtotamDarkBlue
)

@SuppressLint("ConflictingOnColor")
private val LenaTheme = lightColors(
    primary = LenaBluePrimary,
    onPrimary = Color.White,
    background = LenaWhiteBackground,
    secondary = LenaYellowSecondary,
    onSecondary = LenaYellowSecondary,

)

private val LightColorPalette = SaloTheme

@Composable
fun DiaryAppTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable() () -> Unit) {
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LenaTheme
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}