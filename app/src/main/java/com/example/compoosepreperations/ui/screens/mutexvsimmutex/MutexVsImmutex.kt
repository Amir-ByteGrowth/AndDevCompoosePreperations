package com.example.compoosepreperations.ui.screens.mutexvsimmutex

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color


@Composable
fun MutexScreen(modifier: Modifier = Modifier, mutexViewModel: MutexViewModel = MutexViewModel()) {
    mutexViewModel.main()

    Box(modifier = modifier
        .fillMaxSize()
        .background(Color.Red))
}