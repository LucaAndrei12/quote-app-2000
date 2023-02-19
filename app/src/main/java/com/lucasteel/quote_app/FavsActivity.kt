package com.lucasteel.quote_app

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lucasteel.quote_app.ui.theme.QuoteappTheme

@Composable
fun favsScreen(navController: NavController)
{
    Scaffold(bottomBar = { bottomNavigator(navController = navController) },
        content = { Text(text = "FAVOURITES SCREEN") })

}

@Preview(showBackground = true)
@Composable
fun FavsPreview() {
    QuoteappTheme {
        favsScreen(rememberNavController())
    }
}