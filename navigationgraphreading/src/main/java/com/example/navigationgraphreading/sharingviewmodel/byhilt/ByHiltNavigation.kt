package com.example.navigationgraphreading.sharingviewmodel.byhilt

import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.navigationgraphreading.sharingviewmodel.SharedViewmodel
import com.example.navigationgraphreading.sharingviewmodel.byhilt.byhiltscreens.SharedOne.SharedOneScreen
import com.example.navigationgraphreading.sharingviewmodel.byhilt.byhiltscreens.SharedTwo.SharedTwoScreen

@Composable
fun SharedViewModelNavGraph() {

    val navController = rememberNavController()
    val sharedViewModel: SharedViewmodel = hiltViewModel()
    NavHost(navController, startDestination = "screen1") {
        composable("screen1") {


            SharedOneScreen(
              sharedViewmodel = sharedViewModel,
                onButtonClick = {sharedViewModel.incrementValue()},
                navigate = {
                navController.navigate("screen2")
                })
        }
        composable("screen2") {

            SharedTwoScreen(
            sharedViewmodel = sharedViewModel,
                onButtonClick = {sharedViewModel.decrementValue()},
                navigate = {
                    navController.popBackStack()
                })
        }
    }
}