package com.example.hiltwithcleannavgraph.main

import androidx.lifecycle.ViewModel
import com.example.hiltwithcleannavgraph.navigation_utils.AppNavigator

import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    appNavigator: AppNavigator
) : ViewModel() {

    val navigationChannel = appNavigator.navigationChannel
}