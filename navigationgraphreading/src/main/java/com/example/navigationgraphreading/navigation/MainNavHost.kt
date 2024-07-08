package com.example.navigationgraphreading.navigation

import android.util.Log
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
fun MainNavGraph() {
    val navController: NavHostController = rememberNavController()
    Log.d("NavHostComposition", "Composition")
    NavHost(navController = navController, startDestination = Destination.ScreenOne.route) {
        composable(Destination.ScreenOne.route) {
            FirstScreen { navController.navigate(Destination.ScreenTwo.route) }
        }
        composable(Destination.ScreenTwo.route) {
            SecondScreen {
                navController.navigate(Destination.ScreenThree.route + "/123")
            }
        }
        composable(Destination.ScreenThree.route + "/{comingfrom}") { backStackEntry ->
            val comingFrom = backStackEntry.arguments?.getString("comingfrom") ?: ""

            ThirdScreen {
                navController.navigate(Destination.ScreenFour.route)
                //this will clear stack behind this
//            {
//                popUpTo(navController.graph.startDestinationId){
//                    inclusive = true
//                }
//                launchSingleTop =true
//            }
            }
        }
        composable(Destination.ScreenFour.route) {
            FourthScreen { navController.navigate(Destination.ScreenFive.route) }
        }
        composable(Destination.ScreenFive.route) {
            FifthScreen(
                navigate = {
                    navController.navigate(Destination.ScreenOne.route) {
//this will navigate to first screen and clear the stack
                        popUpTo(navController.graph.startDestinationId) {
                            inclusive = true
                        }
                        launchSingleTop = true

                    }
                },
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

        PopEventType.NavigateToRoute -> {
            this.navigate(route = "subgraph")
            {
                popUpTo(route = "subgraph" )
//                {
//                    inclusive=false
//                }
            }
        }

        PopEventType.PopBackToRout -> {
//            this will pop till specified rout
//            this.popBackStack("subgraph",false)
            //if the value is true it will pop specified rout as well, else it will pop till specified rout
            this.popBackStack(Destination.ScreenFour.route,true)
        }
    }

}


sealed class Destination(val route: String) {
    object ScreenOne : Destination("ScreenOne")
    object ScreenTwo : Destination("ScreenTwo")
    object ScreenThree : Destination("ScreenThree")
    object ScreenFour : Destination("ScreenFour")
    object ScreenFive : Destination("ScreenFive")
    object ScreenSix : Destination("ScreenSix")
    object ScreenWithTwoParam : Destination("ScreenWithTwoParam")
}


enum class PopEventType {
    PopBack,
    PopBackInclusiveTrue,
    PopBackInclusiveFalse,
    NavigateUp,
    NavigateToDialog,
    NavigateToRoute,
    PopBackToRout,
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

enum class Screen {
    HOME,
    LOGIN,
}
sealed class NavigationItem(val route: String) {
    object Home : NavigationItem(Screen.HOME.name)
    object Login : NavigationItem(Screen.LOGIN.name)
}