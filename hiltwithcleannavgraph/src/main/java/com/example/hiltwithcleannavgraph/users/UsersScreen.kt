package com.example.hiltwithcleannavgraph.users

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.hiltwithcleannavgraph.R

@Composable
fun UsersScreen(
    usersViewModel: UsersViewModel = hiltViewModel()
) {
    val viewState = usersViewModel.viewState.collectAsState().value

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        horizontalAlignment = Alignment.CenterHorizontally,
    ) {
        Row(
            modifier = Modifier.fillMaxWidth()
        ) {
            IconButton(
                onClick = { usersViewModel.onBackButtonClicked() }
            ) {
                Icon(painter = painterResource(R.drawable.ic_back), contentDescription = "")
            }
        }
        Text(
            text = "UsersScreen",
            style = MaterialTheme.typography.headlineLarge
        )
        Spacer(modifier = Modifier.height(32.dp))
        LazyColumn(
            modifier = Modifier.weight(1f)
        ) {
            items(viewState.users) {
                UserRow(user = it) { user ->
                    usersViewModel.onUserRowClicked(user)
                }
            }
        }
    }
}

@Composable
private fun UserRow(
    user: User,
    onUserClick: (User) -> Unit
) {
    Spacer(modifier = Modifier.height(16.dp))
    Card(
        modifier = Modifier.fillMaxWidth(),
        elevation = CardDefaults.cardElevation(8.dp)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .clickable { onUserClick(user) }
                .padding(16.dp)
        ) {
            Text(text = "${user.firstName} ${user.lastName}")
        }
    }
}