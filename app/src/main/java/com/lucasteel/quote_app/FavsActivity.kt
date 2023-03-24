package com.lucasteel.quote_app

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.gestures.detectTapGestures
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Close
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import com.google.accompanist.swiperefresh.SwipeRefresh
import com.google.accompanist.swiperefresh.rememberSwipeRefreshState
import com.lucasteel.quote_app.backend.IOSaver
import com.lucasteel.quote_app.ui.theme.QuoteappTheme
import com.lucasteel.quote_app.ui.theme.poppinsFamily
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.io.File

val favsViewModel = FavsViewModel()

@Composable
fun favsScreen(navController: NavController) {
    val isLoading by favsViewModel.isLoading.collectAsState()
    val swipeRefreshState = rememberSwipeRefreshState(isRefreshing = isLoading)
    val context = LocalContext.current

    SwipeRefresh(
        state = swipeRefreshState,
        onRefresh = { favsViewModel.loadQuotes(File(context.filesDir, "quotes-file")) }) {
        Scaffold(bottomBar = { bottomNavigator(navController = navController) },
            floatingActionButton = { removeAllButton() },
            content = { padding -> favActivityScreen(padding) })
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun favActivityScreen(padding: PaddingValues) {
    Surface(modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.surface) {
        LazyColumn {
            items(
                items = favsViewModel.favsList,
                key = { unParsedQuoteAuthorString -> unParsedQuoteAuthorString.hashCode()}
            ) { _unParsedQuoteAuthorString ->
                if (favsViewModel.favsList.size != 0) {

                        val isLastFromList =
                            (favsViewModel.favsList.size - 1 == favsViewModel.favsList.indexOf(_unParsedQuoteAuthorString))
                        val paddingRule =
                            if (!isLastFromList) PaddingValues(10.dp) else PaddingValues(
                                bottom = 90.dp,
                                start = 10.dp,
                                end = 10.dp,
                                top = 10.dp
                            )
                        Row(Modifier.animateItemPlacement()){savedQuoteCard(unParsedQuoteAuthorString = _unParsedQuoteAuthorString, paddingRule)}

                }
            }
        }
    }
}

@Composable
fun removeAllButton() {
    val coroutineScope = rememberCoroutineScope()
    val context = LocalContext.current
    FloatingActionButton(backgroundColor = MaterialTheme.colors.primaryVariant, onClick = {
        coroutineScope.launch(Dispatchers.IO) {
            val savedListToMerge =
                ArrayList<String>(favsViewModel.favsList.filter {
                    !favsViewModel.selectedQuotesList.contains(it)
                })
            IOSaver().saveFavs(
                savedListToMerge,
                File(context.filesDir, "quotes-file")
            )
            favsViewModel.favsList = savedListToMerge
        }
    }
    ) {
        Row(modifier = Modifier.padding(10.dp)){
            Text(text = "Remove Selected", modifier = Modifier.align(Alignment.CenterVertically), style = MaterialTheme.typography.button, fontFamily = poppinsFamily)
            Icon(imageVector = Icons.Default.Close, contentDescription = "remove all button")
        }
    }
}

@Composable
fun savedQuoteCard(unParsedQuoteAuthorString: String, cardPadding: PaddingValues) {
    val contentAuthorArray = unParsedQuoteAuthorString.split("111")
    val isSelected = rememberSaveable { mutableStateOf(false) }

    Card(
        backgroundColor = MaterialTheme.colors.secondary, modifier = Modifier
            .fillMaxWidth()
            .padding(cardPadding)
            .pointerInput(Unit) {
                detectTapGestures(onLongPress = {
                    isSelected.value =
                        !isSelected.value; if (isSelected.value) favsViewModel.selectedQuotesList.add(
                    unParsedQuoteAuthorString
                ) else favsViewModel.selectedQuotesList.remove(unParsedQuoteAuthorString)
                })
            }
    ) {
        Row(horizontalArrangement = Arrangement.SpaceBetween) {
            val currentUsableWidth = (LocalConfiguration.current.screenWidthDp - 20).dp
            val spaceForText = (Integer.parseInt(currentUsableWidth.toString().removeSuffix(".0.dp")) * 0.85).dp
            val spaceForIcon = (Integer.parseInt(currentUsableWidth.toString().removeSuffix(".0.dp")) * 0.15).dp
            Column {
                if (contentAuthorArray.size > 1) {
                    Text(
                        text = "\"" + contentAuthorArray[0] + "\" ",
                        modifier = Modifier.width(spaceForText),
                        style = MaterialTheme.typography.h6,
                        fontFamily = poppinsFamily
                    )
                    Text(
                        text = "-" + contentAuthorArray[1],
                        style = MaterialTheme.typography.subtitle1,
                        fontFamily = poppinsFamily
                    )
                }
            }
            AnimatedVisibility(
                visible = isSelected.value, modifier = Modifier
                    .align(Alignment.CenterVertically)
                    .size(spaceForIcon)
            ) {
                Icon(imageVector = Icons.Default.Check, contentDescription = "isChecked")
            }
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