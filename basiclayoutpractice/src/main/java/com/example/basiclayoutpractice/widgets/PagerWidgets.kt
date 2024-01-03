package com.example.basiclayoutpractice.widgets

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.VerticalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.basiclayoutpractice.R


@OptIn(ExperimentalFoundationApi::class)
@Composable
fun HorizontalPagerWidget() {
    // Display 10 items
    val pagerState = rememberPagerState()
    HorizontalPager(
        pageCount = 10,
        state = pagerState,
        contentPadding = PaddingValues(10.dp),
        pageSpacing = 10.dp
    ) { index ->
        PagerCardItem(index = index)
    }

}

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun VerticalPagerWidget() {
    // Display 10 items
    val pagerState = rememberPagerState()
    VerticalPager(
        pageCount = 10,
        state = pagerState,
        contentPadding = PaddingValues(10.dp),
        pageSpacing = 10.dp
    ) { index ->
        VerticalPagerCardItem(index = index)
    }

}

data class ImageTitle(var img: Int, val title: String, val color: Color)

var imageTitleList = listOf(
    ImageTitle(R.drawable.thumb, "Title 1", Color.Green),
    ImageTitle(R.drawable.bash, "Title 2", Color.Red),
    ImageTitle(R.drawable.thumb, "Title 3", Color.Black),
    ImageTitle(R.drawable.bash, "Title 4", Color.Blue),
    ImageTitle(R.drawable.thumb, "Title 5", Color.Yellow),
    ImageTitle(R.drawable.bash, "Title 6", Color.Cyan),
    ImageTitle(R.drawable.thumb, "Title 7", Color.Magenta),
    ImageTitle(R.drawable.bash, "Title 8", Color.Green),
    ImageTitle(R.drawable.thumb, "Title 9", Color.White),
    ImageTitle(R.drawable.bash, "Title 10", Color.Blue)
)

@Composable
fun PagerCardItem(index: Int, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = imageTitleList[index].color),


        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .width(200.dp)
                .padding(vertical = 10.dp)
        ) {
            Image(
                painter = painterResource(id = imageTitleList[index].img),
                contentDescription = "",
                modifier = modifier.size(height = 150.dp, width = 100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = imageTitleList[index].title,
                style = TextStyle.Default.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}

@Composable
fun VerticalPagerCardItem(index: Int, modifier: Modifier = Modifier) {
    Card(
        elevation = CardDefaults.cardElevation(1.dp),
        shape = RoundedCornerShape(10.dp),
        colors = CardDefaults.cardColors(containerColor = imageTitleList[index].color),


        ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = modifier
                .fillMaxSize()
                .padding(vertical = 10.dp), verticalArrangement = Arrangement.SpaceBetween
        ) {
            Image(
                painter = painterResource(id = imageTitleList[index].img),
                contentDescription = "",
                modifier = modifier.size(height = 150.dp, width = 100.dp),
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = modifier.height(10.dp))
            Text(
                text = imageTitleList[index].title,
                style = TextStyle.Default.copy(fontSize = 16.sp, fontWeight = FontWeight.Bold)
            )
        }
    }
}