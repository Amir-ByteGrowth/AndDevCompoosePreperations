package com.example.hiltwithcleannavgraph.details

import androidx.lifecycle.ViewModel
import com.example.hiltwithcleannavgraph.navigation_utils.AppNavigator
import com.example.hiltwithcleannavgraph.navigation_utils.Destination

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class DetailsViewModel @Inject constructor(
    private val appNavigator: AppNavigator
) : ViewModel() {

    fun onBackButtonClicked() {
        appNavigator.tryNavigateBack()
    }

    fun onNavigateToCurrentUserDetailsButtonClicked() {
        appNavigator.tryNavigateTo(
            Destination.UserDetailsScreen(
                fistName = "CurrentUserFirstName",
                lastName = "CurrentUserLastName"
            )
        )
    }
}