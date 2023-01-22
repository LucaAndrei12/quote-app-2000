package com.lucasteel.quote_app.ui.theme

import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material.MaterialTheme
import androidx.compose.material.darkColors
import androidx.compose.material.lightColors
import androidx.compose.runtime.Composable


private val DarkColorPalette = darkColors(
    primary = Purple200,
    primaryVariant = SecondaryDark,
    secondary = SecondaryDark,
    background = LightGrey,
    surface = DarkGrey
)

private val LightColorPalette = lightColors(
    primary = Purple200,
    primaryVariant = SecondaryLight,
    secondary = SecondaryLight,
    background = LightWhite,
    surface = DarkWhite

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