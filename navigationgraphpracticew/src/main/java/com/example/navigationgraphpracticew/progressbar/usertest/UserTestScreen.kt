package com.example.navigationgraphpracticew.progressbar.usertest

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Arrangement.SpaceBetween
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LinearProgressIndicator
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

data class UserTestScreenData(
    var icon: ImageVector = Icons.Filled.Refresh,
    var title: String = "B4:06:AD:60:A6 (Synchronized Clock)",
    var progress: Float = 0f,
    var detail: String = "Wait Reset To Complete",
)

var listOfUserTestScreenData = listOf(
    UserTestScreenData(),
    UserTestScreenData(),
    UserTestScreenData(),
    UserTestScreenData(),
    UserTestScreenData()
)


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun UserScreenContent(
    modifier: Modifier = Modifier,
    userTestScreenViewModel: UserTestScreenViewModel = viewModel(),
) {
    var selectedOption by remember { mutableStateOf(1) }
    var listUserTestScreenData = userTestScreenViewModel.list.collectAsState()

    Log.d("UserScreenData",listUserTestScreenData.value.toString())

    Scaffold(topBar = {
        TopAppBar(
            title = { Text(text = "Task Manager") },
            navigationIcon = {
                IconButton(onClick = { /*TODO*/ }) {
                    Icon(imageVector = Icons.Filled.ArrowBack, contentDescription = "ArrowBack")
                }
            },
            colors = TopAppBarDefaults.smallTopAppBarColors(containerColor = Color.Gray)
        )
    },
        bottomBar = {
            BottomAppBarDefaults(startClick = {
                Log.d("Button Clicked", "Start")
                userTestScreenViewModel.start(selectedOption)
            }
            ) {
                userTestScreenViewModel.stop()
                Log.d("Button Clicked", "Stop")
            }
        }
    ) { innerPAdding ->
        Column(modifier = modifier.padding(innerPAdding)) {
            HeaderSection(selectedOption = selectedOption) {
                selectedOption=it
                Log.d("RetryCountSelected", it.toString())
            }

            ListContent(list = listUserTestScreenData.value)

        }

    }

}

@Composable
fun ListContent(list: List<UserTestScreenData>) {
    LazyColumn {
        items(list) { item ->
            ListItem(userTestScreenData = item)
        }
    }
}

@Composable
fun ListItem(modifier: Modifier = Modifier, userTestScreenData: UserTestScreenData) {
    Card(modifier = modifier.padding(10.dp)) {
        Row(modifier = Modifier.padding(10.dp), verticalAlignment = Alignment.CenterVertically) {
            Icon(imageVector = userTestScreenData.icon, contentDescription = "Refresh")
            Spacer(modifier = modifier.width(10.dp))
            Column {
                Text(text = userTestScreenData.title)
                LinearProgressIndicator(
                    trackColor = Color.LightGray,
                    progress = userTestScreenData.progress,
                    modifier = modifier
                        .fillMaxWidth()
                        .padding(vertical = 5.dp)
                        .height(6.dp)
                )
                Text(
                    text = userTestScreenData.detail,
                    style = TextStyle.Default.copy(fontWeight = FontWeight.Bold)
                )
            }
        }
    }
}

@Composable
fun HeaderSection(modifier: Modifier = Modifier,selectedOption:Int, onRetryCountSelected: (Int) -> Unit) {

    var expanded by remember { mutableStateOf(false) }


    val numList = listOf(1, 2, 3, 4, 5)


    Card(modifier = modifier.padding(10.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Row(
                modifier = Modifier
                    .padding(vertical = 15.dp)
                    .fillMaxWidth()
                    .clickable {
                        expanded = !expanded
                        Log.d("RowClicked", "Yes")
                    },
                horizontalArrangement = Arrangement.Center,
            ) {
                Text(text = "Retry Count $selectedOption")
                Icon(imageVector = Icons.Filled.KeyboardArrowDown, contentDescription = "")
            }

            DropdownMenu(
                expanded = expanded,
                onDismissRequest = { expanded = false }
            ) {
                numList.forEach { num ->
                    DropdownMenuItem(text = { Text(text = "$num") }, onClick = {

                        expanded = false
                        onRetryCountSelected.invoke(num)
                    })
                }

            }
        }

    }
}

@Composable
fun BottomAppBarDefaults(
    modifier: Modifier = Modifier,
    startClick: () -> Unit,
    stopClick: () -> Unit,
) {
    Card(
        modifier = Modifier.fillMaxWidth(),
        shape = RectangleShape,
        colors = CardDefaults.cardColors(containerColor = Color.White)
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 10.dp, vertical = 15.dp),
            horizontalArrangement = SpaceBetween
        ) {
            TextButton(onClick = startClick) {
                Text(text = "Start")
            }

            TextButton(onClick = stopClick) {
                Text(text = "Stop")
            }
        }
    }

}


