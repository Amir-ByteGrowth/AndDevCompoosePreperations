package com.example.hiltwithcleannavgraph.users

import androidx.lifecycle.ViewModel
import com.example.hiltwithcleannavgraph.navigation_utils.AppNavigator
import com.example.hiltwithcleannavgraph.navigation_utils.Destination

import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import javax.inject.Inject

@HiltViewModel
class UsersViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    private val _viewState = MutableStateFlow(UsersViewState())
    val viewState = _viewState.asStateFlow()

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }

    fun onUserRowClicked(user: User) {
        appNavigator.tryNavigateTo(
            Destination.UserDetailsScreen(
                fistName = user.firstName,
                lastName = user.lastName
            )
        )
    }
}