package com.example.compoosepreperations.ui.screens.movies

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import coil.compose.rememberImagePainter
import com.example.compoosepreperations.data.MovieModel
import com.example.compoosepreperations.data.moviesList


@Composable
fun ChangeFromOutSide(modifier: Modifier = Modifier) {
    var counter by remember { mutableStateOf(0) }
    MoviesScreen(counter) { counter++ }
}

//we will use launch effect if there is change from outSide

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MoviesScreen(counter: Int, onButtonClick: () -> Unit) {
    var mutableMoviesList = remember { mutableStateListOf<MovieModel>() }
    LaunchedEffect(Unit) {
        mutableMoviesList.addAll(moviesList)
    }

    Log.d("MovieScreen", "Executed")
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Movies List") },
                navigationIcon = {
                    IconButton(onClick = { /* Handle navigation icon press */ }) {
                        Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "Menu")
                    }
                },
                actions = {
                    Button(onClick = {
                        mutableMoviesList.add(
                            MovieModel(
                                counter,
                                "Movie",
                                "https://via.placeholder.com/300/09f/fff.png"
                            )
                        )
                        onButtonClick.invoke()
                    }) {
                        Text(text = "$counter Add Item")
                    }
                }
            )
        }
    ) { paddingValues ->
        ListContent(modifier = Modifier.padding(paddingValues), moviesList = mutableMoviesList)
    }


}

@Composable
fun ListContent(
    modifier: Modifier = Modifier,
    moviesList: List<MovieModel> = emptyList()
) {

    LazyColumn(
        modifier = modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        items(moviesList) { movie ->
//            MovieItem(movie)
            Row(modifier = Modifier.fillMaxWidth().padding(10.dp).background(color = Color.Gray), verticalAlignment = Alignment.CenterVertically) {
                val painter: Painter = rememberAsyncImagePainter(model = movie.url)
                Image(painter =painter, contentDescription = "", modifier = Modifier.size(60.dp).padding(5.dp))
                Spacer(modifier = modifier.width(10.dp))
                Text (text = "${movie.id}  ${movie.name}")
            }
        }
    }
}


@Composable
fun MovieItem(movie: MovieModel) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp),
        verticalAlignment = Alignment.CenterVertically
    ) {
        val painter: Painter = rememberAsyncImagePainter(model = movie.url)
        Image(
            painter = painter,
            contentDescription = movie.name,
            modifier = Modifier
                .size(64.dp)
                .padding(end = 8.dp)
        )
        Column(modifier = Modifier.weight(1f)) {
            Text(text = movie.name, fontWeight = FontWeight.Bold)
            Text(text = "ID: ${movie.id}")
        }
        IconButton(onClick = { /* Remove item logic here */ }) {
            Icon(imageVector = Icons.Default.Delete, contentDescription = "Remove item")
        }
    }
}