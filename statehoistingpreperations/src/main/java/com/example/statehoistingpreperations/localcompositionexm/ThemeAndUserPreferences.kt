package com.example.statehoistingpreperations.localcompositionexm


import androidx.compose.foundation.layout.Column
import androidx.compose.material3.ColorScheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.colorScheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Text
import androidx.compose.material3.Typography
import androidx.compose.material3.darkColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.compositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp

data class ThemeSettings(val colors: ColorScheme, val typography: Typography)
data class UserPreferences(val userName: String, val isDarkMode: Boolean)


val LocalThemeSettings = compositionLocalOf<ThemeSettings> {
    error("No ThemeSettings provided")
}
val LocalUserPreferences = compositionLocalOf { UserPreferences("Guest", false) }


@Composable
fun ThemeAndUserPReferencesScreen(
    themeSettings: ThemeSettings = ThemeSettings(colorScheme, typography),
    userPreferences: UserPreferences = UserPreferences("Guest", false),
    content: @Composable () -> Unit,
) {


    CompositionLocalProvider(
        LocalThemeSettings provides themeSettings,
        LocalUserPreferences provides userPreferences
    ) {
        MaterialTheme(
            colorScheme = themeSettings.colors,
            typography = themeSettings.typography,
            content = content
        )
    }
}


@Composable
fun UserProfile() {
    val themeSettings = LocalThemeSettings.current
    val userPreferences = LocalUserPreferences.current

    Column {
        Text(
            text = "Welcome, ${userPreferences.userName}",
            style = themeSettings.typography.titleLarge.copy(color = themeSettings.colors.primary)
        )
        if (userPreferences.isDarkMode) {
            Text(
                text = "Dark Mode is enabled",
                style = themeSettings.typography.bodyLarge.copy(fontSize = 20.sp)
            )
        } else {
            Text(
                text = "Light Mode is enabled",
                style = themeSettings.typography.bodyLarge.copy(fontSize = 20.sp)
            )
        }
    }
}

@Composable
fun MyApp() {
    val darkThemeSettings = ThemeSettings(
        colors = darkColorScheme(primary = Color.Cyan),
        typography = Typography().copy()
    )
    val userPreferences = UserPreferences(userName = "John Doe", isDarkMode = true)

    ThemeAndUserPReferencesScreen(
        themeSettings = darkThemeSettings,
        userPreferences = userPreferences
    ) {
        UserProfile()
    }
}