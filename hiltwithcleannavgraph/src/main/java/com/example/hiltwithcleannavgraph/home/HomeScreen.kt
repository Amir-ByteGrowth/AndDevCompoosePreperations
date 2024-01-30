package com.example.hiltwithcleannavgraph.home



import androidx.compose.foundation.layout.*
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text

import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel


@Composable
fun HomeScreen(
    homeViewModel: HomeViewModel = hiltViewModel()
) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(
                horizontal = 16.dp,
                vertical = 32.dp
            ),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Welcome to the NavigationApp",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(64.dp))
        Button(
            onClick = { homeViewModel.onNavigateToUsersButtonClicked() }
        ) {
            Text(text = "Navigate to Users")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { homeViewModel.onNavigateToMessagesButtonClicked() }
        ) {
            Text(text = "Navigate to Messages")
        }
        Spacer(modifier = Modifier.height(32.dp))
        Button(
            onClick = { homeViewModel.onNavigateToDetailsButtonClicked() }
        ) {
            Text(text = "Navigate to Details")
        }
    }
}