package com.lucasteel.quote_app

import androidx.compose.foundation.layout.height
import androidx.compose.material.*
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState

@Composable
fun bottomNavigator(navController: NavController) {
    val backStackEntry = navController.currentBackStackEntryAsState()

    NavigationBar(containerColor = MaterialTheme.colors.primary, modifier = Modifier.height(80.dp))
    {
        routeList.forEach { item ->
            val selected = item.route == backStackEntry.value?.destination?.route

            NavigationBarItem(selected = selected,
                onClick = { navController.navigate(item.route) },
                label = {
                    Text(text = item.name, fontWeight = FontWeight.SemiBold)
                },
                icon = {
                    Icon(imageVector = item.icon, contentDescription = "Icon")
                }
            )

        }
    }
}