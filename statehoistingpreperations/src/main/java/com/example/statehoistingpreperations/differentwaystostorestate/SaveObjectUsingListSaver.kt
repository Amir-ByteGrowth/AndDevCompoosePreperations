package com.example.statehoistingpreperations.differentwaystostorestate

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.listSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier

val cityObjectSaverUsingListSaver = listSaver<City, Any>(
    save = { listOf(it.name, it.country) },
    restore = { City(it[0] as String, it[1] as String) }
)

@Composable
fun CityObjectSaverUsingListSaverScreen(modifier: Modifier = Modifier) {
    var cityState by rememberSaveable(stateSaver = cityObjectSaverUsingListSaver) {
        mutableStateOf(City("USA", "United States of America"))
    }

    Column {
        Text(text = cityState.name + "   " + cityState.country)

        Button(onClick = { cityState = City("Jaranwala", "Pakistan") }) {
            Text(text = "Jaranwala Pakistan")
        }

        Button(onClick = { cityState = City("Bomby", "India") }) {
            Text(text = "Bomby India")
        }
    }
}