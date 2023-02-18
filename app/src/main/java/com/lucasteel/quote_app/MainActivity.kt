package com.lucasteel.quote_app

import android.content.Intent
import android.content.res.Resources.Theme
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Share
import androidx.compose.material.icons.rounded.Refresh
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.TextUnit
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.lucasteel.quote_app.ui.theme.QuoteappTheme
import com.lucasteel.quote_app.backend.ApiHandler
import com.lucasteel.quote_app.ui.theme.Typography
import com.lucasteel.quote_app.ui.theme.poppinsFamily
import java.lang.Long.parseLong

val model = MainViewModel()

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            apihandle.updateQuote()

            QuoteappTheme {
                val navController = rememberNavController()
                NavGraph(navController = navController)
            }
        }
    }
}

@Composable
fun mainScreen(navController: NavController) {

    Scaffold(bottomBar ={ bottomNavigator(navController = navController) },
        content ={
            Surface(
                modifier = Modifier.fillMaxSize(),
                color = MaterialTheme.colors.surface
            ) {
                Column(
                    horizontalAlignment = Alignment.CenterHorizontally,
                    verticalArrangement = Arrangement.Center
                ) {
                    mainCard(cardContent = model.quoteInfo, cardAuthor = model.authorInfo)
                    mainButton()
                    shareTextButton()
                }

            }
        })

        }


@Composable
fun mainCard(cardContent: String, cardAuthor: String) {
    Card(
        modifier = Modifier
            .padding(10.dp)
            .wrapContentHeight()
            .width(1000.dp),
        backgroundColor = Color(0xffDAECEC),
        elevation = 15.dp,
        shape = RoundedCornerShape(10.dp)
    )
    {
        Column {
            Text(
                text = cardContent,
                modifier = Modifier.padding(5.dp),
                color = Color.Black,
                style = MaterialTheme.typography.h4,
                fontFamily = poppinsFamily
            )
            Text(
                text = "\n -" + cardAuthor,
                modifier = Modifier.padding(5.dp),
                fontFamily = poppinsFamily,
                style = MaterialTheme.typography.body2,
                fontWeight = FontWeight.Bold,
                color = Color.Black
            )
        }
    }
}

@Composable
fun mainButton() {
    Button(onClick = { model.refreshStates() }, modifier = Modifier.padding(10.dp)) {
        Icon(imageVector = Icons.Rounded.Refresh, contentDescription = "refresh button")
        Text(
            text = "Get another one!",
            Modifier.padding(start = 5.dp),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.button
        )
    }
}

@Composable
fun shareTextButton() {
    val shareText = "\"" + model.quoteInfo + "\"" + "\n -" + model.authorInfo + "\n This quote has been found via Luca's quote app."
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, shareText)
        type = "text/plain"
    }
    val shareIntent = Intent.createChooser(sendIntent, null)
    val context = LocalContext.current

    Button(onClick = { context.startActivity(shareIntent) }) {
        Icon(imageVector = Icons.Default.Share, contentDescription = "share button")
        Text(
            text = "Share this quote!",
            Modifier.padding(start = 5.dp),
            fontFamily = poppinsFamily,
            fontWeight = FontWeight.Medium,
            style = MaterialTheme.typography.button
        )
    }
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    QuoteappTheme {
       // mainScreen()
    }
}
