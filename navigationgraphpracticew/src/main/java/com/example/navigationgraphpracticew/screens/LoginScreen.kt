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
fun LoginScreen(navHostController: NavHostController, name: String?) {
    Box(
        Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {

        // Retrieve data from next screen
        val msg =
            navHostController.currentBackStackEntry?.savedStateHandle?.get<String>("msg")

        Column {
            Text(text = name ?: "LoginScreen")
            Button(onClick = { navHostController.navigate(NavigationItem.Home.route + "/Home") }) {
                Text(text = "Go to Home")
            }

            Text(text = msg ?: "")
        }

    }
}

@Preview
@Composable
fun LoginScreenPreview() {
//    LoginScreen(navHostController = NavHostController(LocalContext.current))
}