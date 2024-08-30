package com.example.kotlinflowspractice.operators.transform.flatmaps

import android.widget.SearchView
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.emitAll
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.flow.flowOf

class NetwrokCall {
}
fun SearchView.getQueryTextChangeStateFlow(): StateFlow<String> {

    val query = MutableStateFlow("")

    setOnQueryTextListener(object : SearchView.OnQueryTextListener {
        override fun onQueryTextSubmit(query: String?): Boolean {
            return true
        }

        override fun onQueryTextChange(newText: String): Boolean {
            query.value = newText
            return true
        }
    })

    return query

}

/**
 * Simulation of network data
 */
private fun dataFromNetwork(query: String): Flow<String> {
    return flow {
        delay(2000)
        emit(query)
    }
}

//fun main() {
//    searchView.getQueryTextChangeStateFlow()
//        .debounce(300)
//        .filter { query ->
//            if (query.isEmpty()) {
////                textViewResult.text = ""
//                return@filter false
//            } else {
//                return@filter true
//            }
//        }
//        .distinctUntilChanged()
//        .flatMapLatest { query ->
//            dataFromNetwork(query)
//                .catch {
//                    emitAll(flowOf(""))
//                }
//        }
//        .flowOn(Dispatchers.Default)
//        .collect { result ->
//            textViewResult.text = result
//        }
//
//}
