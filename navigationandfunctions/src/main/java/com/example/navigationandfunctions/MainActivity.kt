package com.example.navigationandfunctions

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.navigationandfunctions.funtions.FunctionsList
import com.example.navigationandfunctions.ui.components.RallyTabRow
import com.example.navigationandfunctions.ui.theme.CompoosePreperationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
//            CompoosePreperationsTheme {
//                // A surface container using the 'background' color from the theme
//                Surface(
//                    modifier = Modifier.fillMaxSize(),
//                    color = MaterialTheme.colorScheme.background
//                ) {
//                    Greeting("Android")
//                    FunctionsList().showResult()
//                }
//            }
            RallyAppContent()
        }
    }
}


@Composable
fun RallyAppContent(modifier: Modifier = Modifier) {
    CompoosePreperationsTheme {

        var currentScreen: RallyDestination by remember {
            mutableStateOf(Overview)
        }

        Scaffold(topBar = {
            RallyTabRow(
                allScreens = rallyTabsRowScreens,
                onTabSelected = { screen -> currentScreen = screen },
                currentScreen = currentScreen
            )
        }) { innerPadding ->
            Box(modifier = modifier.padding(innerPadding)) {
                currentScreen.screen()
            }

        }
    }
}


@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoosePreperationsTheme {
        Greeting("Android")
    }
}