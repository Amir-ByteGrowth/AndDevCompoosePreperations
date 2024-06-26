package com.example.compoosepreperations.ui.screens.producestateeffectscreen

import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.produceState
import androidx.compose.ui.Modifier
import kotlinx.coroutines.delay


suspend fun FetchData(): String {
    delay(3000)
    return "Data Fetched From Server"
}


@Composable
fun ProduceEffectScreen(modifier: Modifier = Modifier) {
    val uiStat by produceState<String?>(initialValue = null) {
        value = FetchData()
    }

    when (val data = uiStat) {
        null -> Text(text = "Data is Loading")
        else -> {
            Text(text = data)
        }
    }

}
