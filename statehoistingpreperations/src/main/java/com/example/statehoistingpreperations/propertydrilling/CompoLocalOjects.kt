package com.example.statehoistingpreperations.propertydrilling



import androidx.compose.runtime.staticCompositionLocalOf

//val LocalText = staticCompositionLocalOf { "Hello, World!" }
//val LocalUpdateText = staticCompositionLocalOf<(String) -> Unit> { {} }

//val LocalText = staticCompositionLocalOf<String> { error("No Text provided") }
//val LocalUpdateText = staticCompositionLocalOf<(String) -> Unit> { error("No UpdateText provided") }

val LocalText = staticCompositionLocalOf<String> { error("No Text provided") }
var LocalUpdateText = staticCompositionLocalOf<(String) -> Unit> { error("No UpdateText provided") }