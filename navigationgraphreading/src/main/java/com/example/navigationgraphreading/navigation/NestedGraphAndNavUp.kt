package com.example.navigationgraphreading.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.navigationgraphreading.ui.screens.nestedgraphscreens.home.DashboardScreen
import com.example.navigationgraphreading.ui.screens.nestedgraphscreens.home.HomeScreen
import com.example.navigationgraphreading.ui.screens.nestedgraphscreens.user.UserScreen
import com.example.navigationgraphreading.ui.screens.nestedgraphscreens.userdetail.UserDetailsScreen
import com.example.navigationgraphreading.ui.screens.nestedgraphscreens.usersubdetail.UserSubDetailScreen

@Composable
fun NestedGraph(navHostController: NavHostController) {
    NavHost(
        navController = navHostController, startDestination = NestedGraphDestinations.Dashboard.route
    ) {

        composable(NestedGraphDestinations.Dashboard.route) {
            DashboardScreen {
                navHostController.navigate(NestedGraphDestinations.Home.route)
            }
        }


        composable(NestedGraphDestinations.Home.route) {
            HomeScreen {
                navHostController.navigate("User")
            }
        }


        navigation(startDestination = NestedGraphDestinations.User.route, route = "detailsGraph") {
            composable(NestedGraphDestinations.User.route) {
                UserScreen {
                    navHostController.navigate(NestedGraphDestinations.UserDetail.route)
                }
            }
            composable(NestedGraphDestinations.UserDetail.route) {
                UserDetailsScreen {
                    navHostController.navigate(NestedGraphDestinations.UserSubDetails.route)
                }
            }

            composable(NestedGraphDestinations.UserSubDetails.route) {
                UserSubDetailScreen {
//                    it wil navigate back to entry behind this in stack
//                    or when screen is called using deep link it will navigate back to that app who launched this app
//                    navHostController.navigateUp()

                    navHostController.popBackStack(
                        NestedGraphDestinations.User.route,
                        inclusive = true
                    )
                }
            }
        }


    }
}


@Composable
fun NestedGraphForUser(navHostController: NavHostController) {

}

sealed class NestedGraphDestinations(val route: String) {
    object Dashboard : NestedGraphDestinations("Dashboard")
    object Home : NestedGraphDestinations("Home")
    object User : NestedGraphDestinations("User")
    object UserDetail : NestedGraphDestinations("UserDetails")
    object UserSubDetails : NestedGraphDestinations("UserSubDetails")
}