package com.example.navigationgraphreading.navigation

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation


@Composable
fun MainNavGraph1(navController: NavHostController) {
    NavHost(navController = navController, startDestination = "home") {
        composable("home") {
            HomeScreen(navController)
        }

        navigation(startDestination = "settings_main", route = "settings") {
            composable("settings_main") {
                SettingsScreen(navController)
            }
            composable("profile") {
                ProfileScreen(navController)
            }
            composable("settings_detail") {
                SettingsDetailScreen(navController)
            }
        }
    }
}


@Composable
fun MainScreen() {
    val navController = rememberNavController()
    MainNavGraph1(navController = navController)
}

@Composable
fun HomeScreen(navController: NavHostController) {
    // Home screen UI
    Column {
        Text("Home Screen")
//        Button(onClick = { navController.navigate("profile") }) {
//            Text("Go to Profile")
//        }
        Button(onClick = { navController.navigate("settings") }) {
            Text("Go to Settings")
        }
    }
}

@Composable
fun ProfileScreen(navController: NavHostController) {
    // Profile screen UI
    Column {
        Text("Profile Screen")
        Button(onClick = { navController.navigate("settings_detail")
        {
//            // Pop up to the start destination of the graph to
//            // avoid building up a large stack of destinations
//            // on the back stack as users select items
            popUpTo(navController.graph.findStartDestination().id) {
                saveState = true
            }
//            // Avoid multiple copies of the same destination when
//            // reselecting the same item
            launchSingleTop = true
//            // Restore state when reselecting a previously selected item
            restoreState = true
//
//
        }

        }) {
            Text("Go Back")
        }
    }
}

@Composable
fun SettingsScreen(navController: NavHostController) {
    // Settings screen UI
    Column {
        Text("Settings Screen")
        Button(onClick = { navController.navigate("profile") }) {
            Text("Go to Profile")
        }
//        Button(onClick = { navController.popBackStack() }) {
//            Text("Go Back")
//        }
    }
}

@Composable
fun SettingsDetailScreen(navController: NavHostController) {
    // Settings detail screen UI
    Column {
        Text("Settings Detail Screen")
        Button(onClick = { navController.navigateUp() }) {
            Text("Go Back")
        }
    }
}