package com.lucasteel.quote_app

import android.content.Intent
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.AnimatedContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.rememberNavController
import com.lucasteel.quote_app.ui.theme.QuoteappTheme
import com.lucasteel.quote_app.ui.theme.poppinsFamily

val mainViewModel = MainViewModel()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            mainViewModel.refreshStates()

            QuoteappTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun mainScreen(navController: NavController) {

    Scaffold(bottomBar = { bottomNavigator(navController = navController) },
        content = {
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.surface
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    mainCard(cardContent = mainViewModel.quoteInfo, cardAuthor = mainViewModel.authorInfo)
                    Row {
                        mainButton()
                        shareTextButton()
                    }
                }

            }
        })

}


@OptIn(ExperimentalAnimationApi::class)
@Composable
fun mainCard(cardContent: String, cardAuthor: String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight()
            .width(1000.dp),
        backgroundColor = MaterialTheme.colors.secondary,
        elevation = 15.dp,
        shape = RoundedCornerShape(10.dp)
    )
    {
        Column {
            AnimatedContent(targetState = mainViewModel.isLiked, modifier = Modifier.align(Alignment.End)) { isLiked ->
                Icon(
                    if (isLiked) Icons.Filled.Favorite else Icons.Outlined.FavoriteBorder,
                    contentDescription = "Favorite",
                    modifier = Modifier
                        .padding(10.dp)
                        .size(40.dp)
                        .clickable { mainViewModel.isLiked = !mainViewModel.isLiked }
                )
            }
            AnimatedContent(targetState = cardContent) { targetCardContent ->
                Text(
                    text = "\"" + targetCardContent + "\"",
                    modifier = Modifier.padding(5.dp),
                    color = Color.Black,
                    style = MaterialTheme.typography.h4,
                    fontFamily = poppinsFamily
                )
            }
            AnimatedContent(targetState = cardAuthor) { targetCardAuthor ->
                Text(
                    text = "\n -" + targetCardAuthor,
                    modifier = Modifier.padding(5.dp),
                    fontFamily = poppinsFamily,
                    style = MaterialTheme.typography.body2,
                    fontWeight = FontWeight.Bold,
                    color = Color.Black
                )
            }

        }
    }
}

@Composable
fun mainButton() {
    Button(
        onClick = {refreshButtonAction() },
        modifier = Modifier.padding(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
    ) {
        Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "refresh button")
        Text(
            text = "Get another!",
            Modifier.padding(start = 5.dp),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun shareTextButton() {
    val shareText =
        "\"" + mainViewModel.quoteInfo + "\"" + "\n -" + mainViewModel.authorInfo + "\n This quote has been found via Luca's quote app."
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, "Share quote")
    val context = LocalContext.current

    Button(
        onClick = { context.startActivity(shareIntent) },
        modifier = Modifier.padding(10.dp),
        colors = ButtonDefaults.buttonColors(backgroundColor = MaterialTheme.colors.primaryVariant)
    ) {
        Icon(imageVector = Icons.Default.Share, contentDescription = "share button")
        Text(
            text = "Share this!",
            Modifier.padding(start = 5.dp),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.button
        )
    }

}

fun refreshButtonAction()
{
    if(mainViewModel.isLiked)
    {
        var formattedCompressedQuote = mainViewModel.quoteInfo + "111" + mainViewModel.authorInfo
        favsViewModel.favsList.add(formattedCompressedQuote)
    }
    mainViewModel.refreshStates()
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuoteappTheme {
        mainScreen(rememberNavController())
    }
}
