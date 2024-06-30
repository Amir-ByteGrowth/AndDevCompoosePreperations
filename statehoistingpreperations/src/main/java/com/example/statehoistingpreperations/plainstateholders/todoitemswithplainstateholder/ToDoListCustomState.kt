package com.example.statehoistingpreperations.plainstateholders.todoitemswithplainstateholder

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.Saver
import androidx.compose.runtime.saveable.mapSaver
import androidx.compose.runtime.setValue

class ToDoListCustomState {

    var toDoList by mutableStateOf(listOf<ToDoItem>())
        private set

    fun addItem(toDoItem: ToDoItem) {
        toDoList = toDoList + toDoItem
    }

    fun removeItem(toDoItem: ToDoItem) {
        toDoList -= toDoItem
    }

    fun toggleCompletion(toDoItem: ToDoItem) {
        toDoList =
            toDoList.map { if (it == toDoItem) it.copy(isCompleted = !it.isCompleted) else it }
    }

    fun addAllItems(itemList: List<ToDoItem>){
        toDoList +=itemList
    }

}




class TitleAndDescriptionCustomState() {
    var title by mutableStateOf("")
        private set

    var description by mutableStateOf("")
        private set

    fun setTitles(title: String) {
        this.title = title
    }

    fun setDescriptions(description: String) {
        this.description = description
    }

    fun enableDisableButton(): Boolean {
        return title.isNotEmpty() && description.isNotEmpty()
    }


}

val titleAndDescriptionCustomSaver: Saver<TitleAndDescriptionCustomState, *> = run {
    val titleKey = "Title"
    val descriptionKey = "description"
    mapSaver(
        save = { mapOf(titleKey to it.title, descriptionKey to it.description) },
        restore = {
            TitleAndDescriptionCustomState().apply { setTitles( it[titleKey] as String)
            setDescriptions(  it[descriptionKey] as String)
            }
        }
    )
}


val ToDoListStateSaver: Saver<ToDoListCustomState, *> = Saver(
    save = { state ->
        state.toDoList.map { item ->
            listOf(item.title, item.description, item.isCompleted)
        }
    },
    restore = { savedList ->
        val state = ToDoListCustomState()
        state.addAllItems( (savedList as List<List<*>>).map { item ->
            ToDoItem(item[0] as String, item[1] as String, item[2] as Boolean)
        })
        state
    }
)