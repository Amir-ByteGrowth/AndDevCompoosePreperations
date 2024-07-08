package com.example.navigationgraphreading.ui.screens.sixthscreen

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.navigationgraphreading.navigation.PopEventType

class SixthScreen {
}

@Composable
fun SixthScreen(
    modifier: Modifier = Modifier,
    onNavigateClick: (popEventType: PopEventType) -> Unit,
) {
    Box(modifier = modifier.fillMaxSize(), contentAlignment = Alignment.Center) {
        Column {
            Text(text = "Sixth Screen")

            Button(onClick = {onNavigateClick.invoke(PopEventType.NavigateToRoute) }) {
                Text(text = "Navigate to route")
            }

            Button(onClick = {onNavigateClick.invoke(PopEventType.PopBackToRout) }) {
                Text(text = "Pop Back to route")
            }

        }

    }
}