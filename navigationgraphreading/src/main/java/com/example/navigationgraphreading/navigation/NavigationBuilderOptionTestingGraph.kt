package com.example.navigationgraphreading.navigation

import android.util.Log
import androidx.compose.animation.AnimatedContentScope
import androidx.compose.animation.AnimatedContentTransitionScope
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navigation
import com.example.navigationgraphreading.ui.screens.fifthscreen.FifthScreen
import com.example.navigationgraphreading.ui.screens.fourthscreen.FourthScreen
import com.example.navigationgraphreading.ui.screens.screenone.FirstScreen
import com.example.navigationgraphreading.ui.screens.secondscreen.SecondScreen
import com.example.navigationgraphreading.ui.screens.sixthscreen.SixthScreen
import com.example.navigationgraphreading.ui.screens.thirdscreen.ThirdScreen


@Composable
fun NavigationBuilderOptionTestingGraph() {
    val navController: NavHostController = rememberNavController()
    Log.d("NavHostComposition", "Composition")
    NavHost(navController = navController, startDestination = Destination.ScreenOne.route) {
        composable(Destination.ScreenOne.route,
            enterTransition = {
                when (initialState.destination.route) {
                    Destination.ScreenTwo.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                    else -> null
                }
            },
            exitTransition = {
                when (targetState.destination.route) {
                    Destination.ScreenTwo.route ->
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Left, animationSpec = tween(700))
                    else -> null
                }
            },
            popEnterTransition = {
                when (initialState.destination.route) {
                    Destination.ScreenTwo.route ->
                        slideIntoContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                    else -> null
                }
            },
            popExitTransition = {
                when (targetState.destination.route) {
                    Destination.ScreenTwo.route ->
                        slideOutOfContainer(AnimatedContentTransitionScope.SlideDirection.Right, animationSpec = tween(700))
                    else -> null
                }
            }


        ) {
            FirstScreen { navController.navigate(Destination.ScreenTwo.route) }
        }
        composable(Destination.ScreenTwo.route,
            ){
            SecondScreen {
                navController.navigate(Destination.ScreenThree.route + "/123")
            }
        }

        navigation(
            startDestination = Destination.ScreenThree.route + "/{comingfrom}",
            route = "subgraph"
        ) {
            composable(Destination.ScreenThree.route + "/{comingfrom}") { backStackEntry ->
                val comingFrom = backStackEntry.arguments?.getString("comingfrom") ?: ""

                ThirdScreen {
                    navController.navigate(Destination.ScreenFour.route)

                }
            }
            composable(Destination.ScreenFour.route) {
                FourthScreen { navController.navigate(Destination.ScreenFive.route) }
            }
            composable(Destination.ScreenFive.route) {
                FifthScreen(
                    navigate = {
                        navController.navigate(Destination.ScreenSix.route)
                    },
                    onPopUpClick = { navController.popBackStack(it) })
            }

            composable(Destination.ScreenSix.route){
                SixthScreen {
                   navController.popBackStack(it)
                }
            }
        }
    }
}