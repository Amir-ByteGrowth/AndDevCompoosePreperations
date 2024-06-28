package com.example.statehoistingpreperations.differentwaystostorestate

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier


val CitySaver: Saver<City, Any> = run {
    val nameKey = "Name"
    val countryKey = "Country"
    mapSaver(
        save = { mapOf(nameKey to it.name, countryKey to it.country) },
        restore = { City(it[nameKey] as String, it[countryKey] as String) }
    )
}

@Composable
fun UsingMapSaverScreen(modifier: Modifier = Modifier) {
    var cityState by rememberSaveable(stateSaver = CitySaver) {
        mutableStateOf(City("USA", "United States of America"))
    }

    Column {
        Text(text = cityState.name +"   "+cityState.country)

        Button(onClick = { cityState=City("Jaranwala","Pakistan") }) {
            Text(text = "Jaranwala Pakistan")
        }

        Button(onClick = { cityState=City("Bomby","India") }) {
            Text(text = "Bomby India")
        }
    }
}