package com.example.paginationjetpackcompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.example.paginationjetpackcompose.ui.screens.ItemList1
import com.example.paginationjetpackcompose.ui.screens.PullToRefreshImplementation
import com.example.paginationjetpackcompose.ui.theme.CompoosePreperationsTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            CompoosePreperationsTheme {
                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
                   ItemList1(modifier = Modifier.padding(innerPadding))
                }
//                PullToRefreshImplementation()
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Text(
        text = "Hello $name!",
        modifier = modifier
    )
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    CompoosePreperationsTheme {
        Greeting("Android")
    }
}