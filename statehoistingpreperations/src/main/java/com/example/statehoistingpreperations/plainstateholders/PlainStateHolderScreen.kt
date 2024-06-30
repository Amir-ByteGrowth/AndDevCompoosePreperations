package com.example.statehoistingpreperations.plainstateholders

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.example.statehoistingpreperations.plainstateholders.todoitemswithplainstateholder.ToDoListScreen

@Composable
fun PlainStateHolderScreen(modifier: Modifier = Modifier) {
//    IntPlainStateHolder()
//    IntPlainStateHolderWithCustomSaver()
    ToDoListScreen()
}

@Composable
fun IntPlainStateHolder(modifier: Modifier = Modifier) {
    var counterPlainState = remember { CounterState() }

    Column {
        Text(text = "CounterValue  ${counterPlainState.counter}")
        Row {
            IconButton(onClick = { counterPlainState.increment() }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "AddIcon")
            }
            Spacer(modifier = modifier.width(10.dp))
            IconButton(onClick = { counterPlainState.decrement() }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "AddIcon")
            }
        }
    }
}


//@Composable
//fun rememberSaveableCounterState(): CounterState {
//    return rememberSaveable(saver = CounterStateSaver) { CounterState() }
//}

@Composable
fun IntPlainStateHolderWithCustomSaver(modifier: Modifier = Modifier) {
    var counterPlainState = rememberSaveable(saver = CounterStateSaver) { CounterState() }

    Column {
        Text(text = "CounterValue  ${counterPlainState.counter}")
        Row {
            IconButton(onClick = { counterPlainState.increment() }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowUp, contentDescription = "AddIcon")
            }
            Spacer(modifier = modifier.width(10.dp))
            IconButton(onClick = { counterPlainState.decrement() }) {
                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "AddIcon")
            }
        }
    }
}