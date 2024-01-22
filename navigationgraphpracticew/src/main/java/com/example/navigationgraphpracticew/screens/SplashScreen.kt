package com.example.navigationgraphpracticew.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.LocalContentColor
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import com.example.navigationgraphpracticew.navigation.NavigationItem

@Composable
fun SplashScreen(navHostController: NavHostController,name:String?,) {
    Box(
        Modifier
            .background(color = Color.White)
            .fillMaxSize(),
        contentAlignment = Alignment.Center
    ) {
        Column {
            Text(text = name?: "SplashScreen")
            Button(onClick = { navHostController.navigate(NavigationItem.Login.route+"/Login") }) {
                Text(text = "Go To Login")
            }
        }

    }
}

@Preview
@Composable
fun SplashScreenPreview() {
//    SplashScreen(navHostController = NavHostController(LocalContext.current))
}