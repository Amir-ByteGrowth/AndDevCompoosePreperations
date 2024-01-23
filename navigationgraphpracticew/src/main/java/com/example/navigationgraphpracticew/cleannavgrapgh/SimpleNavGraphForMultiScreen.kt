package com.example.navigationgraphpracticew.cleannavgrapgh

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationgraphpracticew.cleannavgrapgh.screens.DetailScreen
import com.example.navigationgraphpracticew.cleannavgrapgh.screens.HomeScreen
import com.example.navigationgraphpracticew.cleannavgrapgh.screens.MessageScreen
import com.example.navigationgraphpracticew.cleannavgrapgh.screens.UserDetailsScreen
import com.example.navigationgraphpracticew.cleannavgrapgh.screens.UserScreen


@Composable
fun SimpleNavGraphForMultiScreen() {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = "home"
    ) {
        composable(route = "home") {
            HomeScreen(
                navigateToUser = { navController.navigate("users") },
                navigateMessage = { navController.navigate("messages") },
                navigateToDetail = { navController.navigate("details") }
            )
        }
        composable(route = "users") {
            UserScreen(
                navigateBack = { navController.navigateUp() },
                navigateUserDetail = { navController.navigate("user_details") }
            )
        }
        composable(route = "user_details") {
            UserDetailsScreen(
                navigate = { navController.navigateUp() }
            )
        }
        composable(route = "messages") {
            MessageScreen(
                navigate = { navController.navigateUp() }
            )
        }
        composable(route = "details") {
            DetailScreen(
                navigate = { navController.navigateUp() }
            )
        }
    }
}