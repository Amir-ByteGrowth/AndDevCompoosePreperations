package com.example.statehoistingpreperations.derivedstates

import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun DerivedStatesOfToLimitRecomp() {
    val listState = rememberLazyListState()
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        LazyColumn(state = listState) {
            items(list, key = { item -> item.id }) { item ->
                Text(text = item.name + item.id)
            }
        }
        //this will produce again and again
//        val showButton = listState.firstVisibleItemIndex > 0

//        instead use this derived state
        val showButton by remember {
            derivedStateOf {
                listState.firstVisibleItemIndex > 0
            }
        }

        Log.d("ReComp", "Called")
        AnimatedVisibility(visible = showButton) {
            ScrollTopButton()
        }


    }

}

@Composable
fun ScrollTopButton() {
    Log.d("ReComp", "Called")
    Box(
        modifier = Modifier
            .background(color = Color.White)
            .padding(10.dp)
    ) {
        Button(onClick = { /*TODO*/ }) {
            Text(text = "Move Top")
        }

    }
}


data class DerivedStates(val id: Int, val name: String)

var list = listOf(
    DerivedStates(1, "name1"),
    DerivedStates(2, "name"),
    DerivedStates(3, "name3"),
    DerivedStates(4, "name4"),
    DerivedStates(5, "name5"),
    DerivedStates(6, "name6"),
    DerivedStates(7, "name7"),
    DerivedStates(8, "name8"),
    DerivedStates(9, "name9"),
    DerivedStates(10, "name10"),
    DerivedStates(11, "name11"),
    DerivedStates(12, "name12"),
    DerivedStates(13, "name13"),
    DerivedStates(14, "name14"),
    DerivedStates(15, "name15"),
    DerivedStates(16, "name16"),
    DerivedStates(17, "name17"),
    DerivedStates(18, "name18"),
    DerivedStates(19, "name19"),
    DerivedStates(20, "name20"),
    DerivedStates(21, "name21"),
    DerivedStates(22, "name22"),
    DerivedStates(23, "name23"),
    DerivedStates(24, "name24"),
    DerivedStates(25, "name25"),
    DerivedStates(26, "name26"),
    DerivedStates(27, "name27"),
    DerivedStates(28, "name28"),
    DerivedStates(29, "name29"),
    DerivedStates(30, "name30"),
    DerivedStates(31, "name31"),
    DerivedStates(32, "name32"),

    DerivedStates(65, "name65"),
    DerivedStates(33, "name1"),
    DerivedStates(34, "name"),
    DerivedStates(35, "name3"),
    DerivedStates(36, "name4"),
    DerivedStates(37, "name5"),
    DerivedStates(38, "name6"),
    DerivedStates(39, "name7"),
    DerivedStates(40, "name8"),
    DerivedStates(41, "name9"),
    DerivedStates(42, "name10"),
    DerivedStates(43, "name11"),
    DerivedStates(44, "name12"),
    DerivedStates(45, "name13"),
    DerivedStates(46, "name14"),
    DerivedStates(47, "name15"),
    DerivedStates(48, "name16"),
    DerivedStates(49, "name17"),
    DerivedStates(50, "name18"),
    DerivedStates(51, "name19"),
    DerivedStates(52, "name20"),
    DerivedStates(53, "name21"),
    DerivedStates(54, "name22"),
    DerivedStates(55, "name23"),
    DerivedStates(56, "name24"),
    DerivedStates(57, "name25"),
    DerivedStates(58, "name26"),
    DerivedStates(59, "name27"),
    DerivedStates(60, "name28"),
    DerivedStates(61, "name29"),
    DerivedStates(62, "name30"),
    DerivedStates(63, "name31"),
    DerivedStates(64, "name32"),


    )