package com.example.statehoistingpreperations.differentwaystostorestate

import android.os.Parcelable
import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import kotlinx.parcelize.Parcelize

@Parcelize
data class City(var name: String, val country: String) : Parcelable

@Composable
fun CityScreen() {
    val selectedCity = rememberSaveable {
        mutableStateOf(City("Madrid", "Spain"))
    }

    Column {
        Text(text = selectedCity.value.name + "    " + selectedCity.value.country)
        Button(onClick = { selectedCity.value = selectedCity.value.copy(name = "Pakistan") }) {
            Text(text = "Change Value")
        }
    }


}