package com.example.navigationgraphpracticew.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.navigationgraphpracticew.navigation.NavigationItem

@Composable
fun HomeScreen(navHostController: NavHostController, name: String?,id:String?) {
    Box(
        Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = name ?: "HomeScreen" + id ?: "")
            Button(onClick = { navHostController.navigate(NavigationItem.Splash.route + "/Splash") }) {
                Text(text = "Go to Dashboard")
            }
            Button(onClick = {
                navHostController.previousBackStackEntry?.savedStateHandle?.set(
                    "msg",
                    "From Home"
                )
                navHostController.popBackStack()
            }) {
                Text(text = "Back to Splash")
            }
        }

    }
}

@Preview
@Composable
fun HomeScreenPreview() {
//    HomeScreen(rememberNavController())
}