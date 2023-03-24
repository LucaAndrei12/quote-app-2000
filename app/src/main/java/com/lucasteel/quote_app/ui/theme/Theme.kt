package com.lucasteel.quote_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.Colors
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import com.google.accompanist.systemuicontroller.rememberSystemUiController


private val DarkColorPalette = darkColors(
    primary = PrimaryDark, //bottom navigation
    primaryVariant = PrimaryVariantDark, //buttons
    secondary = SecondaryDark, //card color
    background = BackgroundDark,
    surface = SurfaceDark //background
)

private val LightColorPalette = lightColors(
    primary = PrimaryLight, //bottom navigation
    primaryVariant = PrimaryVariantLight, //buttons
    secondary = SecondaryLight, //card color
    background = BackgroundLight,
    surface = SurfaceLight  //background

    /* Other default colors to override
    background = Color.White,
    surface = Color.White,
    onPrimary = Color.White,
    onSecondary = Color.Black,
    onBackground = Color.Black,
    onSurface = Color.Black,
    */
)

@Composable
fun QuoteappTheme(darkTheme: Boolean = isSystemInDarkTheme(), content: @Composable () -> Unit) {
    val systemUiController = rememberSystemUiController()
    val colors = if (darkTheme) {
        DarkColorPalette
    } else {
        LightColorPalette
    }

    if(darkTheme) {
        systemUiController.setSystemBarsColor(
            color = Color(0xffa96da3)
        )
    }else {
        systemUiController.setSystemBarsColor(
            color = Color(0xff57756b)
        )
    }

    MaterialTheme(
        colors = colors,
        typography = Typography,
        shapes = Shapes,
        content = content
    )
}