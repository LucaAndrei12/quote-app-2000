package com.lucasteel.quote_app

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Info
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavController
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable

sealed class Screens (val route: String, val name:String, val icon: ImageVector) {
    object MainScreen: Screens("mainScreen", "Home", Icons.Filled.Home)
    object FavScreen: Screens("favScreen", "Favourites", Icons.Filled.Favorite)
    object InfoScreen: Screens("infoScren", "Info", Icons.Filled.Info)
}

    val routeList = listOf(Screens.MainScreen, Screens.FavScreen, Screens.InfoScreen)

@Composable
fun NavGraph (navController: NavHostController)
{
    NavHost(navController = navController,
            startDestination = Screens.MainScreen.route)
    {
        composable(route = Screens.MainScreen.route)
        {
            mainScreen(navController)
        }
        composable(route = Screens.FavScreen.route)
        {
            favsScreen(navController)
        }
        composable(route = Screens.InfoScreen.route)
        {
            infoScreen(navController)
        }
    }
}