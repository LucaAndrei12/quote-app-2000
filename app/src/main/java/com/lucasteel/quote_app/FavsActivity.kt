package com.lucasteel.quote_app

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lucasteel.quote_app.ui.theme.QuoteappTheme

val favsViewModel = FavsViewModel()

@Composable
fun favsScreen(navController: NavController) {
    Scaffold(bottomBar = { bottomNavigator(navController = navController) },
        content = { favActivityScreen() })
}

@Composable
fun favActivityScreen() {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        LazyColumn {
            item {
                favsViewModel.favsList.forEach { i ->
                    var contentAuthorArray = i.split("111")
                    implementedCustomCard(contentAuthorArray = contentAuthorArray)
                }
            }
        }
    }
}


@Composable
fun implementedCustomCard(contentAuthorArray: List<String>) {
    customCard(
        quoteContent = contentAuthorArray[0],
        quoteAuthor = contentAuthorArray[1]
    )
}

@Composable
fun customCard(quoteContent: String, quoteAuthor: String) {
    Card(
        backgroundColor = MaterialTheme.colors.secondary, modifier = Modifier
            .wrapContentSize()
            .padding(10.dp)

    ) {
        Column() {
            Text(text = quoteContent)
            Text(text = quoteAuthor)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun FavsPreview() {
    QuoteappTheme {
        favsScreen(rememberNavController())
    }
}