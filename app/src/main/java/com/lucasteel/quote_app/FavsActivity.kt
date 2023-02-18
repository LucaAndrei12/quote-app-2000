package com.lucasteel.quote_app

import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.lucasteel.quote_app.ui.theme.QuoteappTheme

@Composable
fun favsScreen()
{
    Text(text = "FAVOURITES SCREEN")
}

@Preview(showBackground = true)
@Composable
fun FavsPreview() {
    QuoteappTheme {
        favsScreen()
    }
}