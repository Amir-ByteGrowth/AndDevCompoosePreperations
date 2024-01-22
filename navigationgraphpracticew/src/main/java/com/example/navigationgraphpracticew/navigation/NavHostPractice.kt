package com.example.navigationgraphpracticew.navigation


import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry

import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.navigationgraphpracticew.screens.HomeScreen
import com.example.navigationgraphpracticew.screens.LoginScreen
import com.example.navigationgraphpracticew.screens.SplashScreen

enum class Screen {
    SPLASH,
    HOME,
    LOGIN,
}

var param = "/{name}"

sealed class NavigationItem(val route: String) {
    object Splash : NavigationItem(Screen.SPLASH.name + param)
    object Home : NavigationItem(Screen.HOME.name + param+",{id}")
    object Login : NavigationItem(Screen.LOGIN.name + param)
}

@Composable
fun AppNavHost(
    modifier: Modifier = Modifier,
    navController: NavHostController,
    startDestination: String = NavigationItem.Splash.route + "/Splash",

    ) {

//    By default, all arguments are parsed as strings. The arguments parameter of composable() accepts a list of NamedNavArguments. You can quickly create a NamedNavArgument using the navArgument method and then specify its exact type:

    NavHost(
        modifier = modifier,
        navController = navController,
        startDestination = startDestination
    ) {
        composable(NavigationItem.Splash.route + param, arguments = listOf(navArgument("name") {
            defaultValue = "Splash"
            type = NavType.StringType
        })) { backStackEntry ->
            SplashScreen(navController, getParamValue(backStackEntry))
        }
        composable(NavigationItem.Login.route + param) { backStackEntry ->
            LoginScreen(navController, getParamValue(backStackEntry))
        }
        composable(NavigationItem.Home.route + param) { backStackEntry ->
            HomeScreen(navController, getParamValue(backStackEntry),getParamValue(backStackEntry,"id"))
        }
    }

}

fun getParamValue(backStackEntry: NavBackStackEntry,paramName:String="name"): String? {
    return backStackEntry.arguments?.getString(paramName)
}