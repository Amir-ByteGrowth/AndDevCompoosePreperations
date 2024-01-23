package com.example.navigationgraphpracticew.bottomnavbar

import androidx.annotation.StringRes
import com.example.navigationgraphpracticew.R

sealed class Screen(val route: String, @StringRes val resourceId: Int) {
    object Profile : Screen("profile", R.string.profile)
    object FriendsList : Screen("friendslist", R.string.friendslist)
}

val items = listOf(Screen.Profile, Screen.FriendsList)