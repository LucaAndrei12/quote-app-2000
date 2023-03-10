package com.lucasteel.quote_app

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lucasteel.quote_app.ui.theme.QuoteappTheme

@Composable
fun infoScreen(navController: NavController)
{
    Scaffold(bottomBar = { bottomNavigator(navController = navController) },
        content = { Text(text = "INFO SCREEN") })

}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    QuoteappTheme {
        favsScreen(rememberNavController())
    }
}