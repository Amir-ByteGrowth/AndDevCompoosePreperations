package com.example.navigationgraphreading.navigation

import android.util.Log
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.example.navigationgraphreading.ui.screens.fifthscreen.FifthScreen
import com.example.navigationgraphreading.ui.screens.fourthscreen.FourthScreen
import com.example.navigationgraphreading.ui.screens.screenone.FirstScreen
import com.example.navigationgraphreading.ui.screens.screenwithtwoparam.ScreenWithTwoParam
import com.example.navigationgraphreading.ui.screens.secondscreen.SecondScreen
import com.example.navigationgraphreading.ui.screens.sixthscreen.SixthScreen
import com.example.navigationgraphreading.ui.screens.thirdscreen.ThirdScreen



@Composable
fun NavGraphWithAnimation(modifier: Modifier = Modifier) {
    val navController: NavHostController = rememberNavController()
    Log.d("NavHostComposition", "Composition")
    NavHost(navController = navController, startDestination = Destination.ScreenOne.route) {
        composable(Destination.ScreenOne.route,
            enterTransition = { fadeIn(animationSpec = tween(700)) },
            exitTransition = { fadeOut(animationSpec = tween(700)) },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(700)
                )
            }


        ) {
            // Retrieve data from next screen
            val msg =
                navController.currentBackStackEntry?.savedStateHandle?.get<String>("msg")
            FirstScreen(
                msg = msg ?: "",
                navigateToTwoParamScreen = { navController.navigate(Destination.ScreenWithTwoParam.route + "/123/true") }) {
                navController.navigate(
                    Destination.ScreenTwo.route
                )
            }
        }

        composable(
            Destination.ScreenWithTwoParam.route + "/{userId}/{isMember}",
            arguments = listOf(
                navArgument("userId") {
                    type = NavType.StringType
                    defaultValue = "user1234"
                    // OR
                    type = NavType.StringType
                    nullable = true
                },
                navArgument("isMember") {
                    type = NavType.BoolType
                }
            ),
            enterTransition = {
                slideInHorizontally(
                    initialOffsetX = { it },
                    animationSpec = tween(700)
                )
            },
            exitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { it },
                    animationSpec = tween(700)
                )
            },
            popEnterTransition = {
                slideInHorizontally(
                    initialOffsetX = { -it },
                    animationSpec = tween(700)
                )
            },
            popExitTransition = {
                slideOutHorizontally(
                    targetOffsetX = { -it },
                    animationSpec = tween(700)
                )
            }
        ) { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")
            val isMember = backStackEntry.arguments?.getBoolean("isMember") ?: false
            ScreenWithTwoParam(paramOne = userId ?: "", paramTwo = isMember.toString()) {
                navController.previousBackStackEntry?.savedStateHandle?.set<String>(
                    "msg",
                    "Data Sent By Amir"
                )
                navController.popBackStack()
            }
        }

        composable(
            Destination.ScreenTwo.route,
            enterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) },
            exitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(700)) },
            popEnterTransition = { slideInHorizontally(initialOffsetX = { -it }, animationSpec = tween(700)) },
            popExitTransition = { slideOutHorizontally(targetOffsetX = { -it }, animationSpec = tween(700)) }
        ) {
            SecondScreen {
                navController.navigate(Destination.ScreenThree.route + "/123")
            }
        }

        navigation(
            startDestination = Destination.ScreenThree.route + "/{comingfrom}",
            route = "subgraph"
        ) {
            composable(Destination.ScreenThree.route + "/{comingfrom}",
                enterTransition = { fadeIn(animationSpec = tween(700)) },
                exitTransition = { fadeOut(animationSpec = tween(700)) },
                popEnterTransition = { slideInHorizontally(initialOffsetX = { it }, animationSpec = tween(700)) },
                popExitTransition = { slideOutHorizontally(targetOffsetX = { it }, animationSpec = tween(700)) }
                ) { backStackEntry ->
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

            composable(Destination.ScreenSix.route) {
                SixthScreen {
                    navController.popBackStack(it)
                }
            }
        }
    }
}

//Transition Types:
//fadeIn: Fades in the composable.
//fadeOut: Fades out the composable.
//slideInHorizontally: Slides in the composable horizontally.
//slideOutHorizontally: Slides out the composable horizontally.
//slideInVertically: Slides in the composable vertically.
//slideOutVertically: Slides out the composable vertically.

sealed class NavArgus(name:String){
    object name :NavArgus("name")
}