package com.example.globalsnackbar.onetimeevent

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.repeatOnLifecycle
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.globalsnackbar.onetimeevent.ui.theme.CompoosePreperationsTheme
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.withContext


// source https://www.youtube.com/watch?v=njchj9d_Lf8
// don't collect flow as state in composable because we don't want to listen when we are in background .
// instead use collectAsStateWithLifecycle() in lifecycle aware manner


class OneTimeEventMainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompoosePreperationsTheme {

                val navController = rememberNavController()


                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                    NavHost(
                        navController = navController,
                        startDestination = "login",
                        modifier = Modifier.padding(innerPadding)
                    ) {
                        composable("login") {

                            val viewModel = viewModel<OneTimeEventViewModel>()
                            val state = viewModel.state
                            ObserveAsEvent(flow = viewModel.navigationChannelFlow) { event ->
                                when (event) {
                                    NavigationEvent.NavigateToProfile -> {
                                        navController.navigate("profile")
                                    }
                                }
                            }


//                            LaunchedEffect(state.isLoggedIn) {
//                                if (state.isLoggedIn){
//                                    navController.navigate("profile")
//                                    viewModel.onNavigateBack()
//                                }
//                            }

                            //                            ObserveAsEvent(flow = viewModel.navigationSharedFlow) {event ->
//                                when(event){
//                                    NavigationEvent.NavigateToProfile -> {
//                                        println("Shared Flow")
//                                        navController.navigate("profile")
//                                    }
//                                }
//                            }

                            LoginScreen(state, viewModel::login)
                        }

                        composable("profile") {
                            ProfileScreen()
                        }
                    }
                }
            }
        }
    }
}


@Composable
fun LoginScreen(state: LoginState, login: () -> Unit) {

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,
        verticalArrangement = Arrangement.Center
    ) {
        Button(onClick = { login.invoke() }) {
            Text(text = "Login")
        }

        if (state.isLoading) {
            CircularProgressIndicator()
        }
    }

}

@Composable
fun <T> ObserveAsEvent(flow: Flow<T>, event: (T) -> Unit) {
    val lifecycleOwner = LocalLifecycleOwner.current
    LaunchedEffect(lifecycleOwner.lifecycle) {
        lifecycleOwner.repeatOnLifecycle(state = Lifecycle.State.STARTED) {
            withContext(Dispatchers.Main.immediate) {
                flow.collect(event)
            }
        }
    }

}


@Composable
fun ProfileScreen(modifier: Modifier = Modifier) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Text(text = "Profile")
    }
}


@Composable
fun Greeting2(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview2() {
    CompoosePreperationsTheme {
        Greeting2("Android")
    }
}