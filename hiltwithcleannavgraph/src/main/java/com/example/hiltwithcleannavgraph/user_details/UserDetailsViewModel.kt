package com.example.hiltwithcleannavgraph.user_details

import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import com.example.hiltwithcleannavgraph.navigation_utils.AppNavigator
import com.example.hiltwithcleannavgraph.navigation_utils.Destination

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import javax.inject.Inject

@HiltViewModel
class UserDetailsViewModel @Inject constructor(
    private val appNavigator: AppNavigator,
    savedStateHandle: SavedStateHandle
) : ViewModel() {

    private val _viewState = MutableStateFlow(UserDetailsViewState())
    val viewState = _viewState.asStateFlow()

    init {
        val firstName =
            savedStateHandle.get<String>("firstName") ?: ""
        val lastName =
            savedStateHandle.get<String>("lastName") ?: ""

        _viewState.update {
            it.copy(
                firstName = firstName,
                lastName = lastName
            )
        }
    }

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }
}