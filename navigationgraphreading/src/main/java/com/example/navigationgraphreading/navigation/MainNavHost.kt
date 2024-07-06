package com.example.navigationgraphreading.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationgraphreading.ui.screens.fifthscreen.FifthScreen
import com.example.navigationgraphreading.ui.screens.fourthscreen.FourthScreen
import com.example.navigationgraphreading.ui.screens.screenone.FirstScreen

import com.example.navigationgraphreading.ui.screens.secondscreen.SecondScreen
import com.example.navigationgraphreading.ui.screens.thirdscreen.ThirdScreen

@Composable
fun MainNavGraph(navController: NavHostController) {
    NavHost(navController = navController, startDestination = Destination.ScreenOne.route) {
        composable("ScreenOne") {
            FirstScreen { navigateTo(Destination.ScreenTwo.route, navController) }
        }
        composable(Destination.ScreenTwo.route) {
            SecondScreen {
                navigateTo(Destination.ScreenThree.route, navController)

            }
        }
        composable(Destination.ScreenThree.route) {
            ThirdScreen { navigateTo(Destination.ScreenFour.route, navController) }
        }
        composable(Destination.ScreenFour.route) {
            FourthScreen { navigateTo(Destination.ScreenFive.route, navController) }
        }
        composable(Destination.ScreenFive.route) {
            FifthScreen(
                navigate = { navController.navigate(Destination.ScreenOne.route) },
                onPopUpClick = {navController.popBackStack(it)})
        }
    }
}

fun navigateTo(route: String, navController: NavHostController) {
    navController.navigate(route)
}

fun NavHostController.popBackStack(
    popEventType: PopEventType,
) {
    when (popEventType) {
        PopEventType.PopBack -> {
            this.popBackStack()
        }

        PopEventType.PopBackInclusiveTrue -> {
            this.popBackStack(Destination.ScreenOne.route, true)
        }

        PopEventType.PopBackInclusiveFalse -> {
            this.popBackStack(Destination.ScreenOne.route, false)
        }

        PopEventType.NavigateUp -> {
            this.navigateUp()
        }

        PopEventType.NavigateToDialog -> {

        }
    }

}


sealed class Destination(val route: String) {
    object ScreenOne : Destination("ScreenOne")
    object ScreenTwo : Destination("ScreenTwo")
    object ScreenThree : Destination("ScreenThree")
    object ScreenFour : Destination("ScreenFour")
    object ScreenFive : Destination("ScreenFive")
}


enum class PopEventType {
    PopBack,
    PopBackInclusiveTrue,
    PopBackInclusiveFalse,
    NavigateUp,
    NavigateToDialog
}

@Composable
fun MyApp() {
    val navController = rememberNavController()

    NavHost(navController = navController, startDestination = Destination.ScreenOne.route) {
        composable(Destination.ScreenOne.route) {
            FirstScreen {
                navigateTo(Destination.ScreenTwo.route, navController)
            }
        }
        composable(Destination.ScreenTwo.route) {
            SecondScreen {
                navigateTo(Destination.ScreenThree.route, navController)
            }
        }
    }
}