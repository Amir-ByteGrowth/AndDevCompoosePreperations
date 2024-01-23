package com.example.navigationgraphpracticew

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.navigationgraphpracticew.bottomnavbar.BottomNavbarScreen
import com.example.navigationgraphpracticew.cleannavgrapgh.SimpleNavGraphForMultiScreen
import com.example.navigationgraphpracticew.navigation.AppNavHost
import com.example.navigationgraphpracticew.navigation.NestedNavGraph
import com.example.navigationgraphpracticew.ui.theme.CompoosePreperationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CompoosePreperationsTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
//                    AppNavHost(navController = rememberNavController())
//                    NestedNavGraph(navController = rememberNavController())
//                    BottomNavbarScreen()
                    SimpleNavGraphForMultiScreen()
                }
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