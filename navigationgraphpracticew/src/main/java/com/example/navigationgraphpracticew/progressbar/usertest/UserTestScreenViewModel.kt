package com.example.navigationgraphpracticew.progressbar.usertest

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class UserTestScreenViewModel : ViewModel() {
    private val _list = MutableStateFlow<List<UserTestScreenData>>(emptyList())
    val list = _list.asStateFlow()

    private var updateJob: Job? = null

    fun start(selectedOption:Int) {
        updateJob?.cancel()
        _list.value = listOfUserTestScreenData.take(selectedOption)
       updateJob= updateRecord()

    }

    private fun updateRecord() :Job{
        var progress = 0f
      return  viewModelScope.launch {
            while (progress < 1f) {
                delay(500)
                progress += 0.1f

                // Create a new list with updated progress values
                val updatedList = _list.value.map { item ->
                    item.copy(progress = progress)
                }
                _list.value = updatedList

            }

        }
    }

    fun stop(){
        updateJob?.cancel()
    }

}