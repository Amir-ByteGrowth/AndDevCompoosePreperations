package com.example.navigationgraphpracticew.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.navigationgraphpracticew.nestedgraphscreens.auth.ForgotPasswordScreen
import com.example.navigationgraphpracticew.nestedgraphscreens.auth.RegisterScreen
import com.example.navigationgraphpracticew.screens.HomeScreen
import com.example.navigationgraphpracticew.screens.LoginScreen
import com.example.navigationgraphpracticew.screens.SplashScreen

enum class NestedGraphScreen {
    REGISTER,
    LOGIN,
    FORGOT,
}

sealed class NestedNavigationItem(val route: String) {
    object Register : NavigationItem(NestedGraphScreen.REGISTER.name + param)
    object Forgot : NavigationItem(NestedGraphScreen.FORGOT.name + param)
    object Login : NavigationItem(NestedGraphScreen.LOGIN.name + param)
}

@Composable
fun NestedNavGraph(
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
            HomeScreen(
                navController,
                getParamValue(backStackEntry),
                getParamValue(backStackEntry, "id")
            )
        }
        navigation(
            startDestination = NestedNavigationItem.Register.route,
            route = "nestedgraph"
        ) {
            composable(NestedNavigationItem.Register.route){
               RegisterScreen(navController)
            }
            composable(NestedNavigationItem.Login.route){
                com.example.navigationgraphpracticew.nestedgraphscreens.auth.LoginScreen()
            }
            composable(NestedNavigationItem.Forgot.route){
                ForgotPasswordScreen()
            }
        }
    }

}

