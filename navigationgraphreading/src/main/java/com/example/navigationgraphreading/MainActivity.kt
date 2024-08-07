package com.example.navigationgraphreading

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.rememberNavController
import com.example.navigationgraphreading.navigation.MainNavGraph
import com.example.navigationgraphreading.navigation.MainScreen
import com.example.navigationgraphreading.navigation.NavGraphWithAnimation
import com.example.navigationgraphreading.navigation.NavigationBuilderOptionTestingGraph
import com.example.navigationgraphreading.navigation.NestedGraph
import com.example.navigationgraphreading.navigation.NestedGraphWithNavGraphBuilder
import com.example.navigationgraphreading.sharingviewmodel.SharedViewmodel
import com.example.navigationgraphreading.sharingviewmodel.byhilt.ByBackStackEntry
import com.example.navigationgraphreading.sharingviewmodel.byhilt.SharedViewModelNavGraph
import com.example.navigationgraphreading.ui.theme.CompoosePreperationsTheme
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
//we can also share view model against app by using this approach

//    private val sharedViewModel: SharedViewmodel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()



        setContent {
            CompoosePreperationsTheme {
//                val navController = rememberNavController()

                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    MainNavGraph(navController = navController)
//                    MyApp()
//                    NestedGraph(navController)
//                    MainScreen()
//                    MainNavGraph()

//                    NavigationBuilderOptionTestingGraph()
//                    NavGraphWithAnimation()

//                    NestedGraphWithNavGraphBuilder()
//                    SharedViewModelNavGraph()

                    ByBackStackEntry()
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