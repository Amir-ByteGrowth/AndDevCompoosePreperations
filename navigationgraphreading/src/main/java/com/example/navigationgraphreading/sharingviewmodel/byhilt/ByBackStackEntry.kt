package com.example.navigationgraphreading.sharingviewmodel.byhilt

import androidx.compose.runtime.Composable
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationgraphreading.sharingviewmodel.SharedViewmodel
import com.example.navigationgraphreading.sharingviewmodel.byhilt.byhiltscreens.SharedOne.SharedOneScreen
import com.example.navigationgraphreading.sharingviewmodel.byhilt.byhiltscreens.SharedTwo.SharedTwoScreen



@Composable
fun ByBackStackEntry(navController: NavHostController = rememberNavController()) {
    val sharedViewModel: SharedViewmodel = viewModel()

    NavHost(navController, startDestination = "screen1") {
        composable("screen1") { backStackEntry ->

            SharedOneScreen(
                sharedViewmodel = sharedViewModel,
                onButtonClick = {sharedViewModel.incrementValue()},
                navigate = {
                    navController.navigate("screen2")
                })
        }
        composable("screen2") { backStackEntry ->

            SharedTwoScreen(
                sharedViewmodel = sharedViewModel,
                onButtonClick = {sharedViewModel.decrementValue()},
                navigate = {
                    navController.popBackStack()
                })
        }
    }
}