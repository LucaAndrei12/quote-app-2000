package com.lucasteel.quote_app

import android.annotation.SuppressLint
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.ClickableText
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.Face
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MonotonicFrameClock
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalUriHandler
import androidx.compose.ui.text.*
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.lucasteel.quote_app.ui.theme.QuoteappTheme
import com.lucasteel.quote_app.ui.theme.poppinsFamily

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun infoScreen(navController: NavController) {
    Scaffold(bottomBar = { bottomNavigator(navController = navController) },
        content = { InfoScreen() })
}

@Composable
fun InfoScreen() {
    val description = "Hello! My name is Luca and I am passionate about computers. I am 15 by the time I am writing this. For this app, I used Jetpack Compose for the frontend and Java, Retrofit and the Quotable API for the backend. <3"
    val annotatedString = "\nP.S. You can check my GitHub(where you can also find this project) by clicking on this."

    val currentUriHandler = LocalUriHandler.current

    Surface(modifier = Modifier.fillMaxSize()) {
        Column(
            modifier = Modifier
                .padding(20.dp), horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Icon(
                imageVector = Icons.Rounded.Face, contentDescription = "meeee", modifier = Modifier
                    .size(100.dp)
                    .padding(bottom = 10.dp)
            )
            Text(
                text = description,
                fontFamily = poppinsFamily,
                style = MaterialTheme.typography.body1
            )
            Text(annotatedString, Modifier.clickable { currentUriHandler.openUri("https://github.com/LucaAndrei12")}, style = MaterialTheme.typography.body2, textDecoration = TextDecoration.Underline)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun InfoPreview() {
    QuoteappTheme {
        InfoScreen()
    }
}