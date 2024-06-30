package com.example.statehoistingpreperations.plainstateholders.todoitemswithplainstateholder


import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.Checkbox
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp

@Composable
fun ToDoListState(): ToDoListCustomState {
    return rememberSaveable(saver = ToDoListStateSaver) {
        ToDoListCustomState()
    }
}

@Composable
fun TitleAndDescriptionState(): TitleAndDescriptionCustomState {
    return rememberSaveable(saver = titleAndDescriptionCustomSaver) {
        TitleAndDescriptionCustomState()
    }
}


@Composable
fun ToDoListScreen(
    modifier: Modifier = Modifier,
    titleAndDescriptionCustomState: TitleAndDescriptionCustomState = TitleAndDescriptionState(),
    toDoListCustomState: ToDoListCustomState = ToDoListState(),
) {
    Column(modifier = modifier.padding(10.dp).fillMaxSize()) {
        Row(
            modifier = modifier
                .fillMaxWidth()
                .padding(10.dp)
                .height(50.dp)

        ) {
            OutlinedTextField(
                value = titleAndDescriptionCustomState.title,
                onValueChange = { titleAndDescriptionCustomState.setTitles(it) },
                modifier = modifier.weight(1f).padding(start = 10.dp)
            )
            OutlinedTextField(
                value = titleAndDescriptionCustomState.description,
                onValueChange = { titleAndDescriptionCustomState.setDescriptions(it) },
                modifier = modifier.weight(1f).padding(start = 10.dp)
            )

            Button(
                modifier = modifier.padding(start = 10.dp),
                onClick = {
                    toDoListCustomState.addItem(
                        ToDoItem(
                            titleAndDescriptionCustomState.title,
                            titleAndDescriptionCustomState.description,
                            false
                        )
                    )
                },
                enabled = titleAndDescriptionCustomState.enableDisableButton(),

            ) {
                Text(text = "Add")
            }
        }

        Spacer(modifier = modifier.height(10.dp))

        LazyColumn( state = rememberLazyListState()) {
            items( toDoListCustomState.toDoList) {item->
                ToDoItemComposable(item = item,
                { toDoListCustomState.toggleCompletion(item) },
                { toDoListCustomState.removeItem(item) })
            }
        }
//        toDoListCustomState.toDoList.forEach { item ->
//            ToDoItemComposable(item = item,
//                { toDoListCustomState.toggleCompletion(item) },
//                { toDoListCustomState.removeItem(item) })
//        }

    }

}

@Composable
fun ToDoItemComposable(item: ToDoItem, onToggleComplete: () -> Unit, onDelete: () -> Unit) {
    Row(
        verticalAlignment = Alignment.CenterVertically,
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
    ) {
        Checkbox(checked = item.isCompleted, onCheckedChange = { onToggleComplete() })
        Column(
            modifier = Modifier
                .weight(1f)
                .padding(start = 8.dp)
        ) {
            Text(text = item.title, style = MaterialTheme.typography.titleSmall)
            Text(text = item.description, style = MaterialTheme.typography.bodySmall)
        }
        IconButton(onClick = { onDelete() }) {
            Icon(Icons.Default.Delete, contentDescription = "Delete")
        }
    }
}