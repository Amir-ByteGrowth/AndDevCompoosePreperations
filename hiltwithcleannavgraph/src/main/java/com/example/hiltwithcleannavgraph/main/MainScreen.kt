package com.example.hiltwithcleannavgraph.main

import android.app.Activity
import androidx.compose.foundation.layout.fillMaxSize

import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.hiltwithcleannavgraph.details.DetailsScreen
import com.example.hiltwithcleannavgraph.home.HomeScreen
import com.example.hiltwithcleannavgraph.messages.MessagesScreen
import com.example.hiltwithcleannavgraph.navigation_utils.Destination
import com.example.hiltwithcleannavgraph.navigation_utils.NavHost
import com.example.hiltwithcleannavgraph.navigation_utils.NavigationIntent
import com.example.hiltwithcleannavgraph.navigation_utils.composable
import com.example.hiltwithcleannavgraph.ui.theme.CompoosePreperationsTheme
import com.example.hiltwithcleannavgraph.user_details.UserDetailsScreen
import com.example.hiltwithcleannavgraph.users.UsersScreen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.receiveAsFlow

@Composable
fun MainScreen(
    mainViewModel: MainViewModel = hiltViewModel()
) {
    val navController = rememberNavController()

    NavigationEffects(
        navigationChannel = mainViewModel.navigationChannel,
        navHostController = navController
    )
    CompoosePreperationsTheme {
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colorScheme.background
        ) {
            NavHost(
                navController = navController,
                startDestination = Destination.HomeScreen
            ) {
                composable(destination = Destination.HomeScreen) {
                    HomeScreen()
                }
                composable(destination = Destination.UsersScreen) {
                  UsersScreen()
                }
                composable(destination = Destination.UserDetailsScreen) {
                    UserDetailsScreen()
                }
                composable(destination = Destination.MessagesScreen) {
                    MessagesScreen()
                }
                composable(destination = Destination.DetailsScreen) {

                    DetailsScreen()
                }
            }
        }
    }
}

@Composable
fun NavigationEffects(
    navigationChannel: Channel<NavigationIntent>,
    navHostController: NavHostController
) {
    val activity = (LocalContext.current as? Activity)
    LaunchedEffect(activity, navHostController, navigationChannel) {
        navigationChannel.receiveAsFlow().collect { intent ->
            if (activity?.isFinishing == true) {
                return@collect
            }
            when (intent) {
                is NavigationIntent.NavigateBack -> {
                    if (intent.route != null) {
                        navHostController.popBackStack(intent.route, intent.inclusive)
                    } else {
                        navHostController.popBackStack()
                    }
                }

                is NavigationIntent.NavigateTo -> {
                    navHostController.navigate(intent.route) {
                        launchSingleTop = intent.isSingleTop
                        intent.popUpToRoute?.let { popUpToRoute ->
                            popUpTo(popUpToRoute) { inclusive = intent.inclusive }
                        }
                    }
                }
            }
        }
    }
}