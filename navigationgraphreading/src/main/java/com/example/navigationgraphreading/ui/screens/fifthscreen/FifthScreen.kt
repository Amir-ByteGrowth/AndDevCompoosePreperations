package com.example.navigationgraphreading.ui.screens.fifthscreen

import android.util.Log
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import com.example.navigationgraphreading.navigation.PopEventType


@Composable
fun FifthScreen(modifier: Modifier = Modifier,navigate :() ->Unit,onPopUpClick: (PopEventType) ->Unit) {
    Log.d("WhichScreen","Fifth screen")
    Box(contentAlignment = Alignment.Center, modifier = Modifier.fillMaxSize()) {
        Column {
            Text(text = "Fifth Screen")
            Button(onClick =navigate) {
                Text(text = "Move To First")
            }
            Button(onClick ={onPopUpClick.invoke(PopEventType.PopBack)}) {
                Text(text = "Pop Back")
            }
            Button(onClick ={onPopUpClick.invoke(PopEventType.PopBackInclusiveTrue)}) {
                Text(text = "Pop Back Inclusive true")
            }
            Button(onClick ={onPopUpClick.invoke(PopEventType.PopBackInclusiveFalse)}) {
                Text(text = "Pop Back Inclusive False")
            }
            Button(onClick ={onPopUpClick.invoke(PopEventType.NavigateUp)}) {
                Text(text = "Pop Up To")
            }

        }

    }
}