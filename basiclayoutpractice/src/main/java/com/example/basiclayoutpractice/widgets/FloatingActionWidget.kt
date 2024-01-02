package com.example.basiclayoutpractice.widgets

import android.widget.Toast
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Clear
import androidx.compose.material.icons.outlined.Edit
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.FloatingActionButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeFloatingActionButton
import androidx.compose.material3.SmallFloatingActionButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp

@Composable
fun ListOfFloatingActionButton() {
    Column(Modifier.padding(20.dp)) {
        FloatingActionButton(
            onClick = { /*TODO*/ },
            shape = CircleShape,
            containerColor = Color.Red,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Add, contentDescription = "Add")
        }
        Spacer(modifier = Modifier.height(10.dp))
        FloatingActionButton(
            onClick = { /*TODO*/ },
            shape = RectangleShape,
            containerColor = Color.Green,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Clear, contentDescription = "Add")
        }
        Spacer(modifier = Modifier.height(10.dp))
        FloatingActionButton(
            onClick = { /*TODO*/ },

            containerColor = Color.Blue,
            contentColor = Color.White
        ) {
            Icon(Icons.Default.Clear, contentDescription = "Add")
        }
        Spacer(modifier = Modifier.height(10.dp))

        FloatingActionButton(
            onClick = { /*TODO*/ },
            elevation = FloatingActionButtonDefaults.elevation(16.dp),

            ) {
            Icon(Icons.Default.Clear, contentDescription = "Add")
        }
        Spacer(modifier = Modifier.height(10.dp))
        SmallFloatingActionButton(onClick = { println("FloatingActionButton") }) {
            IconButton(onClick = { println("IconButton") }) {
                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null
                )
            }
        }

        Spacer(modifier = Modifier.height(10.dp))
        LargeFloatingActionButton(onClick = { println("FloatingActionButton") }) {

                Icon(
                    imageVector = Icons.Outlined.Edit,
                    contentDescription = null
                )

        }
        Spacer(modifier = Modifier.height(10.dp))
        var context= LocalContext.current
        ExtendedFloatingActionButton(
            // on below line we are setting text for our fab
            text = { Text(text = "Extended FAB") },
            // on below line we are adding click listener.
            onClick = {
                Toast.makeText(context, "Extended Floating Action Button", Toast.LENGTH_SHORT).show()
            },
            // on below line adding
            // a background color.
            containerColor = Color.Green,
            // on below line we are
            // adding a content color.
            contentColor = Color.White,
            // on below line we are
            // adding icon for our fab
            icon = { Icon(Icons.Filled.Add, "") }
        )

    }
}