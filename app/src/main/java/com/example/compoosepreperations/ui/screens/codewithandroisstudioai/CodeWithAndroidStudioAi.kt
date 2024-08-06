package com.example.compoosepreperations.ui.screens.codewithandroisstudioai

import android.provider.CalendarContract.Colors
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

class CodeWithAndroidStudioAi {
}

@Composable
fun MyComposable() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "3. Click the window or menu to capture it. To exclude the window's shadow from the screenshot, and hold the Option key while you click.",
            style = TextStyle(fontSize = 16.sp)
        )
        Text(
            text = "4. If you see a thumbnail in the corner of your screen, click it to edit the screenshot. Or wait for the screenshot to save to your desktop.",
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Where to find screenshots",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "By default, screenshots save to your desktop with the name \"Screen Shot [date] at [time].png\".",
            style = TextStyle(fontSize = 16.sp)
        )
        Text(
            text = "In macOS Mojave or later, you can change the default location of saved screenshots from the Options menu in the Screenshot app. You can also drag the thumbnail to a folder or document.",
            style = TextStyle(fontSize = 16.sp)
        )
        Spacer(modifier = Modifier.height(16.dp))
        Text(
            text = "Learn more",
            style = TextStyle(
                fontSize = 24.sp,
                fontWeight = FontWeight.Bold
            )
        )
        Spacer(modifier = Modifier.height(8.dp))
        Text(
            text = "In macOS Mojave or later, you can also set a timer and choose where screenshots are saved within the Screenshot app. To open the app, press and hold these three keys together: Shift, Command, and 5. Learn more about the Screenshot app.",
            style = TextStyle(fontSize = 16.sp)
        )
        Text(
            text = "Some apps, such as the Apple TV app, might not let you take screenshots of their windows.",
            style = TextStyle(fontSize = 16.sp)
        )
        Text(
            text = "To copy a screenshot to the Clipboard, press and hold the Control key while you take the screenshot. You can then paste the screenshot somewhere else. Or use Universal Clipboard to paste it on an Apple device.",
            style = TextStyle(fontSize = 16.sp)
        )
    }
}

@Preview(showBackground = true)
@Composable
private fun MyCompossablePreview() {
    MyComposable()
}