package com.example.statehoistingpreperations.plainstateholders

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.setValue

class CounterState(var defaultValue: Int = 0) {
    var counter by mutableStateOf(defaultValue)
        private set

    fun increment() {
        counter++
    }

    fun decrement() {
        if (counter > 0)
            counter--
    }
}

// Custom Saver For Custom PlainText Class
val CounterStateSaver: Saver<CounterState, *> = Saver(
    save = { state -> state.counter },
    restore = { counter ->
        CounterState(defaultValue = counter)
    }
)